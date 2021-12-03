package com.sunidhishende.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;

import java.util.Stack;

public class propertiesText implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Button close;
    private final MprGame game;
    public static int isclosed=0;
    public static Label prop;
    public static Stack<String> stack;



    public propertiesText(final MprGame game)
    {
        this.game=game;
        viewport= new FitViewport(MprGame.V_WIDTH, MprGame.V_HEIGHT, new OrthographicCamera());
        stage=new Stage(viewport, ((MprGame)game).batch);
        stack= new Stack<String>();
        Gdx.input.setInputProcessor(stage);

        final Label.LabelStyle font= new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        TextButton.TextButtonStyle tbstyle= new TextButton.TextButtonStyle();
        tbstyle.font= new BitmapFont();
        tbstyle.fontColor= Color.WHITE;
        close= new TextButton("close",tbstyle);
        close.addListener(new InputListener()
        {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)|| Gdx.input.isTouched()) {
                    return true;
                }
                return false;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.play);
            }

        });

        addProp();
        prop= new Label( "", font);
        Gdx.app.log("stack", stack.peek());
        addactors();
    }



    public void addactors()
    {
        stage.addActor(prop);
        stage.addActor(close);
        prop.setPosition(145, 100);
        close.setPosition(145, 60);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    public void addProp() {
        stack.push("Hydrogen (H) is colourless");
        stack.push("Hydrogen is odourless");
        stack.push("Hydrogen is nefkvs");
        stack.push("Hydrogen is clsm;a;ac");
        stack.push("Hydrogen is  szldcn;z");
        stack.push("Hydrogen is LSNCzcmz");
        stack.push("Hydrogen is dnv;zv");
        stack.push("Hydrogen is oldvzv");
        stack.push("Hydrogen is lzc zv ");
        stack.push("Hydrogen is vdjksznv ");
        stack.push("Hydrogen is zkjdcvnzv");
        stack.push("Hydrogen is zkdcvdvn");
        stack.push("Hydrogen is dvzsnvvzv");



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
        stage.dispose();

    }
}

