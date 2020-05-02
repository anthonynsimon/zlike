package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.io.FileReader;
import java.io.StringReader;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch batch;

    private TextureAtlas atlas;
    private Player player;
    private OrthographicCamera camera;
    private float stateTime = 0f;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.virtualWidth, GameSettings.virtualHeight);

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spritesheet.txt"));
        player = new Player(new Vector2(0f, 0f), atlas);
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;

        // Update camera projection matrix
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        player.render(batch, stateTime);
        batch.end();

        update(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        // Resize camera viewport to keep aspect ratio and "zoom level"
        super.resize(width, height);
        camera.viewportWidth = GameSettings.virtualWidth;
        camera.viewportHeight = GameSettings.virtualWidth * ((float) height / (float) width);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }
}
