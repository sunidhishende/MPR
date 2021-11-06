package com.sunidhishende.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sunidhishende.game.Scenes.Hud;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question {
    private String correctanswer;
    private String question;
    private ArrayList<String> options;


    public Question(String question1, String option01, String option02, String option03, String option04, String correctanswer)
    {
        this.question= question1;
        this.correctanswer=correctanswer;
        options= new ArrayList<String>();
        options.add(option01);
        options.add(option02);
        options.add(option03);
        options.add(option04);

    }

    public String getCorrectanswer() {
        return correctanswer;
    }


    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
