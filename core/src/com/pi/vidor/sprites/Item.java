/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.vidor.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pi.vidor.Hud.Hud;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;

/**
 *
 * @author Francisco
 */
public class Item extends InteractiveItem {

    public Item(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        setFilterCategory(Main.getITEM_FILTER());
    }

    @Override
    public void onCollision() {
        System.out.println("COLETA ITEM");
        setFilterCategory(Main.getDESTROYED_FILTER());
        getCell().setTile(null);
        Hud.addScore(200);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
