package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.components.CameraFollowComponent;
import com.anthonynsimon.zlike.components.PlayerInputComponent;
import com.anthonynsimon.zlike.components.core.AnimationComponent;
import com.anthonynsimon.zlike.components.core.TextureComponent;
import com.anthonynsimon.zlike.components.core.TransformComponent;
import com.anthonynsimon.zlike.core.GameObject;
import com.anthonynsimon.zlike.core.Scene;
import com.anthonynsimon.zlike.systems.DebugSystem;
import com.anthonynsimon.zlike.systems.RenderSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

public class GameMain extends Game {
    private TextureAtlas atlas;
    private Scene currentScene;
    private DebugSystem debugSystem;
    private RenderSystem renderSystem;

    @Override
    public void create() {
        // Load static assets
        atlas = new TextureAtlas(Gdx.files.internal("sprites/spritesheet.txt"));

        // Setup initial scene and game objects
        setupScene();

        // Setup systems
        renderSystem = new RenderSystem(currentScene);
        debugSystem = new DebugSystem(currentScene);

        currentScene.awake();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        currentScene.update(deltaTime);
        renderSystem.render();

        if (GameConf.DEBUG) {
            debugSystem.render();
        }
    }

    @Override
    public void resize(int width, int height) {
        renderSystem.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        currentScene.destroy();
        debugSystem.destroy();
        atlas.dispose();
    }


    private void setupScene() {
        currentScene = new Scene("tilemap.tmx");

        GameObject player = new GameObject();
        TransformComponent playerTransform = (TransformComponent)player.getComponent("transform");
        playerTransform.position.set(new Vector3(64, 64, 0));
        player.addComponent("playerInput", new PlayerInputComponent(100));
        player.addComponent("texture", new TextureComponent(null)); // idle anim is set right after this
        player.addComponent("animation", new AnimationComponent("idle", new HashMap<String, Animation<TextureRegion>>() {{
            put("idle", new Animation<>(
                    0.1f,
                    atlas.findRegions("elf_m_idle_anim"),
                    Animation.PlayMode.LOOP
            ));
            put("running", new Animation<>(
                    0.1f,
                    atlas.findRegions("elf_m_run_anim"),
                    Animation.PlayMode.LOOP
            ));
        }}));
        player.tags.add("player");
        currentScene.gameObjects.add(player);

        currentScene.camera.addComponent("followPlayer", new CameraFollowComponent(player, 2.8f));
    }
}
