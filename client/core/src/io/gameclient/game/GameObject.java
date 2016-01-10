package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameObject {
    public boolean isSelected = false;

    public GameObject(){
    }

    public void render(SpriteBatch batch, float x, float y){
        if (this.isSelected){System.out.println("Selected");}
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
