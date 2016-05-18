package com.pi.vidor.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;

/**
 * Created by rodrigo.bcarvalho1 on 10/05/2016.
 */
public class Wolf extends Enemy {
    public Wolf(){
        setHealth(3);
        setWorld();
    }

    @Override
    public void defineBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(320 / Main.getPPM(), 240 / Main.getPPM());
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.fixedRotation = true;
        bdef.linearDamping = 5.0f;
        b2body = world.createBody(bdef);
        b2body.setFixedRotation(true);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8 / Main.getPPM());

        fdef.shape = shape;

        b2body.createFixture(fdef);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
