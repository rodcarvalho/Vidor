package com.pi.vidor.ai;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pi.vidor.screens.PlayScreen;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */

public class Graph {
    
    private Node[][] nodes;
    private int WIDTH;
    private int HEIGHT;
    private int TILESIZE;
    private ArrayList<Node> path;
    private Astar astar;

    public Graph(PlayScreen screen) {
        this.WIDTH = screen.getMapWidth();
        this.HEIGHT = screen.getMapHeight();
        this.TILESIZE = 16;
        
        astar = new Astar(this);
        
        this.nodes = new Node[WIDTH][HEIGHT];
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Vector2 nodePos = new Vector2((x * TILESIZE), (y * TILESIZE));
                boolean collidable = true;
                for(RectangleMapObject object : screen.getObstacles()) {
                    if(object.getRectangle().overlaps(new Rectangle(nodePos.x, nodePos.y, TILESIZE, TILESIZE))) {
                            collidable = false;
                            break;
                    }
                }
                nodes[x][y] = new Node(x, y, nodePos, collidable);

            }
        }
        
    }
    
    public ArrayList<Node> neighbors(Node node){
        ArrayList<Node> neighbors = new ArrayList<Node>();

        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++){
                if(x == 0 && y == 0)
                    continue;

                int X = node.getX() + x;
                int Y = node.getY() + y;

                if(X >= 0 && X < WIDTH && Y >= 0 && Y < HEIGHT){
                    neighbors.add(nodes[X][Y]);
                }
            }
        }
        return neighbors;
    }
    
    public Node nodeXY(Vector2 pos){
        int x = (int)((pos.x) / TILESIZE);
        int y = (int)((pos.y) / TILESIZE);
        return getNode(x, y);
    }
    
    public ArrayList<Node> findPath(Vector2 pos, Vector2 targetPos) {
        astar.Pathfinding(pos, targetPos);
        return path;
    }

    public void setPath(ArrayList<Node> path) {
        this.path = path;
    }
    
    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Node getNode(int x, int y) {
        return this.nodes[x][y];
    }
    
}
