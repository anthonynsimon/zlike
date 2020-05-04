package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class AnimationComponent implements Component {
    public String state;
    public Map<String, Animation<TextureRegion>> animations;
}
