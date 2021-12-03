package com.sunidhishende.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;

public class Bricks extends InteractiveTileObject{


    public Bricks(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MprGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Bricks", "Collision");
//        setCategoryFilter(MprGame.DESTROYED_BIT);
//        getCell().setTile(null);
        //  Hud.addScore(100);

    }
}
