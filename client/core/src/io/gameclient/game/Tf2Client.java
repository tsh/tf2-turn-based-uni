package io.gameclient.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    GameObjectsManager gameObjectsManager;
    private OrthographicCamera camera;


	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 500);
        this.world = new World();
        this.map = new Map();
        this.gameObjectsManager = new GameObjectsManager();
        Gdx.input.setInputProcessor(new MyInputHandler(camera, gameObjectsManager));
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        this.map.render(batch);
        this.gameObjectsManager.render(batch);
        batch.end();
	}


}
