package com.sunidhishende.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunidhishende.game.MprGame;

public class Hud implements Disposable {
    public Stage stage;
    public Viewport viewport;

    private static Integer score;
    private static Integer health=100;
    private static Label scoreLabel;
    private static Label healthLabel;
    private Label healthname;
    private Label levelLabel;
    private Label worldLabel;
    private Label mprLabel;



    public Hud(SpriteBatch sb)
    {

        score=0;
        viewport= new FitViewport(MprGame.V_WIDTH*2, MprGame.V_HEIGHT*2, new OrthographicCamera());
        stage= new Stage(viewport, sb);
        Table table= new Table();
        table.top();
        table.setFillParent(true);
        scoreLabel=new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel=new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel= new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mprLabel=new Label("MPR", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(mprLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        healthname= new Label("Health:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel= new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        stage.addActor(healthLabel);
        stage.addActor(healthname);
        stage.addActor(table);
        healthLabel.setPosition(75, 10);
        healthname.setPosition(25, 10);


    }

    public static void decreaseHealth(int value)
    {
        health-=value;
        healthLabel.setText(String.format("%03d", health));
    }

    public static int gethealth()
    {
        return health;
    }

    public static void addScore(int value)
    {
        score+=value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
