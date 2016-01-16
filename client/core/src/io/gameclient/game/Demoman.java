package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Demoman extends CharacterObject{
    Texture texture;
    Sound noSound;

    public Demoman(Team team) {
        super(team);
        if (team == Team.BLUE) {
            this.texture = new Texture(Gdx.files.internal("characters/blue_demo_alfa.png"));
        } else {
            this.texture = new Texture(Gdx.files.internal("characters/red_demo_alfa.png"));
        }
        // "NO" sound
        switch (prefs.getInteger("lang")) {
            case Langs.EN:
                this.noSound = Gdx.audio.newSound(Gdx.files.internal("audio/demoman/Demoman_no01.wav"));
                break;
            case Langs.FR:
                this.noSound = Gdx.audio.newSound(Gdx.files.internal("audio/demoman/Demoman_no01_fr.wav"));
                break;
            case Langs.DE:
                this.noSound = Gdx.audio.newSound(Gdx.files.internal("audio/demoman/Demoman_no01_de.wav"));
                break;
        }
    }


    public void render(SpriteBatch batch, float x, float y){
        super.render(batch, x, y);
        batch.draw(this.texture, x, y);
    }

    public void sayNo(){
        noSound.play();
    }

}
