package io.gameclient.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;


public class Tf2Client extends Game {
	SpriteBatch batch;
    BitmapFont font;

	@Override
	public void create () {
        font = new BitmapFont();
        batch = new SpriteBatch();
//        this.setScreen(new MainMenuScreen(this));
        this.setScreen(new GameScreen(this));
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
