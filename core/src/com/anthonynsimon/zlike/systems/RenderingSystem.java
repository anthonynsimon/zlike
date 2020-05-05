package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.Globals;
import com.anthonynsimon.zlike.components.TextureComponent;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Objects;

public class RenderingSystem implements System {
    private Engine engine;
    private Batch batch;

    public OrthographicCamera camera;
    public Viewport viewport;

    public RenderingSystem(Engine engine) {
        this.engine = engine;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Globals.V_WIDTH, Globals.V_HEIGHT, this.camera);
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Sort by z depth and render in order
        batch.begin();
        engine.entities.keySet().stream()
                .map(entity -> {
                    TransformComponent transformComponent = (TransformComponent) engine.findEntityComponent(entity, TransformComponent.class);
                    TextureComponent textureComponent = (TextureComponent) engine.findEntityComponent(entity, TextureComponent.class);
                    // Skip missing transforms or invisible ones
                    if (transformComponent != null && transformComponent.isVisible && textureComponent != null && textureComponent.texture != null) {
                        return new RenderEntry(transformComponent, textureComponent);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .sorted()
                .forEach(this::draw);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void draw(RenderEntry renderEntry) {
        TextureRegion texture = renderEntry.textureComponent.texture;

        float width = texture.getRegionWidth();
        float height = texture.getRegionHeight();

        float originX = width / 2f;
        float originY = height / 2f;

        batch.draw(
                texture,
                renderEntry.transformComponent.position.x - originX,
                renderEntry.transformComponent.position.y - originY,
                originX,
                originY,
                width,
                height,
                renderEntry.transformComponent.scale.x,
                renderEntry.transformComponent.scale.y,
                renderEntry.transformComponent.rotation
        );
    }
}
