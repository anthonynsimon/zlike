package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.GameConf;
import com.anthonynsimon.zlike.components.TextureComponent;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.anthonynsimon.zlike.core.GameObject;
import com.anthonynsimon.zlike.core.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RenderSystem {
    private Viewport viewport;
    private Scene scene;

    private Batch batch;

    public RenderSystem(Scene scene) {
        this.batch = new SpriteBatch();
        this.viewport = new FitViewport(GameConf.V_WIDTH, GameConf.V_HEIGHT);
        this.setScene(scene);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        viewport.setCamera(scene.camera.underlyingCamera);
    }

    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        OrthographicCamera activeCamera = scene.camera.underlyingCamera;
        activeCamera.update();
        batch.setProjectionMatrix(activeCamera.combined);

        scene.gameMap.render();

        batch.begin();
        for (GameObject gameObject : scene.gameObjects) {
            TextureComponent textureComponent = (TextureComponent) gameObject.getComponent("texture");
            TransformComponent transform = gameObject.transform;

            if (textureComponent != null && transform != null && transform.visible) {
                draw(textureComponent, transform);
            }
        }
        batch.end();
    }

    private void draw(TextureComponent textureComponent, TransformComponent transform) {
        float width = textureComponent.texture.getRegionWidth();
        float height = textureComponent.texture.getRegionHeight();

        float originX = width / 2f;
        float originY = height / 2f;

        batch.draw(
                textureComponent.texture,
                transform.position.x - originX,
                transform.position.y - originY,
                originX,
                originY,
                width,
                height,
                transform.scale.x,
                transform.scale.y,
                transform.rotation
        );
    }
}
