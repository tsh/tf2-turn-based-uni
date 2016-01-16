package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import  java.util.Random;

enum Team { RED, BLUE }

public class MyGame {
    Player redPlayer;
    Player bluePlayer;
    Player currentPlayer;
    Boolean canPlayerMove;
    GameObjectsManager gameObjectsManager;

    public MyGame(){
        this.redPlayer = new Player(Team.RED, false);
        this.bluePlayer = new Player(Team.BLUE, true);
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
        // Parse actions
        // move object to empty location
        if ((selectedObject instanceof CharacterObject) && (clickedObject instanceof EmptyGameObject)){
            this.gameObjectsManager.moveObject(selectedObject, clickedObject);
        }

        // attack
        if ((selectedObject instanceof CharacterObject) &&  (clickedObject instanceof CharacterObject) && !(selectedObject == clickedObject)){
            if (canAttack((CharacterObject)selectedObject, (CharacterObject)clickedObject)) {
                this.gameObjectsManager.attackTarget(selectedObject, (CharacterObject) clickedObject);
            }
        }
    }


    public boolean canAttack(CharacterObject attacker, CharacterObject target){
        if (!currentPlayer.canPerformAction){
            return false;
        }
        if (attacker.canAttack(target)) {
            return true;
        }
        return false;
    }


    public void inputReceived(float worldX, float worldY){
        this.gameObjectsManager.inputReceived(worldX, worldY);
    }


    public void render(SpriteBatch batch){
        this.gameObjectsManager.render(batch);
    }
}
