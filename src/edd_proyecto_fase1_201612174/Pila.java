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
public class Pila {
    public Node inicio = null;
    public Node fin = null;

    Pila() {
    }

    public void push(int data) {
        Node newNode = new Node(data);
        if (inicio == null) { //Agrega al inicio
            inicio = newNode;
            fin = inicio;
        } else if (inicio.next == null) { //Agregar despues del inicio
            fin = newNode;
            inicio.next = fin;
        } else { //Agregar al ultimo del circulo
            fin.next = newNode;
            fin = newNode;
        }
    }

    public void pop() {
        if(inicio == null) {
            System.out.println("Lista esta vacio");
        } else if(fin == null || inicio.next == null){
            //System.out.println(inicio.data + " has been removed");
            inicio = null;
            fin = null;
        } else {
            Node temp = inicio;
            while(temp.next != fin){
                temp = temp.next;
            }
            //System.out.println(fin.data + " has been removed");
            fin = temp;
            fin.next = null;
        }
    }
    
    public void display() {
        Node current = inicio;
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else {
            System.out.println("Nodos son: ");
            do {
                System.out.println(current.data);
                current = current.next;
            } while (current != null);
            System.out.println();
        }
    }
}
