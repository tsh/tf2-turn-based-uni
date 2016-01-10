package io.gameclient.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectsManager {
    public final Integer TILE_WIDTH = 100;
    public final Integer TILE_HEIGHT = 100;

    public final Integer EMPTY = 0;
    public final Integer SCOUT = 1;
    public final Integer DEMOMAN = 2;

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
        int row = (int)Math.floor(worldX/100);
        int column = (int)Math.floor(worldY/100);
        GameObject go = this.gameObjectsMap[row][column];
        go.toggleSelected();
    }
}
