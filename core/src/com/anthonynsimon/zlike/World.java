package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.systems.System;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

// TODO: how does this fit into ECS properly?
public class World implements System {
    private OrthogonalTiledMapRenderer renderer;
    private TiledMap map;
    private OrthographicCamera camera;

    public World(OrthographicCamera camera) {
        this.map = new TmxMapLoader().load("tilemap.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map, 1);
        this.camera = camera;
    }

    public void update(float deltaTime) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        map.dispose();
    }
}
