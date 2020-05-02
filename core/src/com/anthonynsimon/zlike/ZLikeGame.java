package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch batch;

    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private GameMap gameMap;
    private BitmapFont font;

    private float stateTime = 0f;
    private float cameraFollowSpeed = 3f;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.virtualWidth, GameSettings.virtualHeight);
        viewport = new FitViewport(GameSettings.virtualWidth, GameSettings.virtualHeight, camera);

        font = new BitmapFont();
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spritesheet.txt"));
        player = new Player(new Vector3(16 * 10f, 16 * 10f, 0f), atlas);
        gameMap = new GameMap(atlas, camera);
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;

        player.update(deltaTime);

        camera.position.set(camera.position.lerp(player.position, cameraFollowSpeed * deltaTime));
    }

    @Override
    public void render() {
        float fps = Gdx.graphics.getFramesPerSecond();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Must be run outside of main sprite batch
        gameMap.render(batch, stateTime);

        batch.begin();
        player.render(batch, stateTime);
        font.draw(batch, "fps: " + fps, (camera.position.x - (GameSettings.virtualWidth / 2f)) + 8, (camera.position.y + (GameSettings.virtualHeight / 2f)) - 8);
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
        font.dispose();
        gameMap.dispose();
        atlas.dispose();
        batch.dispose();
    }
}
