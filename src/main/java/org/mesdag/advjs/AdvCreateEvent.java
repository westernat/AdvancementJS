package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.getter.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.MinMaxBoundsProvider;

import static org.mesdag.advjs.util.Data.REMOVES;

public class AdvCreateEvent extends EventJS {
    public final Trigger TRIGGER = new Trigger();
    public final Predicate PREDICATE = new Predicate();
    public final MinMaxBoundsProvider BOUNDS = new MinMaxBoundsProvider();

    public AdvGetter create(Identifier rootPath) {
        return new AdvGetter(null, "root", rootPath, false);
    }

    public void remove(Identifier remove) {
        REMOVES.add(remove);
    }
}
