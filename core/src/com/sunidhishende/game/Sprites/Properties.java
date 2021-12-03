package com.sunidhishende.game.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;
import com.sunidhishende.game.Screens.propertiesText;

import java.awt.Window;

public class Properties extends InteractiveTileObject{
    private TiledMapTileSet tileSet;
    private final int BLANK_COIN=91;
    public static int prophit=0;
    private MprGame game;

    public Properties(World world, TiledMap map, Rectangle bounds, MprGame game)
    {
        super(world, map, bounds);
        this.game=game;
        tileSet=map.getTileSets().getTileSet("mario");
        fixture.setUserData(this);
        setCategoryFilter(MprGame.PROPERTIES_BIT);

    }



    @Override
    public void onHeadHit() {
        Gdx.app.log("Properties", "Collision");
        Gdx.app.log("Hud", String.format("%d",Hud.getScore()));
        setCategoryFilter(MprGame.DESTROYED_BIT);
        getCell().setTile(null);
        propertiesText.prop.setText(propertiesText.stack.pop());
        Hud.addScore(200);
        game.setScreen(game.propertiesText);


    }

}

