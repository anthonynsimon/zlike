package com.anthonynsimon.zlike.components;

import com.anthonynsimon.zlike.components.core.AnimationComponent;
import com.anthonynsimon.zlike.components.core.Component;
import com.anthonynsimon.zlike.components.core.TransformComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class PlayerInputComponent extends Component {
    private float movementSpeed;

    public PlayerInputComponent(float movementSpeed) {
        this.movementSpeed = movementSpeed;

    }

    @Override
    public void update(float deltaTime) {
        TransformComponent transform = (TransformComponent) gameObject.getComponent("transform");
        AnimationComponent animation = (AnimationComponent) gameObject.getComponent("animation");

        if (transform != null) {
            Vector3 velocity = new Vector3(0f, 0f, 0f);

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

            animation.state = velocity.isZero() ? "idle" : "running";

            // Facing left or right.
            // We intentionally don't do anything when velocity == 0.0 to keep the last direction we were facing.
            if (velocity.x < 0) {
                transform.scale.x = -1;
            } else if (velocity.x > 0) {
                transform.scale.x = 1;
            }

            transform.position.add(velocity);
        }
    }
}
