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
        // Match the GameObject's transform and the underlyingCamera one
        underlyingCamera.position.set(transform.position);
        underlyingCamera.rotate(transform.rotation);
    }
}
