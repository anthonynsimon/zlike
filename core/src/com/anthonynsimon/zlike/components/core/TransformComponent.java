package com.anthonynsimon.zlike.components.core;

import com.badlogic.gdx.math.Vector3;

public class TransformComponent extends Component {
    public Vector3 position;
    public Vector3 scale;
    public float rotation;
    public boolean visible;

    public TransformComponent() {
        this(new Vector3(0f, 0f, 0f), new Vector3(1, 1, 1), 0f, true);
    }

    public TransformComponent(Vector3 position, Vector3 scale, float rotation, boolean visible) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.visible = visible;
    }
}
