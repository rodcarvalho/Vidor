package com.pi.vidor.ai;

/**
 *
 * @author Francisco
 */
public interface Heuristic {    
    /*
    MANHATTTAN DISTANCE:
    DX = abs(targetNode.x - currentNode.x);
    DY = abs(targetNode.y - currentNode.y);
    H = DX + DY;
    
    public double calc(Node n, Node target) {
        return Math.abs(target.getX() - n.getX()) + Math.abs(target.getY() - n.getY());
    }
    */
    
    public float heuristic(Node n, Node target);
    
}
