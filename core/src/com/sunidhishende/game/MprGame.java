package com.sunidhishende.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunidhishende.game.Screens.McqScreen;
import com.sunidhishende.game.Screens.PlayScreen;
import com.sunidhishende.game.Screens.propertiesText;

public class MprGame extends Game {
	public PlayScreen play;
	public propertiesText propertiesText;

	public static final int V_WIDTH=400;
	public static final int V_HEIGHT=208;
	public SpriteBatch batch;
	public static final float PPM= 100;
	public static final short DEFAULT=1;
	public static final short CHARACTER_BIT=2;
	public static final short BRICK_BIT=4;
	public static final short PROPERTIES_BIT=4;
	public static final short MCQ_BIT=4;
	public static final short DESTROYED_BIT=16;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		play= new PlayScreen(this);
		propertiesText = new propertiesText(this);
		setScreen(play);

	}

	@Override
	public void render () {
		super.render();
	}
	

}
