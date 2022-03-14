package edd_proyecto_fase2_201612174;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista_Orthogonal {
    Node inicio,fin,next;
    int x,y;
    
    public Lista_Orthogonal(){
        this.inicio = null;
        this.fin = null;
        this.next = null;
        this.x = 0;
        this.y = 0;
    }
    
    public void add_headers(String name, int x, int y){
        Node node_Header = new Node(name);  // Create new node that has matrix name (1st block)
        Node temp = new Node();
        this.inicio = node_Header;              
        for(int i = 0; i < x; i++){         // Create all the row headers in the matrix
            Node row = new Node(i);
            row.next = temp;
            temp.prev = row;
            temp = temp.prev;
        }
        temp = this.inicio;                 // Return temp to the 1st block
        for(int i = 0; i < y; i++){         // Create all the col headers in the matrix
            Node col = new Node(i);
            col.prev = temp;
            temp.next = col;
            temp = temp.next;
        }
    }
    
    public void add_nodes(int x, int y, int data){ // Change data 
        Node newNode = new Node(x, y, data);
        Node temp = new Node();
        Node temp_ = new Node();
        temp = this.inicio.next;
        while(temp.id != y){
            temp = temp.next;
        }
        if(temp.nodeAccess == null){
            temp.nodeAccess = newNode;
            if(temp.prev == this.inicio){
                temp = this.inicio.prev;
                temp.nodeAccess = newNode;
            } else {
                temp = temp.prev;
                temp_ = temp.nodeAccess;
                temp_.right = newNode;
                newNode.left = temp_;
            }
        } else {
            temp_ = temp.nodeAccess;
            while(temp_.down != null){
                temp_ = temp_.down;
            }
            temp_.down = newNode;
            newNode.up = temp_;
            if(temp.prev == this.inicio){
                temp = this.inicio.prev;
                while(temp.id != x){
                    temp = temp.prev;
                }
                temp.nodeAccess = newNode;
            } else {
                temp = temp.prev;
                temp_ = temp.nodeAccess;
                while(temp_.down != null){
                    temp_ = temp_.down;
                }
                temp_.right = newNode;
                newNode.left = temp_;
            }
        }
    }
    
    public void setList(Node inicio, Node next, int x, int y){
        this.inicio = inicio;
        this.next = next;
        this.x = x;
        this.y = y;
    }
}
