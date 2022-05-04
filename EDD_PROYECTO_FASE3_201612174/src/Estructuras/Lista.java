/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista { //Lista para guardar rutas

    Node inicio, fin;
    int peso_Total;

    public class Node {

        int id;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int id) {
            this.id = id;
            this.next = null;
        }
    }

    public Lista() {
        inicio = null;
        fin = null;
        peso_Total = 0;
    }

    public Lista(Lista route) {
        inicio = null;
        fin = null;
        peso_Total = 0;
        this.copy(route);
    }

    public void copy(Lista route) {
        peso_Total = route.peso_Total;
        Node temp = route.inicio;
        while (temp != null) {
            this.insert(temp.id);
            temp = temp.next;
        }
    }

    public void insert(int id) {
        Node newNode = new Node(id);
        if (inicio == null) {
            inicio = newNode;
        } else if (fin == null) {
            fin = newNode;
            inicio.next = fin;
        } else {
            fin.next = newNode;
            fin = newNode;
        }
    }

    public void insert(int id, int peso) {
        Node newNode = new Node(id);
        peso_Total += peso;
        if (inicio == null) {
            inicio = newNode;
        } else if (fin == null) {
            fin = newNode;
            inicio.next = fin;
        } else {
            fin.next = newNode;
            fin = newNode;
        }
    }

    public void pop(int peso) {
        if (this.inicio == null) {
            System.out.println("Error: Ruta Vacio");
            return;
        }

        Node temp = this.inicio;
        while (temp.next != fin) {
            temp = temp.next;
        }
        temp.next = null;
        fin = temp;
        peso_Total -= peso;
    }

    public boolean buscar(int id) {
        if (inicio == null) {
            System.out.println("Error: Lista Vacio");
            return false;
        }
        Node temp = inicio;
        while (temp != null) {
            if (temp.id == id) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void display() {
        Node temp = this.inicio;
        System.out.println("Peso Total: " + this.peso_Total);
        while (temp.next != null) {
            System.out.print(temp.id + " -> ");
            temp = temp.next;
        }
        System.out.print(temp.id);
    }
}
