package com.anthonynsimon.zlike;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

public class Player {
    final Vector2 position;
    private final Vector2 velocity;
    private final float movementSpeed = 100f;
    private boolean isFacingLeft = false;

    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> runningAnimation;

    public Player(Vector2 pos, TextureAtlas atlas) {
        position = pos;
        velocity = new Vector2(0f, 0f);

        idleAnimation = new Animation<>(
                0.1f,
                atlas.findRegions("elf_m_idle_anim"),
                Animation.PlayMode.LOOP
        );

        runningAnimation = new Animation<>(
                0.1f,
                atlas.findRegions("elf_m_run_anim"),
                Animation.PlayMode.LOOP
        );
    }

    public void update(float deltaTime) {
        velocity.setZero();

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y = -movementSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y = movementSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -movementSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = movementSpeed * deltaTime;
        }

        if (velocity.x < 0) isFacingLeft = true;
        else if (velocity.x > 0) isFacingLeft = false;

        position.add(velocity);
    }

    public void render(Batch batch, float stateTime) {
        Animation<TextureRegion> anim = velocity.isZero() ? idleAnimation : runningAnimation;
        TextureRegion frame = anim.getKeyFrame(stateTime, true);

        if (isFacingLeft) {
            float width = frame.getRegionWidth();
            float height = frame.getRegionHeight();
            batch.draw(frame, position.x + width, position.y, -width, height);
        } else {
            batch.draw(frame, position.x, position.y, frame.getRegionWidth(), frame.getRegionHeight());
        }
    }
}
