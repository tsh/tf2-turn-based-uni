package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Demoman extends GameObject{
    Texture texture;

    public Demoman(){
        this.texture = new Texture(Gdx.files.internal("characters/blue_demo_alfa.png"));
    }

    public void draw(SpriteBatch batch){
        batch.draw(this.texture, 0,0);
    }
}