package com.pi.vidor.ai;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class List {
    
    private ArrayList<Node> list = new ArrayList<Node>();
   
    public void insertNode(Node node) {
        list.add(node);
    }
    
    public void removeNode(Node node) {
        list.remove(node);
    }
    
    public Node get(int x) {
        return list.get(x);
    }
    
    public boolean contains(Node node) {
        return list.contains(node);
    }
    
    public int size () {
        return list.size();
    }
    
}
