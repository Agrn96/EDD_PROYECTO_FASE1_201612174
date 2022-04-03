package edd_proyecto_fase2_201612174;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista_DE {

    String album_Name;
    Node_DE inicio;
    Node_DE fin;

    public Lista_DE() {
        this.inicio = null;
        this.fin = null;
    }

    public void add(String id) { //Adding albums
        Node_DE newNode = new Node_DE(id);
        if (inicio == null) { //Agrega al inicio
            inicio = newNode;
        } else if (inicio.next == inicio) { //Agregar despues del inicio
            inicio.next = newNode;
            newNode.prev = inicio;
        } else { //Agregar al ultimo del circulo
            Node_DE current = inicio;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
    }

    public void add_Images(String album_Name, ABB img) {
        Node_DE temp = this.inicio;
        Node_DE newNode = new Node_DE();
        while (temp != null) {
            if (temp.id == album_Name) {
                if (temp.down == null) {
                    temp.down = newNode;
                    newNode.node_Access = img;
                } else {
                    temp = temp.down;
                    while(temp != null){
                        if(temp.node_Access.raiz.data == img.raiz.data){
                            System.out.println("Error: Duplicate image");
                            break;
                        }
                        if(temp.down == null){
                            temp.down = newNode;
                            newNode.node_Access = img;
                            break;
                        }
                        temp = temp.down;
                    }
                }
                break;
            } 
            temp = temp.next;
        }
    }

    public void display() {
        Node_DE current = inicio;
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else {
            System.out.println("Nodos son ");
            do {
                System.out.println("Placeholder");
                current = current.next;
            } while (current != inicio);
            System.out.println();
        }
    }
}
