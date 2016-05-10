
package com.pi.vidor.ai;

import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class List {
    
    private ArrayList<Node> list = new ArrayList<Node>();
    
    public void inserir (Node node) {
        list.add(node);
    }
    
    public void remover (Node node) {
        list.remove(node);
    }
    
    public boolean get (Node node) {
        return list.contains(node);
    }
    
    public int tamanho () {
        return list.size();
    }
    
}
