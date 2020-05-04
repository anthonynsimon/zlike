package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.systems.System;
import com.anthonynsimon.zlike.systems.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameMain extends Game {
    private TextureAtlas atlas;
    private Engine engine;
    private RenderingSystem renderingSystem;
    private World world;

    @Override
    public void create() {
        // Load static assets
        atlas = new TextureAtlas(Gdx.files.internal("sprites/spritesheet.txt"));

        // Bootstrap engine
        engine = new Engine();

        // Create entities with components attached
        int playerId = Entities.createPlayerEntity(engine, atlas);

        // Create systems
        renderingSystem = new RenderingSystem(engine);
        engine.systems.add(renderingSystem);
        engine.systems.add(new MovementSystem(engine));
        engine.systems.add(new PlayerControlSystem(engine, playerId));
        engine.systems.add(new AnimationSystem(engine));
        engine.systems.add(new CameraControlSystem(engine, renderingSystem, playerId));
        engine.systems.add(new DebugSystem(engine));
        engine.systems.add(new World(renderingSystem.camera));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        for (System system : engine.systems) {
            system.update(deltaTime);
        }
    }

    @Override
    public void resize(int width, int height) {
        renderingSystem.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        for (System system : engine.systems) {
            system.dispose();
        }
        atlas.dispose();
    }
}
