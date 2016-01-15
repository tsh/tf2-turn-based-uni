package io.gameclient.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Tf2Client extends ApplicationAdapter {
	SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    World world;
    Map map;
    MyGame game;
    private OrthographicCamera camera;


	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 500);
        this.world = new World();
        this.map = new Map();
        this.game = new MyGame();
        Gdx.input.setInputProcessor(new MyInputHandler(camera, this.game));
    }

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        this.map.render(batch);
        this.game.render(batch);
        batch.end();
	}


}
