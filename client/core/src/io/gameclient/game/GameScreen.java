package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final Tf2Client game;

    World world;
    Map map;
    MyGame tf2Game;
    private OrthographicCamera camera;
    Viewport viewport;
    Music backgroundMusic;

    public GameScreen(final Tf2Client gam){
        game = gam;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        //viewport = new ExtendViewport(w,h,camera);
        //viewport.apply();
        // dev: 480 854
        // opt: 600, 500
        this.world = new World();
        this.map = new Map();
        this.tf2Game = new MyGame(gam, this);
        Gdx.input.setInputProcessor(new MyInputHandler(camera, this.tf2Game));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/background/the-art-of-war.mp3"));
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        this.map.render(game.batch);
        this.tf2Game.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 700; // dev 854
        camera.viewportHeight = 500f;
        //viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
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
        this.backgroundMusic.dispose();
    }
}
