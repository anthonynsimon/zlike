package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch batch;

    private TextureAtlas atlas;
    private Player player;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float stateTime = 0f;
    private float cameraFollowSpeed = 0.5f;

    @Override
    public void create() {
        camera = new OrthographicCamera(GameSettings.virtualWidth, GameSettings.virtualHeight);
        viewport = new FitViewport(GameSettings.virtualWidth, GameSettings.virtualHeight, camera);

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spritesheet.txt"));
        player = new Player(new Vector3(0f, 0f, 0f), atlas);
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;

        player.update(deltaTime);

        camera.position.set(camera.position.lerp(player.position, cameraFollowSpeed * deltaTime));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        player.render(batch, stateTime);
        batch.end();

        update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
    }
}
