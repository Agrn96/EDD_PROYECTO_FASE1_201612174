package edd_proyecto_fase2_201612174;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class NodoB {

    //Data
    public String id; // dpi
    public String nombre_Cliente; //Name
    public String password; //Password
    //Pointers
    NodoB siguiente;
    NodoB anterior;
    RamaB derecha;
    RamaB izquierda;
    //Pointer to album
    public Lista_DE node_Album;
    //Pointer to capas
    public ABB node_Capas;
    //Pointer to images
    public AVL node_Img;

    public NodoB(String id, String nombreC, String pass) {
        this.id = id;
        this.nombre_Cliente = nombreC;
        this.password = pass;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
        this.node_Capas = null;
    }

}