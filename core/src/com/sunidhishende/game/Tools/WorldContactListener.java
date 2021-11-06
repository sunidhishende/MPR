package com.sunidhishende.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sunidhishende.game.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Fixture fixa= contact.getFixtureA();
        Fixture fixb= contact.getFixtureB();

        if (fixa.getUserData()=="head"|| fixb.getUserData()=="head")
        {
            Fixture head= fixa.getUserData()=="head"?fixa:fixb;
            Fixture object= head==fixa?fixb:fixa;

            if(object.getUserData()!= null&& InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass()))
            {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
