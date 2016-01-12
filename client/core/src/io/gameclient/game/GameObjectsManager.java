package io.gameclient.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectsManager {
    public final Integer TILE_WIDTH = 100;
    public final Integer TILE_HEIGHT = 100;

    private GameObject currentlySelectedObject = null;
    private Game game;

    GameObject[][] gameObjectsMap;

    public GameObjectsManager(Game game){
        this.game = game;
        this.gameObjectsMap = new GameObject[][]{
                {new Demoman(Team.BLUE), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new Demoman(Team.RED)},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(),},
                {new EmptyGameObject(), new Scout(Team.BLUE), new EmptyGameObject(), new EmptyGameObject(), new Scout(Team.RED)},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()}
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
            currentlySelectedObject = clickedObject;
        }
        // unset selection
        else if (currentlySelectedObject == clickedObject){
            clickedObject.toggleSelected();
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
}
