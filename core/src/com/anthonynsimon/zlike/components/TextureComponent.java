package com.anthonynsimon.zlike.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
    public TextureRegion texture;

    public TextureComponent(TextureRegion texture) {
        this.texture = texture;
    }
}
