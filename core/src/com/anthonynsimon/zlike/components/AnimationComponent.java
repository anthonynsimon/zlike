package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class AnimationComponent extends Component {
    public String state;
    private float stateTime;
    public Map<String, Animation<TextureRegion>> animations;

    public AnimationComponent(String state, Map<String, Animation<TextureRegion>> animations) {
        this.stateTime = 0f;
        this.state = state;
        this.animations = animations;
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        TextureComponent render = (TextureComponent) gameObject.getComponent("texture");
        Animation<TextureRegion> anim = animations.get(state);
        if (render != null && anim != null) {
            render.texture = anim.getKeyFrame(stateTime, true);
        }
    }
}
