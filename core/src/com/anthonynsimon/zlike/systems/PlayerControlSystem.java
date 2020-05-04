package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.components.AnimationComponent;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class PlayerControlSystem implements System {
    private Engine engine;
    private int playerEntityId;
    private float movementSpeed = 100f;

    public PlayerControlSystem(Engine engine, int playerEntityId) {
        this.engine = engine;
        this.playerEntityId = playerEntityId;
    }

    @Override
    public void update(float deltaTime) {
        TransformComponent transformComponent = (TransformComponent) engine.findEntityComponent(playerEntityId, TransformComponent.class);
        AnimationComponent animationComponent = (AnimationComponent) engine.findEntityComponent(playerEntityId, AnimationComponent.class);

        if (transformComponent != null) {
            Vector2 velocity = transformComponent.velocity;
            velocity.setZero();

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                velocity.y = -movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                velocity.y = movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                velocity.x = -movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                velocity.x = movementSpeed * deltaTime;
            }

            animationComponent.state = transformComponent.velocity.isZero() ? "idle" : "running";

            // Facing left or right.
            // We intentionally don't do anything when velocity == 0.0 to keep the last direction we were facing.
            if (velocity.x < 0) {
                transformComponent.scale.x = -1;
            } else if (velocity.x > 0) {
                transformComponent.scale.x = 1;
            }
            ;
        }
    }

    @Override
    public void dispose() {
    }
}