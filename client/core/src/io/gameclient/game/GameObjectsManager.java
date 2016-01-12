package io.gameclient.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectsManager {
    public final Integer TILE_WIDTH = 100;
    public final Integer TILE_HEIGHT = 100;

    private GameObject currentlySelectedObject = null;

    GameObject[][] gameObjectsMap;

    public GameObjectsManager(){
        this.gameObjectsMap = new GameObject[][]{
                {new Demoman(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
                {new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject(),},
                {new EmptyGameObject(), new Scout(), new EmptyGameObject(), new EmptyGameObject(), new EmptyGameObject()},
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
        int row = (int)Math.floor(worldX / 100);
        int column = (int)Math.floor(worldY / 100);
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
        // move object to empty location
        if ((clickedObject instanceof EmptyGameObject) && (currentlySelectedObject != null) && !(currentlySelectedObject instanceof EmptyGameObject)){
            moveObject(currentlySelectedObject, row, column);
        }

    }

    public void moveObject(GameObject movedObject, int destinationRow, int destinationColumn){
        int[] movedObjectPosition = getObjectPosition(movedObject);
        // move only to empty space
        if (gameObjectsMap[destinationRow][destinationColumn] instanceof EmptyGameObject){
            gameObjectsMap[destinationRow][destinationColumn] = movedObject;
            gameObjectsMap[movedObjectPosition[0]][movedObjectPosition[1]] = new EmptyGameObject();
        }

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
