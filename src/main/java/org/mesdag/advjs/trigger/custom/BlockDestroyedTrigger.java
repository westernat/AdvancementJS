package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.trigger.BaseTriggerInstanceBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;


public class BlockDestroyedTrigger extends SimpleCriterionTrigger<BlockDestroyedTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("advjs", "block_destroyed");

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate composite, @NotNull DeserializationContext deserializationContext) {
        Block block = deserializeBlock(jsonObject);
        TagKey<Block> tag = jsonObject.has("tag") ? TagKey.create(Registries.BLOCK, new ResourceLocation(jsonObject.get("tag").getAsString())) : null;
        StatePropertiesPredicate state = StatePropertiesPredicate.fromJson(jsonObject.get("state"));
        ItemPredicate item = ItemPredicate.fromJson(jsonObject.get("item"));
        return new TriggerInstance(composite, block, tag, state, item);
    }

    @Nullable
    private static Block deserializeBlock(JsonObject jsonObject) {
        if (jsonObject.has("block")) {
            ResourceLocation resourcelocation = new ResourceLocation(GsonHelper.getAsString(jsonObject, "block"));
            return BuiltInRegistries.BLOCK.getOptional(resourcelocation).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + resourcelocation + "'"));
        } else {
            return null;
        }
    }

    public void trigger(ServerPlayer serverPlayer, BlockState state, ItemStack stack) {
        this.trigger(serverPlayer, instance -> instance.matches(state, stack));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public static TriggerInstance create(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new TriggerInstance(builder.player, builder.block, builder.tag, builder.statePredicate, builder.item);
    }

    public static class Builder extends BaseTriggerInstanceBuilder implements ItemSetter {
        @Nullable
        Block block;
        @Nullable
        TagKey<Block> tag;
        StatePropertiesPredicate statePredicate = StatePropertiesPredicate.ANY;
        ItemPredicate item = ItemPredicate.ANY;

        @Info("Checks the block that was destroyed.")
        public void setBlock(@Nullable Block block) {
            this.block = block;
        }

        @Info("Match block's tag.")
        public void ofTag(ResourceLocation tag) {
            this.tag = TagKey.create(Registries.BLOCK, tag);
        }

        @Info("Checks states of destroyed block.")
        public void setState(StatePropertiesPredicate statePredicate) {
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

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final Block block;
        private final TagKey<Block> tag;
        private final StatePropertiesPredicate statePredicate;
        private final ItemPredicate itemPredicate;

        public TriggerInstance(@NotNull ContextAwarePredicate composite, Block block, TagKey<Block> tag, StatePropertiesPredicate statePredicate, ItemPredicate itemPredicate) {
            super(ID, composite);
            this.block = block;
            this.tag = tag;
            this.statePredicate = statePredicate;
            this.itemPredicate = itemPredicate;
        }

        public boolean matches(BlockState state, ItemStack stack) {
            if (this.tag != null && !state.is(this.tag)) {
                return false;
            } else if (this.block != null && !state.is(this.block)) {
                return false;
            } else {
                return statePredicate.matches(state) && this.itemPredicate.matches(stack);
            }
        }
    }
}
