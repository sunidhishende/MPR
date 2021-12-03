package com.sunidhishende.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Sprites.Bricks;
import com.sunidhishende.game.Sprites.Properties;
import com.sunidhishende.game.Sprites.mcqbrick;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map, MprGame game){
        BodyDef bdef= new BodyDef();
        PolygonShape shape= new PolygonShape();
        FixtureDef fdef= new FixtureDef();
        Body body;

        //Ground Layer
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type= BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ MprGame.PPM, (rect.getY()+rect.getHeight()/2)/MprGame.PPM);
            body= world.createBody(bdef);
            shape.setAsBox((rect.getWidth()/2)/MprGame.PPM , (rect.getHeight()/2)/MprGame.PPM);
            fdef.shape= shape;
            body.createFixture(fdef);
        }

        //Testtubes layer
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type= BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/MprGame.PPM, (rect.getY()+rect.getHeight()/2)/MprGame.PPM);
            body= world.createBody(bdef);
            shape.setAsBox((rect.getWidth()/2)/MprGame.PPM , (rect.getHeight()/2)/MprGame.PPM);
            fdef.shape= shape;
            body.createFixture(fdef);
        }


        //Bricks layer
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Bricks(world, map, rect);
        }
        //Properties layer
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Properties(world, map, rect, game);
        }
        //mcqbrick
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new mcqbrick(world, map, rect);
        }


    }
}
