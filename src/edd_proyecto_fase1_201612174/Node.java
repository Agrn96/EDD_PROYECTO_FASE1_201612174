package edd_proyecto_fase1_201612174;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Node {

    Pila info = new Pila();
    public int data, id, c, bw, tempC = 0, tempBW = 0, turnoI, turnoF;
    public String nombre;
    public Node next = null;
    public Node prev = null;

    public Node() {
    }

    public Node(Pila info) {
        this.info = info;
    }

    public Node(int data) {
        this.data = data;
    }

    public Node(int id, String nombre, int c, int bw, int turno) {
        this.id = id;
        this.nombre = nombre;
        this.c = c;
        this.bw = bw;
        this.turnoI = turno;
    }

    public void Clear() {
        id = 0;
        c = 0;
        bw = 0;
        tempC = 0;
        tempBW = 0;
        nombre = "";
    }

    public void Copy(Node temp) {
        this.id = temp.id;
        this.c = temp.c;
        this.bw = temp.bw;
        this.turnoI = temp.turnoI;
        this.turnoF = temp.turnoF;
        this.tempC = 0;
        this.tempBW = 0;
        this.nombre = temp.nombre;
    }
}
