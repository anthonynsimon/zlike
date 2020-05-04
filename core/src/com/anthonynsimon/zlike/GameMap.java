package com.anthonynsimon.zlike;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMap {
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private TiledMap map;

    public GameMap(TextureAtlas atlas, OrthographicCamera cam) {
        camera = cam;
        map = new TmxMapLoader().load("tilemap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);
    }

    public void render(SpriteBatch batch, float stateTime) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        map.dispose();
    }
}
