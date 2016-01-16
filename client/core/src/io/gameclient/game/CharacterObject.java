package io.gameclient.game;


public abstract class CharacterObject extends GameObject {
    public Team team;

    public CharacterObject(Team team){
        this.team = team;
    }

    public boolean canAttack(CharacterObject target){
        return (this.team != target.team);
    };
}
