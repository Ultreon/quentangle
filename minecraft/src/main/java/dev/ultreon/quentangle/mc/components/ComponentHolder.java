package dev.ultreon.quentangle.mc.components;

public interface ComponentHolder<O, T extends Component<O>> {
    T get(O value);
}
