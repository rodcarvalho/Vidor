
package com.pi.vidor.ai;

/**
 *
 * @author Francisco
 */
public class Astar implements Heuristic {
    private Graph graph;
    private List open;
    private List closed;
    private Node startNode;

    @Override
    public float heuristic(Node n, Node target) {
        return Math.abs(target.getX() - n.getX()) + Math.abs(target.getY() - n.getY());
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public void Pathfinder () {
        
    }
    
}
