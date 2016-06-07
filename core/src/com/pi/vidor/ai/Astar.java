
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
        Node startNode = graph.nodeFromXY(start);
        Node targetNode = graph.nodeFromXY(target);
        
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
                retracePath(startNode, targetNode);
                return;
            }

            for (Node neighbour : graph.getNeighbours(currentNode)) {
                if(!neighbour.isCollidable() || closed.contains(neighbour))
                    continue;

                int newMovementCostToNeighbour = (currentNode.getG() + heuristic(currentNode, neighbour));

                if (!open.contains(neighbour) || newMovementCostToNeighbour < neighbour.getG()){
                    neighbour.setG(newMovementCostToNeighbour);
                    neighbour.setH(heuristic(neighbour, targetNode));
                    neighbour.setParent(currentNode);

                    if (!open.contains(neighbour))
                        open.insertNode(neighbour);
                }
            }
            
        }
    }
    
    public void retracePath(Node startNode, Node endNode){
        ArrayList<Node> path = new ArrayList<Node>();
        Node currentNode = endNode;

        while (currentNode != startNode){
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        graph.setPath(reverse(path));
    }
    
    public ArrayList<Node> reverse(ArrayList<Node> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++)
            list.add(i, list.remove(j));
        return list;
    }
    
    @Override
    public int heuristic(Node n, Node target) {
        return Math.abs(target.getX() - n.getX()) + Math.abs(target.getY() - n.getY());
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
