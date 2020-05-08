package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.math.Vector3;

public class MovementComponent extends Component {
    public final Vector3 velocity;

    public MovementComponent() {
        this.velocity = new Vector3(0f, 0f, 0f);
    }

    @Override
    public void update(float deltaTime) {
        TransformComponent transform = gameObject.transform;
        AnimationComponent animation = (AnimationComponent) gameObject.getComponent("animation");

        animation.state = velocity.isZero() ? "idle" : "running";

        // Facing left or right.
        // We intentionally don't do anything when velocity == 0.0 to keep the last direction we were facing.
        if (velocity.x < 0) {
            transform.scale.x = -1;
        } else if (velocity.x > 0) {
            transform.scale.x = 1;
        }

        transform.position.add(velocity);
        velocity.setZero();
    }
}
