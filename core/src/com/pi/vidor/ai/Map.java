
package com.pi.vidor.ai;
import com.badlogic.gdx.maps.MapProperties;
import com.pi.vidor.screens.PlayScreen;
/**
 *
 * @author Francisco
 */

public class Map {
    private MapProperties properties;
    private int WIDTH;
    private int HEIGHT;
    private PlayScreen screen;
    private Node[][] map;
    private List adj = new List();

    public Map() {
        properties = screen.getMap().getProperties();
        WIDTH = properties.get("width", Integer.class);
        HEIGHT = properties.get("height", Integer.class);
        
        map = new Node[WIDTH][HEIGHT];
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                map[x][y] = new Node(x, y);
            }
        }
            
    } 
    
    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
    
}
