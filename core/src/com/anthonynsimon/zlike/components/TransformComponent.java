package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    public final Vector2 position = new Vector2(0f, 0f);
    public final Vector2 velocity = new Vector2(0f, 0f);
    public final Vector2 scale = new Vector2(1f, 1f);
    public boolean isVisible = true;
    public float rotation = 0f;
}
