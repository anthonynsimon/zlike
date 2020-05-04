package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DebugSystem implements System {
    private Engine engine;
    private Batch batch;
    private BitmapFont font;

    public DebugSystem(Engine engine) {
        this.engine = engine;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 16, 16);
        font.draw(batch, "width: " + Gdx.graphics.getWidth() + " height: " + Gdx.graphics.getHeight(), 16, 32);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}