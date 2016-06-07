package com.pi.vidor.ai;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Francisco
 */

public class Node {
    
    private int x, y;
    private Vector2 worldPos;
    private int f, g, h;
    private boolean collidable;
    private Node parent;

    public Node(int x, int y, Vector2 worldPos, boolean collidable) {
        this.x = x;
        this.y = y;
        this.worldPos = worldPos;
        this.collidable = collidable;
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

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
    
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    public int getF() {
        return g + h;
    }

    public void setF(int f) {
        this.f = f;
    }
    
}
