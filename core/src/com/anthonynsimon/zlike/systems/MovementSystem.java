package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.components.TransformComponent;

public class MovementSystem implements System {
    private Engine engine;

    public MovementSystem(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        engine.entities.forEach((entity, components) -> {
            TransformComponent transform = (TransformComponent) engine.findEntityComponent(entity, TransformComponent.class);
            if (transform != null) {
                transform.position.add(transform.velocity);
            }
        });
    }

    @Override
    public void dispose() {
    }
}