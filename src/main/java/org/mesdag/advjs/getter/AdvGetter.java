package org.mesdag.advjs.getter;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public class AdvGetter {
    public static final HashMap<ResourceLocation, AdvGetter> getterMap = new HashMap<>();
    @Nullable
    private ResourceLocation parent;
    private final String name;
    private final ResourceLocation rootPath;
    private DisplayInfo displayInfo;
    private AdvancementRewards rewards = AdvancementRewards.EMPTY;
    private Map<String, Criterion> criteria;
    private String[][] requirements;

    private final boolean isRoot;

    public AdvGetter(String name, ResourceLocation rootPath) {
        this.name = name;
        this.rootPath = rootPath;
        this.isRoot = Objects.equals(name, "root");
        if (isRoot) parent = null;
    }

    public AdvGetter addChild(Consumer<AdvGetter> advGetterConsumer) {
        return addChild(UUID.randomUUID().toString().replaceAll("-", ""), advGetterConsumer);
    }

    public AdvGetter addChild(String name, Consumer<AdvGetter> advGetterConsumer) {
        AdvGetter child = new AdvGetter(name, rootPath);
        child.parent = getSavePath();
        advGetterConsumer.accept(child);

        return child;
    }

    public AdvGetter displayInfo(Consumer<DisplayBuilder> displayBuilderConsumer) {
        DisplayBuilder displayBuilder = new DisplayBuilder();
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground("textures/gui/advancements/backgrounds/stone.png");
        }
        this.displayInfo = new DisplayInfo(
            displayBuilder.getIcon(),
            displayBuilder.getTitle(),
            displayBuilder.getDescription(),
            displayBuilder.getBackground(),
            displayBuilder.getFrameType(),
            displayBuilder.isShowToast(),
            displayBuilder.isAnnounceToChat(),
            displayBuilder.isHidden()
        );

        getterMap.put(getSavePath(), this);
        return this;
    }

    public AdvGetter criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
        criteriaBuilderConsumer.accept(criteriaBuilder);
        this.criteria = criteriaBuilder.getCriteria();
        this.requirements = criteriaBuilder.getRequirements();

        getterMap.put(getSavePath(), this);
        return this;
    }

    public AdvGetter rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        RewardsBuilder rewardsBuilder = new RewardsBuilder();
        rewardsBuilderConsumer.accept(rewardsBuilder);
        this.rewards = rewardsBuilder.getRewards();

        getterMap.put(getSavePath(), this);
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
        return displayInfo;
    }

    public AdvancementRewards getRewards() {
        return rewards;
    }

    public Map<String, Criterion> getCriteria() {
        return criteria;
    }

    public String[][] getRequirements() {
        return requirements;
    }

    public boolean isRoot() {
        return isRoot;
    }
}
