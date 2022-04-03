package edd_proyecto_fase2_201612174;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Node_DE {
    String id;
    Node_DE next, prev, down;
    ABB node_Access;
    
    public Node_DE(){
        this.next = null;
        this.prev = null;
    }
    
    public Node_DE(String id){                        
        this.id = id;
        this.next = null;
        this.prev = null;
        this.node_Access = null; // Change later to accept an avl and change id to be the avl id
    }
}
