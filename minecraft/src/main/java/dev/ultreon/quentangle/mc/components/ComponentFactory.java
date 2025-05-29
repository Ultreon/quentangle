package dev.ultreon.quentangle.mc.components;

@FunctionalInterface
public interface ComponentFactory<O, T extends Component<O>> {
    T create(O entity);
}
