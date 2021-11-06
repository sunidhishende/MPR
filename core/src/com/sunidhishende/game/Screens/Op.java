package com.sunidhishende.game.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

public class Op {
    private Label question;
    private Array<Button> buttons;
    private Array<TextButton.TextButtonStyle> tbstyles;
    private Question q;

    public Op(Question q)
    {
        this.q=q;
        Label.LabelStyle font= new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        question= new Label(q.getQuestion(), font);
        tbstyles= new Array<TextButton.TextButtonStyle>();
        buttons= new Array<Button>();

        for(int x=0; x<4; x++)
        {
            TextButton.TextButtonStyle tbs= new TextButton.TextButtonStyle();
            tbs.font= new BitmapFont();
            tbs.fontColor= Color.WHITE;
            tbstyles.add(tbs);
            Button option= new TextButton(q.getOptions().get(x), tbstyles.get(x));
            buttons.add(option);
        }
    }

    public Array<Button> getButtons() {
        return buttons;
    }

    public Array<TextButton.TextButtonStyle> getTbstyles() {
        return tbstyles;
    }

    public Label getQuestion() {
        return question;
    }

    public Question getQ() {
        return q;
    }
}
