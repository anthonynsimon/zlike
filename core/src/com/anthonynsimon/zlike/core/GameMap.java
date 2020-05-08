package com.anthonynsimon.zlike.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

// TODO: refactor into plain GameObject's
public class GameMap {
    private OrthogonalTiledMapRenderer renderer;
    private TiledMap map;
    private OrthographicCamera camera;

    public GameMap(OrthographicCamera camera, String mapName) {
        this.map = new TmxMapLoader().load(mapName);
        this.renderer = new OrthogonalTiledMapRenderer(map, 1);
        this.camera = camera;
    }

    public void render() {
        renderer.setView(camera);
        renderer.render();
    }

    public void destroy() {
        map.dispose();
    }
}
