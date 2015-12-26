package de.craften.plugins.educraft.luaapi;

import de.craften.plugins.educraft.ScriptExecutor;
import de.craften.plugins.managedentities.ManagedEntity;
import de.craften.plugins.managedentities.behavior.StationaryBehavior;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * The Lua API for EduCraft. This API works with an entity.
 */
public class EduCraftApi extends LuaTable {
    private Vector direction = new Vector(0, 0, -1);

    public EduCraftApi(final ManagedEntity entity) {
        entity.spawn();

        final StationaryBehavior stationary = new StationaryBehavior(entity.getEntity().getLocation().setDirection(direction), false);
        entity.addBehavior(stationary);

        set("moveForward", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                stationary.setLocation(stationary.getLocation().clone().add(direction));
                sleep();
                return LuaValue.NIL;
            }
        });

        set("turnLeft", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                direction = new Vector(direction.getZ(), 0, -direction.getX());
                stationary.setLocation(stationary.getLocation().clone().setDirection(direction));
                sleep();
                return LuaValue.NIL;
            }
        });

        set("turnRight", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                direction = new Vector(-direction.getZ(), 0, direction.getX());
                stationary.setLocation(stationary.getLocation().clone().setDirection(direction));
                sleep();
                return LuaValue.NIL;
            }
        });

        set("placeTorch", new SyncWaitingZeroArgFunction() {
            @Override
            public LuaValue callSync() {
                Block blockInSight = entity.getEntity().getLocation().add(direction).getBlock();
                if (blockInSight.getType() == Material.AIR) {
                    blockInSight.setType(Material.TORCH);
                } else {
                    blockInSight = blockInSight.getRelative(BlockFace.UP);
                    if (blockInSight.getRelative(0, 1, 0).getType() == Material.AIR) {
                        blockInSight.setType(Material.TORCH);
                    }
                }
                return LuaValue.NIL;
            }
        });

        stationary.setLocation(stationary.getLocation().clone().setDirection(direction));
    }

    private void sleep() {
        try {
            Thread.sleep(ScriptExecutor.MOVEMENT_DELAY);
        } catch (InterruptedException e) {
            throw new LuaError(e);
        }
    }
}
