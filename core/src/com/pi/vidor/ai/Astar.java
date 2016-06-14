
package com.pi.vidor.ai;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Francisco
 */

public class Astar implements Heuristic {
    
    private Graph graph;
    private List open;
    private HashSet<Node> closed;
    
    public Astar(Graph graph) {
        this.graph = graph;
        this.open = new List();
        this.closed = new HashSet<Node>();
    }
    
    public void Pathfinding(Vector2 start, Vector2 target) {
        Node startNode = graph.nodeXY(start);
        Node targetNode = graph.nodeXY(target);
        
        open.insertNode(startNode);
        
        while (open.size() != 0) { 
            Node currentNode = open.get(0);
            
            for (int i = 1; i < open.size(); i++){
                if(open.get(i).getF() < currentNode.getF() || open.get(i).getF() == currentNode.getF() && open.get(i).getH() < currentNode.getH()){
                    currentNode = open.get(i);
                }
            }

            open.removeNode(currentNode);
            closed.add(currentNode);

            if(currentNode == targetNode){
                followPath(startNode, targetNode);
                return;
            }
            
            for (Node neighbour : graph.neighbors(currentNode)) {
                if(!neighbour.isCollidable() || closed.contains(neighbour))
                    continue;

                int newCost = (currentNode.getG() + heuristic(currentNode, neighbour));

                if (!open.contains(neighbour) || newCost < neighbour.getG()){
                    neighbour.setG(newCost);
                    neighbour.setH(heuristic(neighbour, targetNode));
                    neighbour.setParent(currentNode);

                    if (!open.contains(neighbour))
                        open.insertNode(neighbour);
                }
            }
            
        }
    }
    
    public void followPath(Node startNode, Node targetNode){
        ArrayList<Node> path = new ArrayList<Node>();
        Node currentNode = targetNode;

        while (currentNode != startNode){
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        graph.setPath(reverse(path));
    }
    
    public ArrayList<Node> reverse(ArrayList<Node> path) {
        for(int i = 0, j = path.size() - 1; i < j; i++)
            path.add(i, path.remove(j));
        return path;
    }
    
    @Override
    public int heuristic(Node n, Node target) {
        return Math.abs(target.getX() - n.getX()) + Math.abs(target.getY() - n.getY());
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
