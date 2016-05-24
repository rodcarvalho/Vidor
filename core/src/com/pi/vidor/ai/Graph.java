package com.pi.vidor.ai;

import com.pi.vidor.screens.PlayScreen;

/**
 *
 * @author Francisco
 */
public class Graph {
    
    private int WIDTH;
    private int HEIGHT;
    private Node[][] nodes;

    public Graph(PlayScreen screen) {
        this.WIDTH = screen.getMapWidth();
        this.HEIGHT = screen.getMapHeight();
        
        this.nodes = new Node[WIDTH][HEIGHT];
        
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                nodes[x][y] = new Node(x, y, false);
                
                if (x > 0) nodes[x][y].getAdj().insertNode(nodes[x -1][y]);
                    //
                if (y > 0) nodes[x][y].getAdj().insertNode(nodes[x][y -1]);
                    //
                if (x < WIDTH - 1) nodes[x][y].getAdj().insertNode(nodes[x + 1][y]);
                    //
                if (y < HEIGHT - 1) nodes[x][y].getAdj().insertNode(nodes[x][y + 1]);
            }
        }
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
