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
    Map map;
    Demoman dm;


	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        this.world = new World();
        this.map = new Map();
        this.dm = new Demoman();
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        this.map.render(batch);
        dm.draw(batch);
        batch.end();
	}


}
