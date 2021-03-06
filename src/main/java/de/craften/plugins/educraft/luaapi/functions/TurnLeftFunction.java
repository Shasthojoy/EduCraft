package de.craften.plugins.educraft.luaapi.functions;

import de.craften.plugins.educraft.luaapi.EduCraftApiFunction;
import org.bukkit.util.Vector;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * Lua API function to turn 90° left.
 */
public class TurnLeftFunction extends EduCraftApiFunction {
    @Override
    public Varargs execute(Varargs varargs) {
        getApi().setDirection(new Vector(getApi().getDirection().getZ(), 0, -getApi().getDirection().getX()));
        return LuaValue.NIL;
    }
}
