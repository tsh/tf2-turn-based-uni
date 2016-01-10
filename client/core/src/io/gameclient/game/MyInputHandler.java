package io.gameclient.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


public class MyInputHandler extends InputAdapter {
    OrthographicCamera camera;
    GameObjectsManager gameObjectsManager;

    public MyInputHandler(OrthographicCamera camera, GameObjectsManager gmo){
        super();
        this.gameObjectsManager = gmo;
        this.camera = camera;
    }
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        Vector3 touch = new Vector3(x, y, 0);
        camera.unproject(touch);
        if (touch.x <= 500 && touch.y <= 500){
            gameObjectsManager.inputReceived(touch.x, touch.y);
        }

        return true; // return true to indicate the event was handled
    }
}
