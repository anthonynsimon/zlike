package com.anthonynsimon.zlike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ZLikeGame extends ApplicationAdapter {
    SpriteBatch batch;

    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(new Vector2(0, 0));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);

        batch.begin();
        player.render(batch);
        batch.end();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
