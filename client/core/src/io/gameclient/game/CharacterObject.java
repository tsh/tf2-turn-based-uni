package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.List;
import java.util.Random;

public abstract class CharacterObject extends GameObject {
    public Player player;

    Preferences prefs;
    Random random;

    public CharacterObject(Player player){
        this.player = player;
        prefs = Gdx.app.getPreferences("Preferences");
        this.random = new Random();
    }

    public boolean canAttack(CharacterObject target, float distanceToTarget){
        if (this.player.team != target.player.team && distanceToTarget < getMaxAttackDistance()){
            return true;
        } else {
            sayNo();
            return false;
        }
    }

    public boolean canMove(float distance){
        return distance < getMaxMoveDistance();
    }


    abstract public void sayNo();
    abstract public void sayMove();
    abstract public void sayKill();
    abstract public float getMaxAttackDistance();
    abstract public float getMaxMoveDistance();

    public Sound getRandomSound(List<Sound> soundsArray){
        int randomIndex = this.random.nextInt(soundsArray.size());
        return soundsArray.get(randomIndex);
    }

    public Music getRandomMusic(List<Music> musicArray){
        int randomIndex = this.random.nextInt(musicArray.size());
        return musicArray.get(randomIndex);
    }

}
