package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.Bounds;

import static org.mesdag.advjs.adv.Data.LOCK_MAP;
import static org.mesdag.advjs.adv.Data.REMOVES;

public class AdvancementEvent extends EventJS {
    public final Trigger TRIGGER = new Trigger();
    public final Predicate PREDICATE = new Predicate();
    public final Bounds BOUNDS = new Bounds();

    public AdvBuilder create(ResourceLocation rootPath) {
        return new AdvBuilder(null, "root", rootPath, false);
    }

    public void remove(ResourceLocation remove) {
        REMOVES.add(remove);
    }

    public AdvGetter get(ResourceLocation path) {
        return new AdvGetter(path);
    }

    public void lock(ItemStackJS toLock, ResourceLocation lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy);
    }

    public void lock(ItemStackJS toLock, AdvBuilder lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy.getSavePath());
    }
}
