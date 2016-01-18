package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class EmptyGameObject extends GameObject{
    public void render(SpriteBatch batch, float x, float y){
        super.renderSelection(batch, x, y);
    }
}
