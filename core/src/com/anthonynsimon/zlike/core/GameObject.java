package com.anthonynsimon.zlike.core;

import com.anthonynsimon.zlike.components.Component;
import com.anthonynsimon.zlike.components.TransformComponent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameObject {
    public final Set<String> tags;
    public final TransformComponent transform;
    protected final Map<String, Component> components;

    public GameObject() {
        this.tags = new HashSet<>();
        this.components = new HashMap<>();
        // Cache transform component as all game objects must have one and it's frequently accessed
        this.transform = new TransformComponent();
        this.addComponent("transform", transform);
    }
    
    public void awake() {
        for (Component component : components.values()) {
            component.awake();
        }
    }

    public void update(float deltaTime) {
        for (Component component : components.values()) {
            component.update(deltaTime);
        }
    }

    public void addComponent(String name, Component component) {
        components.put(name, component);
        component.attach(this);
    }

    public Component getComponent(String name) {
        return components.get(name);
    }

    public Component removeComponent(String name) {
        Component component = components.remove(name);
        if (component != null) {
            component.detach();
        }
        return component;
    }
}
