package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Map {
    public final Integer TILE_WIDTH = 100;
    public final Integer TILE_HEIGHT = 100;

    public final Integer DIRT = 0;

    int[][] map;
    Texture dirt;
    Texture white;

    public Map(){
        this.map = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0,},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        this.dirt = new Texture(Gdx.files.internal("tiles/dirt_1.png"));
        this.white = new Texture(Gdx.files.internal("tiles/white_100_100.png"));
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                if (map[i][j] == this.DIRT) {
                    batch.draw(dirt, i * this.TILE_WIDTH, j * this.TILE_HEIGHT);
                }
                else {
                    batch.draw(white, i * this.TILE_WIDTH, j * this.TILE_HEIGHT);
                }
            }
        }
    }
}
