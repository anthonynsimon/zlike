package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.math.MathUtils;

public class CameraControlSystem implements System {
    private Engine engine;
    private RenderingSystem renderingSystem;
    private int targetEntityId;
    private float cameraFollowSpeed = 2.8f;

    public CameraControlSystem(Engine engine, RenderingSystem renderingSystem, int targetEntityId) {
        this.engine = engine;
        this.renderingSystem = renderingSystem;
        this.targetEntityId = targetEntityId;
    }

    @Override
    public void update(float deltaTime) {
        TransformComponent targetTransform = (TransformComponent) engine.findEntityComponent(targetEntityId, TransformComponent.class);

        if (targetTransform != null) {
            float alpha = cameraFollowSpeed * deltaTime;
            renderingSystem.camera.position.set(
                    MathUtils.lerp(renderingSystem.camera.position.x, targetTransform.position.x, alpha),
                    MathUtils.lerp(renderingSystem.camera.position.y, targetTransform.position.y, alpha),
                    0
            );
            renderingSystem.camera.update();
        }
    }

    @Override
    public void dispose() {
    }
}