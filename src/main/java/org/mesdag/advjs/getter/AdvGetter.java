package org.mesdag.advjs.getter;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.GETTER_MAP;

public class AdvGetter {
    @Nullable
    private final Identifier parent;
    private final String name;
    private final Identifier rootPath;
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();

    private final boolean attention;

    public AdvGetter(@Nullable Identifier parent, String name, Identifier rootPath, boolean attention) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.attention = attention;
    }

    public AdvGetter addChild(Consumer<AdvGetter> advGetterConsumer) {
        AdvGetter child = new AdvGetter(getSavePath(), UUID.randomUUID().toString(), rootPath, true);
        advGetterConsumer.accept(child);

        GETTER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvGetter addChild(String name, Consumer<AdvGetter> advGetterConsumer) {
        AdvGetter child = new AdvGetter(getSavePath(), name, rootPath, false);
        advGetterConsumer.accept(child);

        GETTER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvGetter display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground("textures/gui/advancements/backgrounds/stone.png");
        }

        GETTER_MAP.put(getSavePath(), this);
        return this;
    }

    public AdvGetter criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);

        GETTER_MAP.put(getSavePath(), this);
        return this;
    }

    public AdvGetter rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        rewardsBuilderConsumer.accept(rewardsBuilder);

        GETTER_MAP.put(getSavePath(), this);
        return this;
    }

    public @Nullable Identifier getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Identifier getSavePath() {
        return new Identifier(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    public AdvancementDisplay getDisplayInfo() {
        return new AdvancementDisplay(
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

    public Map<String, AdvancementCriterion> getCriteria() {
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
