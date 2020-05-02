package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch gameSpriteBatch;
    SpriteBatch hudSpriteBatch;

    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private GameMap gameMap;
    private BitmapFont font;

    private float stateTime = 0f;
    private float cameraFollowSpeed = 2.4f;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Globals.targetWidth, Globals.targetHeight, camera);

        font = new BitmapFont();
        gameSpriteBatch = new SpriteBatch();
        hudSpriteBatch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("spritesheet.txt"));
        player = new Player(new Vector2(16 * 10f, 16 * 10f), atlas);
        gameMap = new GameMap(atlas, camera);

        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);

        float alpha = cameraFollowSpeed * deltaTime;
        camera.position.set(
                MathUtils.lerp(camera.position.x, player.position.x, alpha),
                MathUtils.lerp(camera.position.y, player.position.y, alpha),
                0
        );
        camera.update();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
        float fps = Gdx.graphics.getFramesPerSecond();

        update(deltaTime);

        gameSpriteBatch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glTexParameterf(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAX_ANISOTROPY_EXT, 32);


        // Must be run outside of main sprite batch
        gameMap.render(gameSpriteBatch, stateTime);

        // Game Sprite Batch
        gameSpriteBatch.begin();
        player.render(gameSpriteBatch, stateTime);
        gameSpriteBatch.end();

        // HUD Sprite Batch
        hudSpriteBatch.begin();
        font.draw(hudSpriteBatch, "fps: " + fps, 16, 16);
        font.draw(hudSpriteBatch, "width: " + Gdx.graphics.getWidth() + " height: " + Gdx.graphics.getHeight(), 16, 32);
        hudSpriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        font.dispose();
        gameMap.dispose();
        atlas.dispose();
        gameSpriteBatch.dispose();
        hudSpriteBatch.dispose();
    }
}
