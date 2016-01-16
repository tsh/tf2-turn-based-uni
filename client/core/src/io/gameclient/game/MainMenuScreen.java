package io.gameclient.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {
    float WIDTH = 800;
    float HEIGHT = 480;

    final Tf2Client game;
    OrthographicCamera camera;

    Music backgroundMusic;
    Texture backgroundTexture;
    Texture logo;

    private Stage stage;
    private Table table;
    Skin skin;

    Actor root;

    public MainMenuScreen(final Tf2Client gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/background/rocket_jump_waltz.mp3"));
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        this.backgroundTexture = new Texture(Gdx.files.internal("ui/2fort_800px.png"));
        this.logo =  new Texture("ui/tf2_logo.png");

        stage = new Stage(new StretchViewport(640, 480));
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Gdx2DPixmap.GDX2D_FORMAT_ALPHA);
        pixmap.fill();
        skin = new Skin();
        skin.add("white", new Texture(pixmap));
        BitmapFont bFont = new BitmapFont();
        BitmapFont.BitmapFontData bfData = bFont.getData();
        bfData.setScale(2);
        skin.add("default", bFont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white");
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        // MENU
        String startGameText = "";
        String quitGameText = "";

        switch (game.prefs.getInteger("lang")){
            case Langs.EN:
                startGameText = "Start Game";
                quitGameText = "Quit";
                break;
            case Langs.FR:
                startGameText = "Démarrer le jeu";
                quitGameText = "Quitter";
                break;
            case Langs.DE:
                startGameText = "Spiel beginnen";
                quitGameText = "Verlassen";
                break;
        }
        final TextButton button = new TextButton(startGameText, skin);
        final TextButton exitButton = new TextButton(quitGameText, skin);
        table.add(button);
        table.row();
        table.add(exitButton).padTop(10);
        table.left().pad(20);
        table.setBounds(75, 30, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        button.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            game.setScreen(new GameScreen(game));
            dispose();
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(this.backgroundTexture, 0, 0);
        stage.getBatch().draw(this.logo, 0, 0);
        stage.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundMusic.dispose();
        logo.dispose();
        backgroundTexture.dispose();
    }
}