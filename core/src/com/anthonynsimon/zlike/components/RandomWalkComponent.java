package com.anthonynsimon.zlike.components;

import com.anthonynsimon.zlike.GameConf;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector3;

public class RandomWalkComponent extends Component {

    private Vector3 target;
    private float speed;
    private MovementComponent movementComponent;

    public RandomWalkComponent(float speed) {
        this.speed = speed;
    }

    @Override
    public void awake() {
        target = newTarget();
        movementComponent = (MovementComponent) gameObject.getComponent("movement");
    }

    @Override
    public void update(float deltaTime) {
        if (movementComponent != null) {
            if (gameObject.transform.position.dst2(target) < 1f) {
                target = newTarget();
            }
            // Compute normalized direction vector and scale by desired speed step
            float alpha = speed * deltaTime;
            Vector3 velocity = target.cpy().sub(gameObject.transform.position).nor().scl(alpha);
            movementComponent.velocity.set(velocity);
        }
    }

    public Vector3 newTarget() {
        float x = (float) (Math.random() * GameConf.MAP_WIDTH * GameConf.PIXELS_PER_UNIT) - (GameConf.MAP_WIDTH / 2f * GameConf.PIXELS_PER_UNIT);
        float y = (float) (Math.random() * GameConf.MAP_HEIGHT * GameConf.PIXELS_PER_UNIT) - (GameConf.MAP_HEIGHT / 2f * GameConf.PIXELS_PER_UNIT);
        return new Vector3(x, y , 0f);
    }
}
