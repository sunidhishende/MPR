package com.sunidhishende.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;

import javax.print.attribute.standard.MediaPrintableArea;

public class McqScreen implements Screen {
    private Hud hud;
    private Viewport viewport;
    private Stage stage;
    private static int i=0;
    private Button next;
    private Button close;
    private static Op o;
    private Array<Question> questionArray;

    private final MprGame game;
    private int isMarked=0;


    public McqScreen(final MprGame game)
    {
        this.game=game;
        hud= new Hud(game.batch);
        viewport= new FitViewport(MprGame.V_WIDTH, MprGame.V_HEIGHT, new OrthographicCamera());
        stage=new Stage(viewport, ((MprGame)game).batch);
        Gdx.input.setInputProcessor(stage);
        questionArray= new Array<Question>();


        TextButton.TextButtonStyle tbstyle= new TextButton.TextButtonStyle();
        tbstyle.font= new BitmapFont();
        tbstyle.fontColor= Color.WHITE;
        next= new TextButton("Next", tbstyle );

        addQuestions();
        o= new Op(questionArray.get(i));
        addactors();

            next.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)|| Gdx.input.isTouched()) {
                        return true;
                    }
                    return false;

                }


                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(isMarked==1) {
                        o.getQuestion().remove();
                        for (int i = 0; i < 4; i++) {
                            o.getButtons().get(i).remove();
                        }
                        i++;
                        o = new Op(questionArray.get(i));
                        addactors();
                        isMarked=0;
                        Gdx.app.log("i", String.format("%d", i));
                        Gdx.app.log("i", String.format("Touch up"));
                    }


                }
            });



    }

    public void update(float dt){
        for (int x = 0; x < 4; x++) {
            if (o.getButtons().get(x).isChecked()) {

                if (o.getQ().getOptions().get(x) == o.getQ().getCorrectanswer()) {
                    o.getTbstyles().get(x).fontColor = Color.GREEN;
                    for(int i=0; i<4; i++)
                    {o.getButtons().get(i).setDisabled(true);}
                } else if (o.getQ().getOptions().get(x) != o.getQ().getCorrectanswer()) {
                    o.getTbstyles().get(x).fontColor = Color.RED;
                    if(!o.getButtons().get(x).isDisabled())
                    {Hud.decreaseHealth(20);}
                    for(int i=0; i<4; i++)
                    {o.getButtons().get(i).setDisabled(true);}

                }
                isMarked=1;
            }

        }
    }

    public void addQuestions()
    {
        Question q1= new Question("What is the answer?", "32", "49", "65", "42", "42");
        questionArray.add(q1);
        Question q2= new Question("Coolest thing ever?", "human", "alien", "improbability drive", "rat", "rat");
        questionArray.add(q2);

    }

    public void addactors()
    {
        stage.addActor(o.getQuestion());
        stage.addActor(o.getButtons().get(0));
        stage.addActor(o.getButtons().get(1));
        stage.addActor(o.getButtons().get(2));
        stage.addActor(o.getButtons().get(3));
        stage.addActor(next);

        o.getQuestion().setPosition(145,150);
        o.getButtons().get(0).setPosition(145,125 );
        o.getButtons().get(1).setPosition(145,100 );
        o.getButtons().get(2).setPosition(145,75 );
        o.getButtons().get(3).setPosition(145,55 );
        next.setPosition(145, 25);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        hud.stage.draw();
        stage.draw();
        if(Hud.gethealth()<=0)
        {
            game.setScreen(new GameOverScreen(game));
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
        stage.dispose();

    }
}
