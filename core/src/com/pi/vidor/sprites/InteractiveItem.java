/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.vidor.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;

/**
 *
 * @author Francisco
 */
public abstract class InteractiveItem {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;
    protected Fixture fixture;

    public InteractiveItem(PlayScreen screen, Rectangle bounds) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;
        
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Main.getPPM(), (bounds.getY() + bounds.getHeight() / 2) / Main.getPPM());

        body = world.createBody(bdef);

        shape.setAsBox((bounds.getWidth() / 2) / Main.getPPM(), (bounds.getHeight() / 2) / Main.getPPM());
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onCollision();
    public void setFilterCategory(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    
    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int) (body.getPosition().x * Main.getPPM() / 16), (int) (body.getPosition().y * Main.getPPM() / 16));
    }
            
    public Body getBody() {
        return body;
    }
    
    
}
