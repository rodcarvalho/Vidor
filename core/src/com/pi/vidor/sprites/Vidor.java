
package com.pi.vidor.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;

/**
 *
 * @author Francisco
 */
public class Vidor extends Sprite {
    private enum State {STANDING, RUNNING};
    private State current_state;
    private State previous_state;
    
    private World world;
    private Body b2body;
    private TextureRegion vidor_standing;
    
    private Animation vidor_run;
    
    private float state_timer;
    private boolean running_right;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Body getB2body() {
        return b2body;
    }

    public void setB2body(Body b2body) {
        this.b2body = b2body;
    }
    
    
    public Vidor(PlayScreen screen) {
        //super();
        super(screen.getAtlas().findRegion("little_vidor"));
        this.world = screen.getWorld();
        current_state = State.STANDING;
        previous_state = State.STANDING;
        state_timer = 0;
        running_right = true;
        
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 1; i < 4; i++)
            frames.add(new TextureRegion(getTexture(), i * 16 + 71, 0, 16, 16));
        
        vidor_run = new Animation(0.1f, frames);
        frames.clear();
        
        defineVidor();
        
        vidor_standing = new TextureRegion(getTexture(), 71, 0, 16, 16);
        setBounds(71, 0, 16 / Main.getPPM(), 16 / Main.getPPM());
        setRegion(vidor_standing);
    }
    
    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }
    
    public TextureRegion getFrame(float delta) {
        current_state = getState();
        
        TextureRegion region;
        switch(current_state) {
            case RUNNING:
                region = vidor_run.getKeyFrame(state_timer, true);
                break;
            case STANDING:
            default:
                region = vidor_standing;
                break;

        }
        
        if ((b2body.getLinearVelocity().x < 0 || !running_right) && !region.isFlipX()) {
            region.flip(true, false);
            running_right = false;        
        }
        else if ((b2body.getLinearVelocity().x > 0 || running_right) && region.isFlipX()) {
            region.flip(true, false);
            running_right = true;
        }
        
        state_timer = current_state == previous_state ? state_timer + delta : 0;
        previous_state = current_state;
        return region;
    }
    
    public State getState() {
        if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }
    
    private void defineVidor() {
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
        
        fdef.filter.categoryBits = Main.getVIDOR_FILTER();
        fdef.filter.maskBits = (short) (Main.getDEFAULT_FILTER() | Main.getITEM_FILTER());
        
        fdef.shape = shape;
        b2body.createFixture(fdef).getUserData();
        
        PolygonShape head = new PolygonShape();
        head.setAsBox(8 / Main.getPPM(), 8 / Main.getPPM());
        //head.set(new Vector2(-2 / Main.getPPM(), 9 / Main.getPPM()), new Vector2(2 / Main.getPPM(), 9 / Main.getPPM()));
        fdef.shape = head;
        fdef.isSensor = true;
        
        b2body.createFixture(fdef).setUserData("head");
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}