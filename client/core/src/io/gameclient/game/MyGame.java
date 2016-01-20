package io.gameclient.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import  java.util.Random;

enum Team { RED, BLUE }

public class MyGame {
    Player redPlayer;
    Player bluePlayer;
    Player currentPlayer;
    GameObjectsManager gameObjectsManager;
    MyGameUi ui;
    Tf2Client game;
    GameScreen gameScreen;

    public MyGame(Tf2Client gam, GameScreen gs){
        this.game = gam;
        this.gameScreen = gs;
        this.redPlayer = new Player(Team.RED, false);
        this.bluePlayer = new Player(Team.BLUE, true);
        this.currentPlayer = this.bluePlayer;
        this.gameObjectsManager = new GameObjectsManager(this);
        this.ui = new MyGameUi();
    }

    public void endTurn(){
        gameObjectsManager.unselectObjects();
        gameObjectsManager.undoHighlightPattern();
        // switch players
        if (this.currentPlayer == this.redPlayer){
            this.currentPlayer = this.bluePlayer;
        } else {
            this.currentPlayer = this.redPlayer;
        }
        this.currentPlayer.canPerformAction = true;
        this.ui.setActiveTeam(currentPlayer.team);
        System.out.println(currentPlayer.team + " " + currentPlayer.canPerformAction);
        // Check win condition
        int liveBlue = this.gameObjectsManager.getLiveTeamCharacterCount(Team.BLUE);
        int liveRed = this.gameObjectsManager.getLiveTeamCharacterCount(Team.RED);
        if (liveBlue == 0 ){
            this.game.setScreen(new EndGameScreen(Team.RED, this.game));
            dispose();
            gameScreen.dispose();
        }
        if (liveRed == 0 ){
            this.game.setScreen(new EndGameScreen(Team.BLUE, this.game));
            dispose();
            gameScreen.dispose();
        }

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
            if (canMove((CharacterObject)selectedObject, (EmptyGameObject) clickedObject)) {
                this.gameObjectsManager.moveObject(selectedObject, clickedObject);
                ((CharacterObject) selectedObject).sayMove();
                endTurn();
            }
        }
        // attack
        else if ((clickedObject instanceof CharacterObject) && !(selectedObject == clickedObject)){
            if (canAttack((CharacterObject)selectedObject, (CharacterObject)clickedObject)) {
                this.gameObjectsManager.attackTarget(selectedObject, (CharacterObject) clickedObject);
                ((CharacterObject) selectedObject).sayKill();
                endTurn();
            }
        }
    }


    public boolean canPerformAction(CharacterObject character){
        return character.player == currentPlayer && currentPlayer.canPerformAction;
    }


    public boolean canMove(CharacterObject movable, EmptyGameObject destination){
        float distance = this.gameObjectsManager.getDistanceBetweenObjects(movable, destination);
        return movable.canMove(distance);
    };


    public boolean canAttack(CharacterObject attacker, CharacterObject target){
        float distance = this.gameObjectsManager.getDistanceBetweenObjects(attacker, target);
        return attacker.canAttack(target, distance);
    }


    public void inputReceived(float worldX, float worldY){
        this.gameObjectsManager.inputReceived(worldX, worldY);
    }


    public void render(SpriteBatch batch){
        this.gameObjectsManager.render(batch);
        this.ui.render(batch);
    }

    public void dispose(){
        this.ui.dispose();
    }
}
