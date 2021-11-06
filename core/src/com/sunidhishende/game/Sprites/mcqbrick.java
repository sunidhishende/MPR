package com.sunidhishende.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;

public class mcqbrick extends InteractiveTileObject {
        public static int ishit=0;
        private TiledMapTileSet tileSet;
        public mcqbrick(World world, TiledMap map, Rectangle bounds)
        {
            super(world, map, bounds);
            tileSet=map.getTileSets().getTileSet("mario");
            fixture.setUserData(this);
            setCategoryFilter(MprGame.MCQ_BIT);

        }

        @Override
        public void onHeadHit() {
            Gdx.app.log("McqBrick", "Collision");
            ishit=1;


        }

    }

