package dev.ultreon.quentangle.item;

import dev.ultreon.quentangle.platform.Services;

public final class ItemProperties {
    public static final int MAX_STACK_SIZE = Services.PLATFORM.getDefaultMaxStackSize();

    private int maxStackSize = MAX_STACK_SIZE;
    private boolean isDamageable = true;
    private boolean isStackable = true;
    private boolean isFireProof = false;
    private FoodProperties food = null;

    private ItemProperties() {

    }

    public FoodProperties getFood() {
        return food;
    }

    public ItemProperties setFood(FoodProperties food) {
        this.food = food;
        return this;
    }

    public boolean isFireProof() {
        return isFireProof;
    }

    public ItemProperties setFireProof(boolean isFireProof) {
        this.isFireProof = isFireProof;
        return this;
    }

    public boolean isDamageable() {
        return isDamageable;
    }

    public ItemProperties setDamageable(boolean isDamageable) {
        this.isDamageable = isDamageable;
        return this;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public ItemProperties setStackable(boolean isStackable) {
        this.isStackable = isStackable;
        return this;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public ItemProperties setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public static ItemProperties create() {
        return new ItemProperties();
    }
}
