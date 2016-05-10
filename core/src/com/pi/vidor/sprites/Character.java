/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.vidor.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    
    private World world;
    private Body b2body;
    
    private int health;
    
    private Animation run;
    private Animation attack;
    
    private TextureRegion texture;
    
    public abstract void defineBody();
    
    
    
}
