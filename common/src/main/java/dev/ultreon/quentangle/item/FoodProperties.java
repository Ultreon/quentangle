package dev.ultreon.quentangle.item;

public record FoodProperties(
        float saturation,
        float hunger
) {
    public FoodProperties {
        if (saturation < 0) throw new IllegalArgumentException("Saturation must be greater than or equal to zero");
        if (hunger < 0) throw new IllegalArgumentException("Hunger must be greater than or equal to zero");
    }
}
