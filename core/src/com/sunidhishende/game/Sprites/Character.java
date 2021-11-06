package com.sunidhishende.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sunidhishende.game.MprGame;
import com.sunidhishende.game.Scenes.Hud;

public class Character extends Sprite {
    public World world;
    public Body b2body;
    private boolean  characterIsDead=false;

    public Character(World world){
        this.world=world;
        defineCharacter();

    }

    public void defineCharacter(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(32/ MprGame.PPM,32/MprGame.PPM);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);

        FixtureDef fdef= new FixtureDef();
        CircleShape shape= new CircleShape();
        shape.setRadius(5/MprGame.PPM);
        fdef.filter.categoryBits=MprGame.CHARACTER_BIT;
        fdef.filter.maskBits= MprGame.DEFAULT|MprGame.PROPERTIES_BIT|MprGame.BRICK_BIT;
        fdef.shape= shape;
        fdef.friction= 60/MprGame.PPM;
        b2body.createFixture(fdef);

        EdgeShape head= new EdgeShape();
        head.set(new Vector2(-2/MprGame.PPM, 5/MprGame.PPM), new Vector2(2/MprGame.PPM, 5/MprGame.PPM));
        fdef.shape=head;
        fdef.isSensor=true;
        b2body.createFixture(fdef).setUserData("head");
    }

    public void update(float dt){
        if (b2body.getPosition().y<0/MprGame.PPM|| Hud.gethealth()==0)
        {
            characterIsDead=true;
        }


    }

    public void jump(){
        b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
    }

    public boolean isDead()
    {
        return characterIsDead;
    }


}
