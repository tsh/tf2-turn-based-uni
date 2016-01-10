package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameObject {
    public boolean isSelected = false;
    private Texture selectionTexture = new Texture(Gdx.files.internal("general/selector.png"));

    public GameObject(){
    }

    public void render(SpriteBatch batch, float x, float y){
        if (this.isSelected){batch.draw(this.selectionTexture, x, y);}
    }

    public void setSelected(){
        this.isSelected = true;
    }

    public void setUnselected(){
        this.isSelected = false;
    }

    public void toggleSelected(){
        this.isSelected = !this.isSelected;
    }
}
