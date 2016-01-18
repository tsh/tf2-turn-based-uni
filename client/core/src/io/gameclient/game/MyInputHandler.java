package io.gameclient.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


public class MyInputHandler extends InputAdapter {
    OrthographicCamera camera;
    MyGame game;

    public MyInputHandler(OrthographicCamera camera, MyGame game){
        super();
        this.game = game;
        this.camera = camera;
    }
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Vector3 touch = new Vector3(x, y, 0);
        camera.unproject(touch);
        if (touch.x <= 600 && touch.y <= 500){
            this.game.inputReceived(touch.x, touch.y);
        }

        return true; // return true to indicate the event was handled
    }
}
