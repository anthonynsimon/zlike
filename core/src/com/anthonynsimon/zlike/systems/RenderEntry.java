package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.components.TextureComponent;
import com.anthonynsimon.zlike.components.TransformComponent;

public class RenderEntry implements Comparable<RenderEntry> {
    public final TransformComponent transformComponent;
    public final TextureComponent textureComponent;

    public RenderEntry(TransformComponent transformComponent, TextureComponent textureComponent) {
        this.transformComponent = transformComponent;
        this.textureComponent = textureComponent;
    }

    @Override
    public int compareTo(RenderEntry o) {
        return Float.compare(textureComponent.zindex, o.textureComponent.zindex);
    }
}
