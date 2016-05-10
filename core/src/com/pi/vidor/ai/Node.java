
package com.pi.vidor.ai;

/**
 *
 * @author Francisco
 */
public class Node {
    private int x, y;
    private double f;
    private double g;
    private double h;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getF() {
        return f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    
    
    
}
