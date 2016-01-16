package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scout extends CharacterObject {
    Texture texture;
    public int[][] movePattern = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
    };

    public Scout(Player player){
        super(player);
        if (player.team == Team.BLUE) {this.texture = new Texture(Gdx.files.internal("characters/blue_scout_alfa.png"));}
        else {this.texture = new Texture(Gdx.files.internal("characters/red_scout_alfa.png"));}
    }

    public float getMaxAttackDistance(){return 1.6f;}
    public float getMaxMoveDistance(){return 4.5f;}

    public void render(SpriteBatch batch, float x, float y){
        super.render(batch, x, y);
        batch.draw(this.texture, x, y);
    }

    public void sayNo(){
        System.out.print("NO!");
    }
}
