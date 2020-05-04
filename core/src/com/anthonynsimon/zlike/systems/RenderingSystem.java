package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.Globals;
import com.anthonynsimon.zlike.Engine;
import com.anthonynsimon.zlike.components.TextureComponent;
import com.anthonynsimon.zlike.components.TransformComponent;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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

        batch.begin();
        engine.entities.forEach((entity, components) -> {
            TransformComponent transform = (TransformComponent) engine.findEntityComponent(entity, TransformComponent.class);
            TextureComponent tex = (TextureComponent) engine.findEntityComponent(entity, TextureComponent.class);
            if (tex != null && transform != null && transform.isVisible) {
                float width = tex.texture.getRegionWidth();
                float height = tex.texture.getRegionHeight();

                float originX = width / 2f;
                float originY = height / 2f;

                batch.draw(
                        tex.texture,
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
        });
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
