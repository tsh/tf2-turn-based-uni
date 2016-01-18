package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameObject {
    public boolean isSelected = false;
    public boolean isHilightedMove = false;
    public boolean isHilightedAttack = false;
    private Texture selectionTexture;
    private Texture hilightMoveTexture;

    public GameObject(){
        this.selectionTexture = new Texture(Gdx.files.internal("general/selector.png"));
        this.hilightMoveTexture = new Texture(Gdx.files.internal("general/square_transperent16.png"));
    }

    public void render(SpriteBatch batch, float x, float y){
        if (this.isSelected){batch.draw(this.selectionTexture, x, y);}
        renderSelection(batch, x, y);
    }

    public void renderSelection(SpriteBatch batch, float x, float y) {
        if (this.isHilightedMove) {batch.draw(this.hilightMoveTexture, x, y);}
    };

    public void setSelected(){
        this.isSelected = true;
    }

    public void setUnselected(){
        this.isSelected = false;
    }

    public void toggleSelected(){
        this.isSelected = !this.isSelected;
    }


    public void setHilightedMoveTrue(){this.isHilightedMove = true;}
    public void setHilightedMoveFalse(){this.isHilightedMove = false;}
}
