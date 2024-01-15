package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.LightningBoltPredicate;
import org.mesdag.advjs.predicate.LightningBoltPredicateBuilder;

import java.util.function.Consumer;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    LootContextPredicate lightning = LootContextPredicate.EMPTY;
    LootContextPredicate bystander =LootContextPredicate.EMPTY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(LightningBoltPredicate lightning) {
        this.lightning = lightning.build();
    }

    @Info("The lightning bolt that disappeared.")
    public void setLightning(Consumer<LightningBoltPredicateBuilder> consumer) {
        LightningBoltPredicateBuilder builder = new LightningBoltPredicateBuilder();
        consumer.accept(builder);
        this.lightning = builder.predicate().build();
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.asLootContextPredicate(bystander);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.bystander = EntityPredicate.asLootContextPredicate(builder.build());
    }
}
