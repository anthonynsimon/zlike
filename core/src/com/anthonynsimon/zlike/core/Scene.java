package com.anthonynsimon.zlike.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scene {
    public final List<GameObject> gameObjects;
    public final Camera camera;
    public final GameMap gameMap;

    public Scene(String mapName) {
        this.gameObjects = new ArrayList<>(128);

        this.camera = new Camera();
        this.gameObjects.add(this.camera);

        this.gameMap = new GameMap(this.camera.underlyingCamera, mapName);
    }

    public void awake() {
        for (GameObject gameObject : gameObjects) {
            gameObject.awake();
        }
    }

    public void update(float deltaTime) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTime);
        }
    }

    public void destroy() {
        gameMap.destroy();
    }

    public List<GameObject> findGameObjectsByTag(String tag) {
        return gameObjects.stream()
                .filter(g -> g.tags.contains(tag))
                .collect(Collectors.toList());
    }
}
