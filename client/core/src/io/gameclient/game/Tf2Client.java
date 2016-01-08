package io.gameclient.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;




public class Tf2Client extends ApplicationAdapter {
	SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    World world;
    int[][] map;
    Demoman dm;


	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        this.world = new World();
        this.map = world.getMap();
        this.dm = new Demoman();
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        Texture t = new Texture("tilemap.png");
        batch.draw(t, 0,0);
//        drawMap(batch);
        dm.draw(batch);
        batch.end();
	}


    public void drawMap(SpriteBatch batch){
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                shapeRenderer.begin(ShapeType.Filled);
                if (map[i][j] == 0) {
                    shapeRenderer.setColor(Color.BLACK);
                } else{
                    shapeRenderer.setColor(Color.FOREST);
                }
                shapeRenderer.rect(50*j, 50*i, 50, 50);
                shapeRenderer.end();
            }
        }
    }


}
