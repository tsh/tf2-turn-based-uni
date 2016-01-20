package io.gameclient.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;


public class Tf2Client extends Game {
	SpriteBatch batch;
    BitmapFont font;
    public Preferences prefs;

	@Override
	public void create () {
        font = new BitmapFont();
        batch = new SpriteBatch();
        prefs = Gdx.app.getPreferences("Preferences");
        if (!prefs.contains("lang")) {
            prefs.putInteger("lang", Langs.EN);
        }
        this.setScreen(new MainMenuScreen(this));
        //this.setScreen(new GameScreen(this));
    }

	@Override
	public void render() {
        super.render();
	}

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}