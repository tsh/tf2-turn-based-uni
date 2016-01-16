package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import  java.util.Random;

enum Team { RED, BLUE }

public class MyGame {
    Player redPlayer;
    Player bluePlayer;
    Player currentPlayer;
    GameObjectsManager gameObjectsManager;

    public MyGame(){
        this.redPlayer = new Player(Team.RED, false);
        this.bluePlayer = new Player(Team.BLUE, true);
        this.currentPlayer = this.bluePlayer;
        this.gameObjectsManager = new GameObjectsManager(this);
    }

    public void endTurn(){
        // switch players
        if (this.currentPlayer == this.redPlayer){
            this.currentPlayer = this.bluePlayer;
        } else {
            this.currentPlayer = this.redPlayer;
        }
        this.currentPlayer.canPerformAction = true;
        System.out.println(currentPlayer.team + " " + currentPlayer.canPerformAction);
    }


    public void performAction(GameObject selectedObject, GameObject clickedObject){
        // Chars only
        if (!(selectedObject instanceof CharacterObject)){
            return;
        }
        if (!canPerformAction((CharacterObject)selectedObject)){
            return;
        }
        /* Parse actions */
        // move
        if ((clickedObject instanceof EmptyGameObject)){
            this.gameObjectsManager.moveObject(selectedObject, clickedObject);
            endTurn();
        }
        // attack
        else if ((clickedObject instanceof CharacterObject) && !(selectedObject == clickedObject)){
            if (canAttack((CharacterObject)selectedObject, (CharacterObject)clickedObject)) {
                this.gameObjectsManager.attackTarget(selectedObject, (CharacterObject) clickedObject);
                endTurn();
            }
        }
    }

    public boolean canPerformAction(CharacterObject character){
        return character.player == currentPlayer && currentPlayer.canPerformAction;
    }


    public boolean canAttack(CharacterObject attacker, CharacterObject target){
        if (! canPerformAction(attacker)){
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
