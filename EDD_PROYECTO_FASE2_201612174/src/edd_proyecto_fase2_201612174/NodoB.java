package edd_proyecto_fase2_201612174;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class NodoB {

    //Data
    String id; // dpi
    String nombre_Cliente; //Name
    String password; //Password
    //Pointers
    NodoB siguiente;
    NodoB anterior;
    RamaB derecha;
    RamaB izquierda;
    //Pointer to album
    Lista_DE node_Access;

    public NodoB(String id, String nombreC, String pass) {
        this.id = id;
        this.nombre_Cliente = nombreC;
        this.password = pass;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
    }

}