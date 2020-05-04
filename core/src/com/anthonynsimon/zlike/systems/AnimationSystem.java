package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.components.Component;
import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.components.AnimationComponent;
import com.anthonynsimon.zlike.components.TextureComponent;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Collection;

public class AnimationSystem implements System {
    private Engine engine;
    private float stateTime;

    public AnimationSystem(Engine engine) {
        this.engine = engine;
        this.stateTime = 0f;
    }

    @Override
    public void update(float deltaTime) {
        engine.entities.forEach(this::processEntity);
        stateTime += deltaTime;
    }

    @Override
    public void dispose() {
    }

    private void processEntity(Integer entity, Collection<Component> components) {
        AnimationComponent animationComponent = (AnimationComponent) engine.findEntityComponent(entity, AnimationComponent.class);
        TextureComponent textureComponent = (TextureComponent) engine.findEntityComponent(entity, TextureComponent.class);
        if (textureComponent != null && animationComponent != null) {
            Animation<TextureRegion> anim = animationComponent.animations.get(animationComponent.state);
            if (anim != null) {
                textureComponent.texture = anim.getKeyFrame(stateTime, true);
            }
        }
    }
}
