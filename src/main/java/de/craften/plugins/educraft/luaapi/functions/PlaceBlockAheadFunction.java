package de.craften.plugins.educraft.luaapi.functions;

import de.craften.plugins.educraft.luaapi.EduCraftApiFunction;
import org.bukkit.Location;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * Lua API function that places a block in front of the entity.
 */
public class PlaceBlockAheadFunction extends EduCraftApiFunction {
    @Override
    public Varargs execute(Varargs varargs) {
        Location location = getApi().getBlockAhead().getLocation();
        if (getApi().getEnvironment().contains(location) && !getApi().getEnvironment().isAliveEntityAt(location)) {
            getApi().getBlockAhead().setType(PlaceBlockFunction.getMaterial(varargs.checkjstring(1)));
        }
        return LuaValue.NIL;
    }
}
