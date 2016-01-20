package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by tsh on 20-Jan-16.
 */
public class EndGameScreen implements Screen {
    Texture victoryLogo;
    private Stage stage;
    Tf2Client game;
    Integer counter;
    Music backgroundMusic;

    public EndGameScreen(Team victoriousTeam, Tf2Client gam){
        this.game = gam;
        stage = new Stage(new StretchViewport(854, 480));
        if (victoriousTeam == Team.RED){
            victoryLogo = new Texture(Gdx.files.internal("ui/red_win.png"));
        } else {
            victoryLogo = new Texture(Gdx.files.internal("ui/blue_win.png"));
        }
        counter = 0;

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/background/the-art-of-war.mp3"));
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(this.victoryLogo, 0, 0);
        stage.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        counter += 1;
        System.out.println(counter);
        if (Gdx.input.isTouched() && counter > 60) {
            this.game.setScreen(new MainMenuScreen(this.game));
            dispose();
        }

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
        this.stage.dispose();
        this.victoryLogo.dispose();
        backgroundMusic.dispose();
    }
}
