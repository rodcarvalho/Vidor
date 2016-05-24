
package com.pi.vidor.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pi.vidor.Main;
import com.pi.vidor.screens.PlayScreen;
import com.pi.vidor.sprites.Item;

/**
 *
 * @author Francisco
 */
public class Box2DWorld {
    public Box2DWorld(PlayScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        
        
        //cria comportamentos físicos para as paredes externas
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Main.getPPM(), (rect.getY() + rect.getHeight() / 2) / Main.getPPM());
            
            body = world.createBody(bdef);
            
            shape.setAsBox((rect.getWidth() / 2) / Main.getPPM(), (rect.getHeight() / 2) / Main.getPPM());
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        //cria comportamentos físicos para as paredes internas
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Main.getPPM(), (rect.getY() + rect.getHeight() / 2) / Main.getPPM());
            
            body = world.createBody(bdef);
            
            shape.setAsBox((rect.getWidth() / 2) / Main.getPPM(), (rect.getHeight() / 2) / Main.getPPM());
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            
            new Item(screen, rect);
        }
        
    }
    
}
