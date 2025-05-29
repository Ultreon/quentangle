package dev.ultreon.quentangle.mc.nbt;

public interface DataKey<E> {
    String getKey(E entity);

    static <E> DataKey<E> of(String modId, String key) {
        return entity -> modId + ":" + key;
    }
}
