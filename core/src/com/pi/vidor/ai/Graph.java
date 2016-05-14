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
        
        for (int i = 0; i < WIDTH; i++) {
            for (int k = 0; k < HEIGHT; k++) {
                nodes[i][k] = new Node(i, k, false);
            }
        }
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Node getNodes(int x, int y) {
        return this.nodes[x][y];
    }
    
}
