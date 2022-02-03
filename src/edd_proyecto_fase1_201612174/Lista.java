/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;
import java.lang.Math;
/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista {

    public Node inicio = null;
    public Node fin = null;
    public String[] fName = {"Azhar","Pippin","Acantha","Sarpedon","Redd","Salome","Karoline","Gobnat","Hamnet","Lyydia"};
    public String[] lName = {"Aparna","Romana","Jedidiah","Darya","Vishal","Dipa","Nicomedes","Haven","Feidlimid","Fiene"};
    public int id = 0;

    Lista() {
    }
    
    Lista(int n) {
        for(int i = 0; i < n; i++){
            Node newNode = new Node();
            add(newNode);
        }
    }

    public void add(String nombre, int c, int bw) { //Add a node to the initial queue and waiting queue
        this.id++;
        Node newNode =new Node(this.id, nombre, c, bw);
        if (inicio == null) { //Agrega al inicio
            inicio = newNode;
        } else if (inicio.next == null) { //Agregar despues del inicio
            fin = newNode;
            inicio.next = fin;
        } else { //Agregar al ultimo del circulo
            fin.next = newNode;
            fin = newNode;
        }
    }
    
    public void add(Node temp) { //Creates the initial window list and adds to the waiting list
        if (inicio == null) { //Agrega al inicio
            inicio = temp;
        } else if (inicio.next == null) { //Agregar despues del inicio
            fin = temp;
            inicio.next = fin;
        } else { //Agregar al ultimo del circulo
            fin.next = temp;
            fin = temp;
        }
    }
    
    public boolean place(Node temp){ // if place == true; pop from initial list;
        Node current = inicio;
        while(current != null){
            if(current.id == 0){
                current.Copy(temp);
                return true;
            } else {
                current = current.next;
            }
        }
        System.out.println("No hay ventanilla disponible");
        return false;      
    }
    
    public void addRandom() {//Adds random people to the waiting queue
        int x = ((int)(Math.random()*10))%4;
        if(x==0){
            System.out.println("Ningun cliente entro");
        }
        else{
            System.out.println(x + " Clientes llego.");
            for(int i = 0; i < x; i++){
                int c = ((int)(Math.random()*10))%6;
                int bw = c==0?(((int)(Math.random()*10))%5)+1:((int)(Math.random()*10))%6;
                String nombre = fName[((int)(Math.random()*10))] + " " + lName[((int)(Math.random()*10))];
                add(nombre, c, bw);
            }
        }
        System.out.println(x);
    }
    
    public void pop(){ //Removes people from the initial/waiting queue
        if(inicio == null){
            System.out.println("Lista esta vacio");
        } else {
            Node temp = inicio;
            inicio = inicio.next;
            temp.next = null;
        }
        
    }
    
    public void delete(int spot) {//Assuming user starts counting from 1, change i to 0 otherwise
        int i = 1;
        Node current = inicio;
        Node temp = current;
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else if (spot == 1) {
            current = inicio.next;
            inicio.next = null;
            inicio = current;
            System.out.println("Pila borrado");
        } else {
            while (i < spot) {
                if (current.next == fin && i == (spot - 1)) {
                    temp = current;
                    current = current.next;
                    break;
                } else if (current.next == fin && i != (spot - 1)) {
                    System.out.println("Fuera de Rango");
                    temp = current;
                    current = current.next;
                    i++;
                    break;
                }
                temp = current;
                current = current.next;
                i++;
            }
            if (current != fin) {
                temp.next = current.next;
                current.next = null;
            }

        }
        System.out.println("Pila borrado");
    }

    public void display() {
        Node current = inicio;
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else {
            do {
                System.out.println("Cliente " + current.id + ": " + current.nombre + " " + current.c + ":" + current.bw);                
                current.info.display();
                current = current.next;
            } while (current != null);
            System.out.println();
        }
    }
}
