package com.anthonynsimon.zlike;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private final Vector2 position;

    private final Texture[] idleTextures;
    private final Animation<Texture> idleAnimation;

    private final Texture[] runningTextures;
    private final Animation<Texture> runningAnimation;

    private final float speed = 100f;
    private boolean isMoving = false;

    public Player(Vector2 pos) {
        position = pos;

        idleTextures = new Texture[4];
        for (int i = 0; i < idleTextures.length; i++) {
            idleTextures[i] = new Texture(String.format("elf_m_idle_anim_f%d.png", i));
        }
        idleAnimation = new Animation<>(0.1f, idleTextures);

        runningTextures = new Texture[4];
        for (int i = 0; i < runningTextures.length; i++) {
            runningTextures[i] = new Texture(String.format("elf_m_run_anim_f%d.png", i));
        }
        runningAnimation = new Animation<>(0.1f, runningTextures);
    }

    public void update(float deltaTime) {
        isMoving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * deltaTime;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * deltaTime;
            isMoving = true;
        }
    }

    public void render(SpriteBatch batch, float stateTime) {
        Texture frame = isMoving ? runningAnimation.getKeyFrame(stateTime, true) : idleAnimation.getKeyFrame(stateTime, true);
        batch.draw(frame, position.x, position.y);
    }

    public void dispose() {
        for (Texture tex : idleTextures) {
            tex.dispose();
        }

        for (Texture var : runningTextures) {
            var.dispose();
        }
    }
}
