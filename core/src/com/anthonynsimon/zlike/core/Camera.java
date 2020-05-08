package com.anthonynsimon.zlike.core;

import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends GameObject {
    public final OrthographicCamera underlyingCamera;

    public Camera() {
        this.underlyingCamera = new OrthographicCamera();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        TransformComponent transform = (TransformComponent) getComponent("transform");
        underlyingCamera.position.set(transform.position);
        underlyingCamera.rotate(transform.rotation);
    }
}
