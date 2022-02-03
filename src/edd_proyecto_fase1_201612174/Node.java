/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Node {
    Pila info = new Pila();
    public int data, id, c, bw, tiempoCompleto = 0, tempC=0, tempBW=0;
    public String nombre;
    public Node next = null;

    public Node() {
    }
    
    public Node(int id, String nombre, int c, int bw) {
        this.id = id;
        this.nombre = nombre;
        this.c = c;
        this.bw = bw;
    }
    
    public void Clear(){
        id = 0;
        c = 0;
        bw = 0;
        tiempoCompleto = 0;
        tempC = 0;
        tempBW = 0;
        nombre = "";
    }
    
    public void Copy(Node temp){
        this.id = temp.id;
        this.c = temp.c;
        this.bw = temp.bw;
        this.tiempoCompleto = temp.tiempoCompleto;
        this.tempC = temp.tempC;
        this.tempBW = temp.tempBW;
        this.nombre = temp.nombre;
    }
    
    public Node(Pila info){
        this.info = info;
    }
}
