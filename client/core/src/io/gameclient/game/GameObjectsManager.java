package io.gameclient.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameObjectsManager {
    public final Integer TILE_WIDTH = 100;
    public final Integer TILE_HEIGHT = 100;

    private GameObject currentlySelectedObject = null;
    private MyGame game;

    GameObject[][] gameObjectsMap;

    public GameObjectsManager(MyGame game){
        this.game = game;
        this.gameObjectsMap = new GameObject[][]{
                {new Demoman(game.bluePlayer), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new Scout(game.redPlayer)},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(),},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new Scout(game.bluePlayer), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new Demoman(game.redPlayer)}
        };
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                GameObject go = gameObjectsMap[i][j];
                go.render(batch, i * TILE_WIDTH, j * TILE_HEIGHT);
            }
        }
    }

    public void inputReceived(float worldX, float worldY){
        int row = (int)Math.floor(worldX / TILE_WIDTH);
        int column = (int)Math.floor(worldY / TILE_HEIGHT);
        GameObject clickedObject = this.gameObjectsMap[row][column];
        // set selection
        if (currentlySelectedObject == null || currentlySelectedObject instanceof EmptyGameObject){
            clickedObject.setSelected();
            // highlight action pattern
            if (clickedObject instanceof CharacterObject){ highlightPattern((CharacterObject) clickedObject);}
            currentlySelectedObject = clickedObject;
        }
        // unset selection
        else if (currentlySelectedObject == clickedObject){
            clickedObject.toggleSelected();
            if (clickedObject instanceof CharacterObject){ undoHighlightPattern();}
            currentlySelectedObject = null;
        }

        this.game.performAction(currentlySelectedObject, clickedObject);
    }

    public void moveObject(GameObject movedObject, int destinationRow, int destinationColumn){
        int[] movedObjectPosition = getObjectPosition(movedObject);
        gameObjectsMap[destinationRow][destinationColumn] = movedObject;
        gameObjectsMap[movedObjectPosition[0]][movedObjectPosition[1]] = new EmptyGameObject();
    }

    public void moveObject(GameObject movedObject, GameObject destinationObject){
        int[] movedObjectPosition = getObjectPosition(movedObject);
        int[] destinationPosition = getObjectPosition(destinationObject);
        gameObjectsMap[destinationPosition[0]][destinationPosition[1]] = movedObject;
        gameObjectsMap[movedObjectPosition[0]][movedObjectPosition[1]] = new EmptyGameObject();
    }

    public void attackTarget(GameObject attacker, CharacterObject target){
        removeCharacter(target);
    }

    private void removeCharacter(CharacterObject characterObject){
        int[] position = getObjectPosition(characterObject);
        gameObjectsMap[position[0]][position[1]] = new EmptyGameObject();

    }

    public int[] getObjectPosition(GameObject object){
        int[] position = new int[2];
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                if (gameObjectsMap[i][j] == object){
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        throw new IllegalArgumentException("Object not found in GameObjectArray");
    }

    public Vector2 getObjectPositionVector(GameObject object){
        Vector2 position = new Vector2(0.0f, 0.0f);
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                if (gameObjectsMap[i][j] == object){
                    position.x = i;
                    position.y = j;
                    return position;
                }
            }
        }
        throw new IllegalArgumentException("Object not found in GameObjectArray");
    }

    public int getLiveTeamCharacterCount(Team team){
        int count = 0;
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                if (gameObjectsMap[i][j] instanceof CharacterObject){
                    CharacterObject chr = (CharacterObject)gameObjectsMap[i][j];
                    if (chr.player.team == team) {count += 1;}
                }
            }
        }
        return count;
    }

    public void highlightPattern(CharacterObject origin) {
        for (int i = 0; i < gameObjectsMap.length; i++) {
            for (int j = 0; j < gameObjectsMap[i].length; j++) {
                float distance = getDistanceBetweenObjects(origin, gameObjectsMap[i][j]);
                System.out.println(distance + " " + origin.getMaxMoveDistance() + " " + origin.getMaxAttackDistance());
                if (distance < origin.getMaxMoveDistance()) {
                    gameObjectsMap[i][j].setHilightedMoveTrue();
                }
                if (distance < origin.getMaxAttackDistance()){
                    gameObjectsMap[i][j].setHighlightedAttackTrue();
                }
            }
        }
    };

    public void undoHighlightPattern(){
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                gameObjectsMap[i][j].setHilightedMoveFalse();
                gameObjectsMap[i][j].setHighlightedAttackFalse();
            }
        }
    }

    public float getDistanceBetweenObjects(GameObject origin, GameObject dest){
        Vector2 originPosition = getObjectPositionVector(origin);
        Vector2 destPosition = getObjectPositionVector(dest);
        return originPosition.dst(destPosition);
    }

    public void dispose(){
        for (int i = 0; i < gameObjectsMap.length; i++){
            for (int j = 0; j < gameObjectsMap[i].length; j++){
                gameObjectsMap[i][j].dispose();
            }
        }
    }

    public void unselectObjects(){
        this.currentlySelectedObject.setUnselected();
        this.currentlySelectedObject = null;
    }
}
