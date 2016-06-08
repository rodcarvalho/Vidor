/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.vidor.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

import com.pi.vidor.screens.PlayScreen;
import com.pi.vidor.sprites.Enemy;
import com.pi.vidor.sprites.InteractiveItem;
import com.pi.vidor.sprites.Wolf;

/**
 *
 * @author Francisco
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        if(fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;
            
            if (object.getUserData() instanceof InteractiveItem) {
                ((InteractiveItem) object.getUserData()).onCollision();
            }
            if (object.getUserData() instanceof Wolf) {
                ((Wolf) object.getUserData()).onCollision();
            }
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endContact(Contact contact) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
