package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.components.*;
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
        currentScene = DemoScene.create(atlas);

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
}
