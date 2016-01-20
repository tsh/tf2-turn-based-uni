package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Demoman extends CharacterObject{
    Texture texture;
    Sound noSound;

    List<Sound> noSounds = new ArrayList<Sound>();
    List<Sound> moveSounds = new ArrayList<Sound>();
    List<Sound> killSounds = new ArrayList<Sound>();

    public Demoman(Player player) {
        super(player);
        if (player.team == Team.BLUE) {
            this.texture = new Texture(Gdx.files.internal("characters/blue_demo_alfa.png"));
        } else {
            this.texture = new Texture(Gdx.files.internal("characters/red_demo_alfa.png"));
        }

        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/no/Demoman_no01.wav")));
        noSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/no/Demoman_no02.wav")));

        moveSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/move/en_freedom.wav")));
        moveSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/move/en_get_em_boyos.wav")));

        killSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/kill/en_could_be_one.wav")));
        killSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/kill/en_gonna_kill_you.wav")));
        killSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/kill/en_reign_of_blood.wav")));
        killSounds.add(Gdx.audio.newSound(Gdx.files.internal("audio/demo/kill/en_wee_lasses.wav")));

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

    public float getMaxAttackDistance(){return 3.0f;}
    public float getMaxMoveDistance(){return 3.0f;}


    public void render(SpriteBatch batch, float x, float y){
        super.render(batch, x, y);
        batch.draw(this.texture, x, y);
    }

    public void sayNo(){
        this.getRandomSound(this.noSounds).play();
    }

    public void sayMove(){
        this.getRandomSound(this.moveSounds).play();
    }

    public void sayKill(){
        this.getRandomSound(this.killSounds).play();
    }

    public void dispose(){
        this.noSound.dispose();
    }

}
