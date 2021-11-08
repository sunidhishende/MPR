package com.sunidhishende.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;

public class Properties extends InteractiveTileObject{
    private TiledMapTileSet tileSet;
    private final int BLANK_COIN=91;

    public Properties(World world, TiledMap map, Rectangle bounds)
    {
        super(world, map, bounds);
        tileSet=map.getTileSets().getTileSet("mario");
        fixture.setUserData(this);
        setCategoryFilter(MprGame.PROPERTIES_BIT);

    }



    @Override
    public void onHeadHit() {
        Gdx.app.log("Properties", "Collision");
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(200);


    }

}

