package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scout extends GameObject {
    Texture texture;

    public Scout(Team team){
        if (team == Team.BLUE) {this.texture = new Texture(Gdx.files.internal("characters/blue_scout_alfa.png"));}
        else {this.texture = new Texture(Gdx.files.internal("characters/red_scout_alfa.png"));}

    }

    public void render(SpriteBatch batch, float x, float y){
        super.render(batch, x, y);
        batch.draw(this.texture, x, y);
    }
}
