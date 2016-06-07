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
    
    private int WIDTH;
    private int HEIGHT;
    private int tileSize;
    private Node[][] nodes;
    private ArrayList<Node> path;
    private Astar a_star;

    public Graph(PlayScreen screen) {
        this.WIDTH = screen.getMapWidth();
        this.HEIGHT = screen.getMapHeight();
        this.tileSize = 16;
        
        a_star = new Astar(this);
        
        this.nodes = new Node[WIDTH][HEIGHT];
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Vector2 nodePos = new Vector2((x * tileSize), (y * tileSize));
                boolean collidable = true;
                for(RectangleMapObject object : screen.getObstacles()) {
                    if(object.getRectangle().overlaps(new Rectangle(nodePos.x, nodePos.y, tileSize, tileSize))) {
                            collidable = false;
                            break;
                    }
                }
                nodes[x][y] = new Node(x, y, nodePos, collidable);

            }
        }
        
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//                if (x > 0) {
//                    nodes[x][y].getAdj().insertNode(nodes[x -1][y]);
//                }
//                    //
//                if (y > 0) {
//                    nodes[x][y].getAdj().insertNode(nodes[x][y -1]);
//                }
//                    //
//                if (x < WIDTH - 1) {
//                    nodes[x][y].getAdj().insertNode(nodes[x + 1][y]);
//                }
//                    //
//                if (y < HEIGHT - 1) {
//                    nodes[x][y].getAdj().insertNode(nodes[x][y + 1]);
//                }
//            }
//        }
    }
    
    public ArrayList<Node> getNeighbours(Node node){
        ArrayList<Node> neighbours = new ArrayList<Node>();

        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++){
                if(x == 0 && y == 0)
                    continue;

                int checkX = node.getX() + x;
                int checkY = node.getY() + y;

                if(checkX >= 0 && checkX < WIDTH && checkY >= 0 && checkY < HEIGHT){
                    neighbours.add(nodes[checkX][checkY]);
                }
            }
        }
        return neighbours;
    }
    
    public Node nodeFromXY(Vector2 pos){
        int x = (int)((pos.x) / tileSize);
        int y = (int)((pos.y) / tileSize);
        return getNode(x, y);
    }
    
    public ArrayList<Node> find(Vector2 pos, Vector2 targetPos) {
        a_star.Pathfinding(pos, targetPos);
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
