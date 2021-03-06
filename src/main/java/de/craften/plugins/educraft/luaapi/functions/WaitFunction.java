package de.craften.plugins.educraft.luaapi.functions;

import de.craften.plugins.educraft.luaapi.EduCraftApiFunction;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * A function to wait for a given duration.
 */
public class WaitFunction extends EduCraftApiFunction {
    private static final long DEFAULT_TIME = 1000;

    @Override
    public Varargs invoke(Varargs varargs) {
        try {
            Thread.sleep(varargs.optlong(1, DEFAULT_TIME) + getApi().getFunctionDelay());
        } catch (InterruptedException e) {
            throw new LuaError(e);
        }
        return LuaValue.NIL;
    }

    @Override
    public Varargs execute(Varargs varargs) {
        return LuaValue.NIL; //no-op (this is not invoked)
    }
}
