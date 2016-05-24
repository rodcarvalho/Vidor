package com.pi.vidor.ai;

/**
 *
 * @author Francisco
 */

public class Node {
    
    private int x, y;
    private int cost;
    private boolean collidable;
    private List adj;
    private Node parent;

    public Node(int x, int y, boolean collidable) {
        this.x = x;
        this.y = y;
        this.collidable = collidable;
        this.adj = null;
        this.cost = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCollidable() {
        return collidable;
    }
    
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List getAdj() {
        return adj;
    }    

    public int getCost() {
        return cost;
    }
    
}
