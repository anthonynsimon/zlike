package com.anthonynsimon.zlike;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class GameMap {

    private Sprite floor;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    public GameMap(TextureAtlas atlas, OrthographicCamera cam) {
        floor = atlas.createSprite("floor");
        tiledMap = new TiledMap();
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = cam;

        create();
    }

    public void create() {
        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(100, 100, 16, 16);
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();

        cell.setTile(new StaticTiledMapTile(floor));
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                layer1.setCell(i, j, cell);
            }
        }
        layers.add(layer1);
    }

    public void render(SpriteBatch batch, float stateTime) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
    }
}
