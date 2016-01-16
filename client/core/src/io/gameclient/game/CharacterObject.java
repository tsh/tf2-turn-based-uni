package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public abstract class CharacterObject extends GameObject {
    public Team team;
    Preferences prefs;

    public CharacterObject(Team team){
        this.team = team;
        prefs = Gdx.app.getPreferences("Preferences");
    }

    public boolean canAttack(CharacterObject target){
        if (this.team != target.team){
            return true;
        } else {
            sayNo();
            return false;
        }
    };

    abstract public void sayNo();
}
