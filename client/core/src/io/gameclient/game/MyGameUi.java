package io.gameclient.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGameUi {
    Texture rightPanel;
    Texture bluePanel;
    Texture redPanel;

    Team activeTeam;

    public MyGameUi(){
        bluePanel = new Texture(Gdx.files.internal("ui/blue_right_side.png"));
        redPanel = new Texture(Gdx.files.internal("ui/red_right_side.png"));
        rightPanel = bluePanel;
        activeTeam = Team.BLUE;
    }

    public void setActiveTeam(Team team){
        this.activeTeam = team;
        if (this.activeTeam == Team.BLUE){
            this.rightPanel = bluePanel;
        } else if (this.activeTeam == Team.RED){
            this.rightPanel = redPanel;
        }
    };


    public void render(SpriteBatch batch){
        batch.draw(rightPanel, 600, 0);
    }

    public void dispose(){
        this.bluePanel.dispose();
        this.redPanel.dispose();
    }
}
