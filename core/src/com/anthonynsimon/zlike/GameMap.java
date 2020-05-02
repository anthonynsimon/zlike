package com.anthonynsimon.zlike;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameMap {

    private Array<TextureAtlas.AtlasRegion> floors;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Random rand;

    public GameMap(TextureAtlas atlas, OrthographicCamera cam) {
        floors = atlas.findRegions("floor");

        tiledMap = new TiledMap();
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = cam;

        rand = new Random();

        create();
    }

    public void create() {
        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer1 = new TiledMapTileLayer(100, 100, Globals.tileSize, Globals.tileSize);

        ArrayList<TiledMapTileLayer.Cell> cells = IntStream.range(0, floors.size)
                .mapToObj(i -> {
                    TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                    cell.setTile(new StaticTiledMapTile(floors.get(i)));
                    return cell;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int choice = rand.nextInt(floors.size);
                layer1.setCell(i, j, cells.get(choice));
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
