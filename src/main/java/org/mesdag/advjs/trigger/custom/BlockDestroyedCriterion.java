package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.trigger.BaseTriggerInstanceBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;


public class BlockDestroyedCriterion extends AbstractCriterion<BlockDestroyedCriterion.Conditions> {
    static final Identifier ID = new Identifier("advjs:block_destroyed");

    @Override
    public @NotNull BlockDestroyedCriterion.Conditions conditionsFromJson(@NotNull JsonObject jsonObject, @NotNull LootContextPredicate composite, @NotNull AdvancementEntityPredicateDeserializer deserializationContext) {
        Block block = deserializeBlock(jsonObject);
        TagKey<Block> tag = jsonObject.has("tag") ? TagKey.of(RegistryKeys.BLOCK, new Identifier(jsonObject.get("tag").getAsString())) : null;
        StatePredicate state = StatePredicate.fromJson(jsonObject.get("state"));
        ItemPredicate item = ItemPredicate.fromJson(jsonObject.get("item"));
        return new Conditions(composite, block, tag, state, item);
    }

    @Nullable
    private static Block deserializeBlock(JsonObject jsonObject) {
        if (jsonObject.has("block")) {
            Identifier identifier = new Identifier(JsonHelper.asString(jsonObject, "block"));
            return Registries.BLOCK.getOrEmpty(identifier).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + identifier + "'"));
        } else {
            return null;
        }
    }

    public void trigger(ServerPlayerEntity serverPlayer, BlockState state, ItemStack stack) {
        this.trigger(serverPlayer, instance -> instance.matches(state, stack));
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public static Conditions create(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new Conditions(builder.player, builder.block, builder.tag, builder.statePredicate, builder.item);
    }

    public static class Builder extends BaseTriggerInstanceBuilder implements ItemSetter {
        @Nullable
        Block block = null;
        @Nullable
        TagKey<Block> tag = null;
        StatePredicate statePredicate = StatePredicate.ANY;
        ItemPredicate item = ItemPredicate.ANY;

        @Info("Checks the block that was destroyed.")
        public void setBlock(@Nullable Block block) {
            this.block = block;
        }

        @Info("Match block's tag.")
        public void ofTag(Identifier tag) {
            this.tag = TagKey.of(RegistryKeys.BLOCK, tag);
        }

        @Info("Checks states of destroyed block.")
        public void setState(StatePredicate statePredicate) {
            this.statePredicate = statePredicate;
        }

        @Info("The item used to break the block.")
        public void setItem(ItemPredicate item) {
            this.item = item;
        }

        @Info("The item used to break the block.")
        public void setItem(Ingredient ingredient) {
            this.item = wrapItem(ingredient);
        }
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final Block block;
        private final TagKey<Block> tag;
        private final StatePredicate statePredicate;
        private final ItemPredicate itemPredicate;

        public Conditions(LootContextPredicate composite, Block block, TagKey<Block> tag, StatePredicate statePredicate, ItemPredicate itemPredicate) {
            super(ID, composite);
            this.block = block;
            this.tag = tag;
            this.statePredicate = statePredicate;
            this.itemPredicate = itemPredicate;
        }

        public boolean matches(BlockState state, ItemStack stack) {
            if (this.tag != null && !state.isIn(this.tag)) {
                return false;
            } else if (this.block != null && !state.isOf(this.block)) {
                return false;
            } else {
                return statePredicate.test(state) && this.itemPredicate.test(stack);
            }
        }
    }
}
