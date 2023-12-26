package org.mesdag.advjs.getter;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.util.Data;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class AdvGetter {
    @Nullable
    private final ResourceLocation parent;
    private final String name;
    private final ResourceLocation rootPath;
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();

    private final boolean attention;

    public AdvGetter(@Nullable ResourceLocation parent, String name, ResourceLocation rootPath, boolean attention) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.attention = attention;
    }

    public AdvGetter addChild(Consumer<AdvGetter> advGetterConsumer) {
        AdvGetter child = new AdvGetter(getSavePath(), UUID.randomUUID().toString().replaceAll("-", ""), rootPath, true);
        advGetterConsumer.accept(child);

        return child;
    }

    public AdvGetter addChild(String name, Consumer<AdvGetter> advGetterConsumer) {
        AdvGetter child = new AdvGetter(getSavePath(), name, rootPath, false);
        advGetterConsumer.accept(child);

        return child;
    }

    public AdvGetter display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground("textures/gui/advancements/backgrounds/stone.png");
        }

        Data.getterMap.put(getSavePath(), this);
        return this;
    }

    public AdvGetter criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);

        Data.getterMap.put(getSavePath(), this);
        return this;
    }

    public AdvGetter rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        rewardsBuilderConsumer.accept(rewardsBuilder);

        Data.getterMap.put(getSavePath(), this);
        return this;
    }

    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getSavePath() {
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    public DisplayInfo getDisplayInfo() {
        return new DisplayInfo(
            displayBuilder.getIcon(),
            displayBuilder.getTitle(),
            displayBuilder.getDescription(),
            displayBuilder.getBackground(),
            displayBuilder.getFrameType(),
            displayBuilder.isShowToast(),
            displayBuilder.isAnnounceToChat(),
            displayBuilder.isHidden()
        );
    }

    public Map<String, Criterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    public AdvancementRewards getRewards() {
        return rewardsBuilder.getRewards();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isAttention() {
        return attention;
    }
}
