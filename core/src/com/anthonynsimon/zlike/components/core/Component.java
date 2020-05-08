package com.anthonynsimon.zlike.components.core;

import com.anthonynsimon.zlike.core.GameObject;

public abstract class Component {
    protected GameObject gameObject;

    public void attach(GameObject parent) {
        this.gameObject = parent;
    }

    public void detach() {
        this.gameObject = null;
    }

    public void awake() {}

    public void update(float deltaTime) {}
}
