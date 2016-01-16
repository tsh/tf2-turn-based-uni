package io.gameclient.game;

public class Player {
    public Team team;
    public boolean canPerformAction;

    public Player(Team team, boolean canPerformAction){
        this.canPerformAction = canPerformAction;
        this.team = team;
    }
}
