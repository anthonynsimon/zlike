package com.anthonynsimon.zlike;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Sprite sprite;
    private Texture texture = new Texture("elf_m_idle_anim_f0.png");
    private float speed = 100f;

    public Player(Vector2 pos) {
        position = pos;
        sprite = new Sprite(texture);
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))  position.y -= speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) position.y += speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= speed * deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) position.x += speed * deltaTime;
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }
}
