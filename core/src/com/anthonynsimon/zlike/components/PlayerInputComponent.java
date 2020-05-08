package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerInputComponent extends Component {
    private float movementSpeed;

    public PlayerInputComponent(float movementSpeed) {
        this.setMovementSpeed(movementSpeed);
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(float deltaTime) {
        MovementComponent movement = (MovementComponent) gameObject.getComponent("movement");

        if (movement != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                movement.velocity.y = -movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                movement.velocity.y = movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                movement.velocity.x = -movementSpeed * deltaTime;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                movement.velocity.x = movementSpeed * deltaTime;
            }
        }
    }
}
