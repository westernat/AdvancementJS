package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.MinMaxBoundsProvider;

import static org.mesdag.advjs.adv.Data.REMOVES;

public class AdvCreateEvent extends EventJS {
    @Info("Trigger required in advancement.")
    public final Trigger TRIGGER = new Trigger();
    @Info("Predicate required in trigger.")
    public final Predicate PREDICATE = new Predicate();
    @Info("Bounds required in predicate.")
    public final MinMaxBoundsProvider BOUNDS = new MinMaxBoundsProvider();

    @Info("Create a new advancement root")
    public AdvBuilder create(Identifier rootPath) {
        return new AdvBuilder(null, "root", rootPath, false);
    }

    @Info("Remove an exist advancement.")
    public void remove(Identifier remove) {
        REMOVES.add(remove);
    }

    @Info("Get an exist advancement to modify.")
    public AdvGetter get(Identifier path) {
        return new AdvGetter(path);
    }
}
