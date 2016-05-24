package com.pi.vidor.ai;

/**
 *
 * @author Francisco
 */

public class Neighbors {
    private Node start;
    private Node end;
    private int cost = 1;

    public Neighbors(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public int getCost() {
        return cost;
    }
    
}
