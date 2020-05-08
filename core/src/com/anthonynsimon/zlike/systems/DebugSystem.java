package com.anthonynsimon.zlike.systems;

import com.anthonynsimon.zlike.components.TransformComponent;
import com.anthonynsimon.zlike.core.GameObject;
import com.anthonynsimon.zlike.core.Scene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Optional;

public class DebugSystem {
    private BitmapFont font;
    private Batch batch;
    private Scene scene;

    public DebugSystem(Scene scene) {
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.setScene(scene);
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("width: %d height: %d\nfps: %d\n", Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Gdx.graphics.getFramesPerSecond()));

        Optional<GameObject> player = scene.findGameObjectsByTag("player").stream().findFirst();
        if (player.isPresent()) {
            TransformComponent transform = (TransformComponent) player.get().getComponent("transform");
            sb.append(String.format("position:\n  x=%f\n  y=%f\n  z=%f\n", transform.position.x, transform.position.y, transform.position.z));
        }

        batch.begin();
        font.draw(batch, sb.toString(), 16, 128);
        batch.end();
    }

    public void destroy() {
        this.batch.dispose();
        this.font.dispose();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
