package com.anthonynsimon.zlike.components;

import com.anthonynsimon.zlike.core.GameObject;

public class CameraFollowComponent extends Component {
    private GameObject target;
    private float followSpeed;

    public CameraFollowComponent(GameObject target, float followSpeed) {
        setTarget(target);
        setFollowSpeed(followSpeed);
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public void setFollowSpeed(float followSpeed) {
        this.followSpeed = followSpeed;
    }

    @Override
    public void update(float deltaTime) {
        if (target != null) {
            TransformComponent cameraTransform = gameObject.transform;
            TransformComponent targetTransform = target.transform;

            float alpha = followSpeed * deltaTime;
            cameraTransform.position.set(cameraTransform.position.lerp(targetTransform.position, alpha));
        }
    }
}
