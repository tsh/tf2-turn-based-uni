package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public abstract class CharacterObject extends GameObject {
    public Player player;
    Preferences prefs;

    public CharacterObject(Player player){
        this.player = player;
        prefs = Gdx.app.getPreferences("Preferences");
    }

    public boolean canAttack(CharacterObject target){
        if (this.player.team != target.player.team){
            return true;
        } else {
            sayNo();
            return false;
        }
    }

    abstract public void sayNo();
}
