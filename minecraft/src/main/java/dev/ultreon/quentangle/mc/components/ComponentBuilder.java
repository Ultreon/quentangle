package dev.ultreon.quentangle.mc.components;

public abstract class ComponentBuilder<O, T extends Component<O>> {
    final Class<T> componentClass;

    public ComponentBuilder(Class<T> componentClass) {
        this.componentClass = componentClass;
    }
}
