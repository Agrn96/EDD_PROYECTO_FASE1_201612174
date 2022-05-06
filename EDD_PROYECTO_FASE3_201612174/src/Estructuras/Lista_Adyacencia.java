/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import edd_proyecto_fase3_201612174.GraphViz;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista_Adyacencia {

    Node inicio, fin;
    int size;
    public Lista best;
    public int routes[];

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
        routes = new int[10];
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
        try {
            route_Rec(temp.id, end, route, temp.peso.inicio);
            System.out.println("Path Found");
            best.display();
        } catch (Exception ignored) {
            System.out.println("");
        }

        if(routes[0] == 0){
            routes[0] = best.peso_Total;
            this.graficar(routes);
        } else {
            if(routes[9] == 0){
                for(int i = 0; i < 10; i++){
                    if(routes[i] == 0){
                        routes[i] = best.peso_Total;
                        this.graficar(routes);
                    }
                }
            } else {
                Arrays.sort(routes);
                if(routes[0] < best.peso_Total){
                    routes[0] = best.peso_Total;
                    Arrays.sort(routes);
                    this.graficar(routes);
                }
            }
        }
    }

    public void route_Rec(int curr, int end, Lista route, Node peso) {
        if (curr == 0) {
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
        return;
    }

    public void graficar(Lista lugares) {
        try {
            FileWriter myWriter = new FileWriter("src\\Salidas\\Rutas.txt");
            myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\nedge [arrowhead= none]\n");
            myWriter.write(graficadora(inicio, lugares));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("Rutas");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Node inicio, Lista lugares) {
        String cadena = "";

        if (this.size == 0) {
            System.out.println("Error: Rutas Vacio");
            return cadena;
        }
        Node temp = this.inicio;
        String nombre;
        while (temp != null) {
            //cadena += "\nnodo" + temp.id;
            nombre = lugares.buscar_Nombre(temp.id);
            cadena += "\nnodo" + temp.id + " [label = \"" + (nombre.equals("") ? temp.id : nombre) + " | ID: " + temp.id + "\"]";
            if (temp.dest.inicio == null) {
                temp = temp.next;
                continue;
            } else {
                Node temp_ = temp.dest.inicio;
                Node rand = temp.peso.inicio;
                while (temp_ != null) {
                    if (temp_.id > temp.id) {
                        cadena += "\nnodo" + temp.id + " -> nodo" + temp_.id + "[label = \"" + rand.id + "\"]";
                    }
                    temp_ = temp_.next;
                    rand = rand.next;
                }
            }
            temp = temp.next;
        }
        return cadena;
    }

    public void graficar() {
        try {
            FileWriter myWriter = new FileWriter("src\\Salidas\\Lista_Adyacencia.txt");
            myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\nedge [arrowhead= none]\n");
            myWriter.write(graficadora(inicio));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("Lista_Adyacencia");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Node inicio) {
        String cadena = "";

        if (this.size == 0) {
            System.out.println("Error: Rutas Vacio");
            return cadena;
        }
        Node temp = this.inicio;
        while (temp != null) {
            cadena += "\nnodo" + temp.id + " [label = \"ID: " + temp.id + "\"]";
            if (temp.dest.inicio == null) {
                temp = temp.next;
                continue;
            } else {
                Node temp_ = temp.dest.inicio;
                cadena += "\nnodo" + temp.id + " -> nodo" + temp.id + "_" + temp_.id;
                cadena += "\nnodo" + temp.id + "_" + temp_.id + "[label = \"ID: " + temp_.id + "\"]";
                while (temp_.next != null) {

                    cadena += "\nnodo" + temp.id + "_" + temp_.id + "[label = \"ID: " + temp_.id + "\"]";
                    cadena += "\nnodo" + temp.id + "_" + temp_.next.id + "[label = \"ID: " + temp_.next.id + "\"]";
                    cadena += "\nnodo" + temp.id + "_" + temp_.id + " -> nodo" + temp.id + "_" + temp_.next.id;

                    temp_ = temp_.next;
                }
            }
            temp = temp.next;
        }

        return cadena;
    }
    
    public void graficar(int[] routes_) {
        try {
            FileWriter myWriter = new FileWriter("src\\Salidas\\RRR.txt");
            myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\nedge [arrowhead= none]\n");
            myWriter.write(graficadora(routes_));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("RRR");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(int[] routes_) {
        String cadena = "";

        for(int i = 0; i < routes_.length; i++){
            cadena += "\nnodo" + i + " [label = \"" + routes_[i] + "\"]";
        }
        
        for(int i = 0; i < routes_.length - 1; i++){
            cadena += "\nnodo" + i + " -> ";
        } cadena += "\nnodo" + 9;
        
        return cadena;
    }
}
