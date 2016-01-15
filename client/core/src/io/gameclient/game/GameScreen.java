package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
    final Tf2Client game;

    ShapeRenderer shapeRenderer;
    World world;
    Map map;
    MyGame tf2Game;
    private OrthographicCamera camera;

    public GameScreen(final Tf2Client gam){
        game = gam;

        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 700, 500);
        this.world = new World();
        this.map = new Map();
        this.tf2Game = new MyGame();
        Gdx.input.setInputProcessor(new MyInputHandler(camera, this.tf2Game));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        this.map.render(game.batch);
        this.tf2Game.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
