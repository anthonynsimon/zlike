package com.anthonynsimon.zlike.components;

import com.anthonynsimon.zlike.core.GameObject;
import com.anthonynsimon.zlike.components.core.Component;
import com.anthonynsimon.zlike.components.core.TransformComponent;

public class CameraFollowComponent extends Component {
    private GameObject target;
    private float followSpeed;

    public CameraFollowComponent(GameObject target, float followSpeed) {
        this.target = target;
        this.followSpeed = followSpeed;
    }

    @Override
    public void update(float deltaTime) {
        if (target != null) {
            TransformComponent cameraTransform = (TransformComponent) this.gameObject.getComponent("transform");
            TransformComponent targetTransform = (TransformComponent) target.getComponent("transform");

            float alpha = followSpeed * deltaTime;
            
            cameraTransform.position.set(cameraTransform.position.lerp(targetTransform.position, alpha));
        }
    }
}
