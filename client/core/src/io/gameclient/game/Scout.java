package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scout extends CharacterObject {
    Texture texture;
    public int[][] movePattern = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
    };
    List<Sound> noSounds = new ArrayList<Sound>();

    public Scout(Player player){
        super(player);
        if (player.team == Team.BLUE) {this.texture = new Texture(Gdx.files.internal("characters/blue_scout_alfa.png"));}
        else {this.texture = new Texture(Gdx.files.internal("characters/red_scout_alfa.png"));}

        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/scout/no/Scout_jeers11.wav")));
        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/scout/no/Scout_jeers12.wav")));
        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/scout/no/Scout_no01.wav")));
        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/scout/no/Scout_no03.wav")));
    }

    public float getMaxAttackDistance(){return 1.6f;}
    public float getMaxMoveDistance(){return 4.5f;}

    public void render(SpriteBatch batch, float x, float y){
        super.render(batch, x, y);
        batch.draw(this.texture, x, y);
    }

    public void sayNo(){
        this.getRandomSound(this.noSounds).play();
    }

    public void dispose(){
        for (Sound sound: this.noSounds){
            sound.dispose();
        }
    }
}
