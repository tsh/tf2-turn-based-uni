package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import  java.util.Random;

enum Team { RED, BLUE }

public class Game {
    Player redPlayer;
    Player bluePlayer;
    Player currentPlayer;
    Boolean canPlayerMove;
    GameObjectsManager gameObjectsManager;

    public Game(){
        this.redPlayer = new Player(Team.RED);
        this.bluePlayer = new Player(Team.BLUE);
        this.currentPlayer = this.bluePlayer;
        this.canPlayerMove = true;
        this.gameObjectsManager = new GameObjectsManager(this);
    }

    public void endTurn(){
        // switch players
        if (this.currentPlayer == this.redPlayer){
            this.currentPlayer = this.bluePlayer;
        } else {
            this.currentPlayer = this.redPlayer;
        }
        this.canPlayerMove = true;
        System.out.println(currentPlayer);
    }

    public void performAction(GameObject selectedObject, GameObject clickedObject){
        // move object to empty location
        if ((clickedObject instanceof EmptyGameObject) && (selectedObject != null) && !(selectedObject instanceof EmptyGameObject)){
            this.gameObjectsManager.moveObject(selectedObject, clickedObject);
        }
    }

    public void inputReceived(float worldX, float worldY){
        this.gameObjectsManager.inputReceived(worldX, worldY);
    }

    public void render(SpriteBatch batch){
        this.gameObjectsManager.render(batch);
    }
}
