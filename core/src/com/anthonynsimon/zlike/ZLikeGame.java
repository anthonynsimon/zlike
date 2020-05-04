package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ZLikeGame extends ApplicationAdapter {
    Batch hudSpriteBatch;

    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private BitmapFont font;

    private OrthogonalTiledMapRenderer renderer;
    private TiledMap map;

    private float stateTime = 0f;
    private float cameraFollowSpeed = 2.4f;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Globals.V_WIDTH, Globals.V_HEIGHT, camera);

        font = new BitmapFont();
        hudSpriteBatch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("sprites/spritesheet.txt"));
        player = new Player(new Vector2(16 * 10f, 16 * 10f), atlas);

        map = new TmxMapLoader().load("tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        camera.position.set(player.position.x, player.position.y, 0);
        camera.update();
    }

    public void update(float deltaTime) {
        Vector2 previousPosition = player.position.cpy();
        player.update(deltaTime);
        if (collision(player, map)) {
            player.position.set(previousPosition);
        };

        float alpha = cameraFollowSpeed * deltaTime;
        camera.position.set(
                MathUtils.lerp(camera.position.x, player.position.x, alpha),
                MathUtils.lerp(camera.position.y, player.position.y, alpha),
                0
        );
        camera.update();
    }

    private boolean collision(Player player, TiledMap map) {
        TiledMapTileLayer walls = (TiledMapTileLayer)map.getLayers().get("Walls");

        // No fancy box2d physics here.
        // Plain and simple collision detection by checking titles surrounding player coordinates
         TiledMapTileLayer.Cell cell = walls.getCell((int)player.position.x/16, (int)player.position.y/16);
         if (cell != null) {
             return true;
         }
         return false;
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
        float fps = Gdx.graphics.getFramesPerSecond();

        update(deltaTime);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Must be run outside of main sprite batch
        Batch batch = renderer.getBatch();
        renderer.setView(camera);
        renderer.render();

        // Game Sprite Batch
        batch.begin();
        player.render(batch, stateTime);
        batch.end();

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
        map.dispose();
        atlas.dispose();
        hudSpriteBatch.dispose();
    }
}
