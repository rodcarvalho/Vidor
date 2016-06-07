package com.pi.vidor.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;

/**
 * Created by rodrigo.bcarvalho1 on 10/05/2016.
 */
public class Wolf extends Enemy {
    
    private float state_time;
    private Animation animation;
    private Array<TextureRegion> frames;

    
    public Wolf(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("little_vidor"), i * 16, 0, 16, 16));
        }
        animation = new Animation(0.4f, frames);
        state_time = 0;
        setBounds(getX(), getY(), 16 / Main.getPPM(), 16 / Main.getPPM());
    }
    
    public void update(float delta) {
        state_time += delta;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(animation.getKeyFrame(state_time, true));
        b2body.setLinearVelocity(velocity);
    }
    
    
//    public Wolf(){
//        setHealth(2);
//        setWorld();
//    }

//    @Override
//    public void defineBody() {
//        BodyDef bdef = new BodyDef();
//        bdef.position.set(320 / Main.getPPM(), 240 / Main.getPPM());
//        bdef.type = BodyDef.BodyType.DynamicBody;
//        bdef.fixedRotation = true;
//        bdef.linearDamping = 5.0f;
//        b2body = world.createBody(bdef);
//        b2body.setFixedRotation(true);
//
//        FixtureDef fdef = new FixtureDef();
//        CircleShape shape = new CircleShape();
//        shape.setRadius(8 / Main.getPPM());
//
//        fdef.shape = shape;
//
//        b2body.createFixture(fdef);
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(300 / Main.getPPM(), 220 / Main.getPPM());
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

    @Override
    public void onCollision(Vidor vidor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
