package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch batch;

    private Player player;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.virtualWidth, GameSettings.virtualHeight);

        batch = new SpriteBatch();

        player = new Player(new Vector2(0, 0));
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update camera projection matrix
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        player.render(batch);
        batch.end();

        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        // Resize camera viewport to keep aspect ratio and "zoom level"
        super.resize(width, height);
        camera.viewportWidth = GameSettings.virtualWidth;
        camera.viewportHeight = GameSettings.virtualWidth * ((float)height / (float)width);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();

    }
}
