/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.vidor.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pi.vidor.Main;

/**
 *
 * @author Francisco
 */
public abstract class Character extends Sprite {
    private enum State {STANDING, RUNNING, ATTACKING};
    private State current_state;
    private State previous_state;
    private World world;
    private Body b2body;
    private Animation run;
    private Animation attack;
    private TextureRegion texture;
    private int health;

    public abstract void defineBody();

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public State getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state() {
        this.current_state = current_state;
    }

    public State getPrevious_state() {
        return previous_state;
    }

    public void setPrevious_state() {
        this.previous_state = previous_state;
    }

    public Animation getRun() {
        return run;
    }

    public void setRun(Animation run) {
        this.run = run;
    }

    public Animation getAttack() {
        return attack;
    }

    public void setAttack(Animation attack) {
        this.attack = attack;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }
}