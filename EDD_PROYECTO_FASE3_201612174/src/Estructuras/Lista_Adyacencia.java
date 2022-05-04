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
public class Lista_Adyacencia {

    Node inicio, fin;
    int size;

    public class Node {

        int id;
        Lista_Adyacencia dest;
        Lista_Adyacencia peso;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int id) {
            this.id = id;
            this.next = null;
        }
    }

    public Lista_Adyacencia() {
        inicio = null;
        fin = null;
        size = 0;
    }

    public void insertar(int start, int end, int peso) { //Insert function that adds the dest for each start point
        if (start > end && start > size) {
            increase(start);
        } else if (end > start && end > size) {
            increase(end);
        }

        Node temp = inicio;
        while (temp.id != start) {
            temp = temp.next;
        } //temp should be at the start position now
        temp.dest.insertar_Dest(end);
        temp.peso.insertar_Dest(peso);

        temp = inicio;
        while (temp.id != end) {
            temp = temp.next;
        } //temp should be at the start position now
        temp.dest.insertar_Dest(start);
        temp.peso.insertar_Dest(peso);
    }

    public void insertar_Dest(int end) { //Insert function just to add to the end of the list
        Node newNode = new Node(end);
        if (this.inicio == null) {
            inicio = newNode;
        } else {
            Node temp = inicio;
            while (temp.next != null) {
                temp = temp.next;
            } //Should be at the end of the dest list
            temp.next = newNode;
        }
    }

    public void increase(int bigger) {
        if (inicio == null) {
            Node newNode = new Node(1);
            inicio = newNode;
            size++;
        }

        Node temp = inicio;
        while (temp.id != size) {
            temp = temp.next;
        }

        while (temp.id != bigger) {
            Node newNode = new Node(temp.id + 1);
            temp.next = newNode;
            size++;
            temp = temp.next;
        }
    }

    public void ruta(int start, int end) {
        Lista best = new Lista();
        Lista route = new Lista();
        Node temp = inicio;
        while (temp.id != start) {
            temp = temp.next;
        }//Should be at start position now

        route.insert(temp.id);
        //Recursion
        route_Rec(temp.id, end, best, route, temp.peso.inicio);
//        route.insert(temp.dest.inicio.id);
//        route.peso_Total += (temp.peso.inicio.id);
//        Node rand = temp.dest.inicio;
//        Node rand_ = temp.peso.inicio;
    }

    public void route_Rec(int curr, int end, Lista best, Lista route, Node peso) {
        Node temp = inicio;
        while (temp.id != curr) {
            temp = temp.next;
        }//Should be at current position now

        if (temp.dest.inicio == null) {
            route.pop(peso.id);
            return;
        }

        if (route.peso_Total > best.peso_Total) {
            route.pop(peso.id);
            return;
        }

        if (curr == end) {
            if (route.peso_Total < best.peso_Total) {
                best = route;
            }
            route.pop(peso.id);
            return;
        }

        Node rand = temp.dest.inicio;
        Node rand_ = temp.peso.inicio;
        while (rand != null) {
            //Check if dest is already in the route
            if (route.buscar(rand.id)) {
                rand = rand.next;
                rand_ = rand_.next;
                continue;
            }
            route.insert(rand.id, rand_.id);
            route_Rec(rand.id, end, best, route, rand_);
            //route_Rec(dest.next, peso.next, end, best, route);
            rand = rand.next;
            rand_ = rand_.next;
        }
        return;
    }
    
    //Graphviz Grafo no dirigido (Iterate through the list and only add arrows for numbers that are bigger than the index)
}
