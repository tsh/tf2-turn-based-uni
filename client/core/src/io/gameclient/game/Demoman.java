package io.gameclient.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Demoman extends GameObject{
    Texture texture;

    public Demoman(){
        this.texture = new Texture("characters.png");
    }

    public void draw(SpriteBatch batch){
        batch.draw(this.texture, 10,100);
    }
}