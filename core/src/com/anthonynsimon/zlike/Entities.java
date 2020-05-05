package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.components.AnimationComponent;
import com.anthonynsimon.zlike.components.TextureComponent;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class Entities {

    public static int createPlayerEntity(Engine engine, TextureAtlas atlas) {
        int playerId = engine.newEntityId();

        engine.addComponent(playerId, new TransformComponent());

        TextureComponent tex = new TextureComponent();
        tex.texture = atlas.findRegion("elf_m_idle_anim");
        tex.zindex = 100;
        engine.addComponent(playerId, tex);

        AnimationComponent anim = new AnimationComponent();
        anim.state = "idle";
        anim.animations = new HashMap<>();
        anim.animations.put("idle",
                new Animation<>(
                        0.1f,
                        atlas.findRegions("elf_m_idle_anim"),
                        Animation.PlayMode.LOOP
                ));
        anim.animations.put("running",
                new Animation<>(
                        0.1f,
                        atlas.findRegions("elf_m_run_anim"),
                        Animation.PlayMode.LOOP
                ));
        engine.addComponent(playerId, anim);

        return playerId;
    }
}
