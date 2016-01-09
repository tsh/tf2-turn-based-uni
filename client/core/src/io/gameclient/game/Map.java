package io.gameclient.game;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Map {
    int[][] map;
    Texture dirt;

    public Map(){
        this.map = new int[][]{
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 55, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        this.dirt = new Texture("dirt_1.png");
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                batch.draw(dirt, i*10, j*10);
            }
        }
    }
}
