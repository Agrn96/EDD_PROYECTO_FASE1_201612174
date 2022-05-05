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
    Lista best;

    public class Node {

        int id;
        Lista_Adyacencia dest;
        Lista_Adyacencia peso;
        Node next;

        public Node() {
            dest = new Lista_Adyacencia();
            peso = new Lista_Adyacencia();
            this.next = null;
        }

        public Node(int id) {
            dest = new Lista_Adyacencia();
            peso = new Lista_Adyacencia();
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

    public void display() {
        if (this.size == 0) {
            System.out.println("Error: Rutas Vacio");
            return;
        }
        Node temp = this.inicio;
        while (temp != null) {
            System.out.print("\n" + temp.id + " --> ");
            if (temp.dest.inicio == null) {
                temp = temp.next;
                continue;
            } else {
                Node temp_ = temp.dest.inicio;
                while (temp_.next != null) {
                    System.out.print(temp_.id + " -> ");
                    temp_ = temp_.next;
                }
                System.out.print(temp_.id);
            }
            temp = temp.next;
        }
    }

    public void ruta(int start, int end) {
        best = new Lista();
        Lista route = new Lista();
        Node temp = inicio;
        while (temp.id != start) {
            temp = temp.next;
        }//Should be at start position now

        route.insert(temp.id);
        //Recursion
        
//        best = route_Rec(temp.id, end, best, route, temp.peso.inicio);
//        System.out.println("Path Found");
//        best.display();
        
        try{
        route_Rec(temp.id, end, route, temp.peso.inicio);
        System.out.println("Path Found");
        best.display();
        } catch (Exception ignored){
            System.out.println("");
        }
        
        System.out.println("\nBest route discovered");
        best.display();
    }

    public void route_Rec(int curr, int end, Lista route, Node peso) {
        if(curr == 0){
            return;
        }
        
        if (curr == end) {
            if (best.peso_Total == 0) {
                best = new Lista();
                best.copy(route);
                System.out.println("\nFOUND ROUTE1: " + best.peso_Total);
            }
            if (route.peso_Total < best.peso_Total) {
                best = new Lista();
                best.copy(route);
                System.out.println("\nFOUND ROUTE2: " + best.peso_Total);

            }
            route.pop(peso.id);
            return;
        }//Checks if we are at the final destination if not, checks next route

        Node temp = inicio;
        while (temp.id != curr) {
            temp = temp.next;
        }//Should be at current position now

        if (temp.dest.inicio == null) {
            route.pop(peso.id);
            System.out.println("\nFOUND DEAD END: " + route.peso_Total);
            return;
        }

        if (route.peso_Total > best.peso_Total && best.peso_Total != 0) {
            route.pop(peso.id);
            System.out.println("\nFOUND INEFFICIENT ROUTE: " + route.peso_Total);
            return;
        }

        Node rand = temp.dest.inicio;
        Node rand_ = temp.peso.inicio;
        while (rand != null && rand_ != null) {
                System.out.println("RAND: " + rand.id);
            //Check if dest is already in the route
            if (route.buscar(rand.id)) {
                rand = rand.next;
                rand_ = rand_.next;
                continue;
            }
            route.insert(rand.id, rand_.id);
            route_Rec(rand.id, end, route, rand_);
            //route_Rec(dest.next, peso.next, end, best, route);

            rand = rand.next;
            rand_ = rand_.next;
            System.out.println("\nFINDING ROUTE: " + route.peso_Total);
        }
        if (route.inicio != null) {
            route.pop(peso.id);
        }
        System.out.println("WHAT");
        best.display();
        return;
    }

    //Graphviz Grafo no dirigido (Iterate through the list and only add arrows for numbers that are bigger than the index)
}
