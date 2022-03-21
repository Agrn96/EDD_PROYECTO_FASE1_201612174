package edd_proyecto_fase2_201612174;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Node {
    int id, col, row, data;
    Node next, prev, nodeAccess, right, left, up, down;
    String name;
    
    public Node(){
        this.next = null;
        this.prev = null;
        this.nodeAccess = null;
    }
    
    public Node(int id){                        
        this.id = id;
        this.next = null;
        this.prev = null;
        this.nodeAccess = null;
    }
    
    public Node(String name){                   //Initial Node
        this.name = name;
        this.next = null;
        this.prev = null;
        this.nodeAccess = null;
    }
    
    public Node(int fila, int col, int data){   //Node inside matrix
        this.col = col;
        this.row = fila;
        this.data = data;
        this.right = null;
        this.left = null;
        this.up = null;
        this.down = null;
    }
}
