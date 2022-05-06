/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import edd_proyecto_fase3_201612174.GraphViz;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista { //Lista para guardar rutas

    Node inicio, fin;
    Node_Clientes start, end;
    int peso_Total;

    public class Node {

        int id;
        String departamento, nombre;
        Boolean sn_sucursal;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int id) {
            this.id = id;
            this.next = null;
        }

        public Node(int id, String departamento, String nombre, Boolean sn_sucursal) {
            this.id = id;
            this.departamento = departamento;
            this.nombre = nombre;
            this.sn_sucursal = sn_sucursal;
            this.next = null;
        }
    }

    public class Node_Clientes {

        public String dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion;
        public int id_Municipio;
        public Node_Clientes next;

        public Node_Clientes() {
            dpi = null;
            nombre_Completo = null;
            nombre_Usuario = null;
            correo = null;
            pass = null;
            tel = null;
            direccion = null;
            id_Municipio = 0;
            next = null;
        }

        public Node_Clientes(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, int id_Municipio) {
            this.dpi = dpi;
            this.nombre_Completo = nombre_Completo;
            this.nombre_Usuario = nombre_Usuario;
            this.correo = correo;
            this.pass = pass;
            this.tel = tel;
            this.direccion = direccion;
            this.id_Municipio = id_Municipio;
            next = null;
        }

        public void mod(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, int id_Municipio) {
            this.nombre_Completo = nombre_Completo;
            this.nombre_Usuario = nombre_Usuario;
            this.correo = correo;
            this.pass = pass;
            this.tel = tel;
            this.direccion = direccion;
            this.id_Municipio = id_Municipio;
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

    //Insert function for Client list
    public void insert(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, int id_Municipio) {
        Node_Clientes newNode = new Node_Clientes(dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion, id_Municipio);
        if (start == null) {
            start = newNode;
        } else if (end == null) {
            end = newNode;
            start.next = end;
        } else {
            end.next = newNode;
            end = newNode;
        }
    }

//    public void modificar(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, int id_Municipio) {
//        Node_Clientes newNode = new Node_Clientes(dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion, id_Municipio);
//        Node_Clientes temp = this.start;
//
//        while (temp.dpi != dpi || temp != null) {
//            temp = temp.next;
//        }
//        //temp.mod(newNode);
//    }

    public void insert(int id, String departamento, String nombre, Boolean sn_sucursal) {
        Node newNode = new Node(id, departamento, nombre, sn_sucursal);
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

    public String buscar_Nombre(int id) { //Store Name
        if (inicio == null) {
            System.out.println("Error: Lista Vacio");
            return "";
        }
        Node temp = inicio;
        while (temp != null) {
            if (temp.id == id) {
                return temp.nombre;
            }
            temp = temp.next;
        }
        return "";
    }
    
    public Node_Clientes buscar_Cliente(String dpi){
        if (start == null) {
            System.out.println("Error: Lista Vacio");
            return null;
        }
        Node_Clientes temp = start;
        while (temp != null) {
            System.out.println("Searching: " + temp.dpi);
            if (temp.dpi.equals(dpi)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void display() {
        Node temp = this.inicio;
        System.out.println("Peso Total: " + this.peso_Total);
        if (inicio == null) {
            System.out.println("Error: Lista Vacio");
            return;
        }

        while (temp.next != null) {
            System.out.print(temp.id + " -> ");
            temp = temp.next;
        }
        System.out.print(temp.id);
    }

    public void display_Lugares() {
        Node temp = this.inicio;
        System.out.println("\nLugares: ");
        while (temp != null) {
            System.out.println(temp.id + " : " + temp.nombre);
            temp = temp.next;
        }
    }

    public void display_Clientes() {
        Node_Clientes temp = this.start;
        System.out.println("\nClientes: ");
        while (temp != null) {
            System.out.println(temp.dpi + " : " + temp.nombre_Completo);
            temp = temp.next;
        }
    }

    public void graficar(int x) { //Graficar lista de lugares
        try {

            if (x == 0) {
                FileWriter myWriter = new FileWriter("src\\Salidas\\Lugares.txt");
                myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\n");
                myWriter.write(graficadora(inicio));
                myWriter.write("}");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                GraphViz.imprimir("Lugares");
            } else if(x==1){
                FileWriter myWriter = new FileWriter("src\\Salidas\\Clientes.txt");
                myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\n");
                myWriter.write(graficadora(start));
                myWriter.write("}");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                GraphViz.imprimir("Clientes");
            } else if(x==2){
                FileWriter myWriter = new FileWriter("src\\Salidas\\Best.txt");
                myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\n");
                myWriter.write(graficadora(inicio));
                myWriter.write("}");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                GraphViz.imprimir("Best");
            } else if(x==3){
                FileWriter myWriter = new FileWriter("src\\Salidas\\RRR.txt");
                myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\n");
                myWriter.write(graficadora(inicio));
                myWriter.write("}");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                GraphViz.imprimir("RRR");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Node inicio) {
        String cadena = "";

        if (this.inicio == null) {
            System.out.println("Error: Rutas Vacio");
            return cadena;
        }
        Node temp = this.inicio;
        while (temp.next != null) {
            //cadena += "\nnodo" + temp.id;
            cadena += "\nnodo" + temp.id + " [label = \"ID: " + temp.id + " | Nombre: " + temp.nombre + "\"]";
            cadena += "\nnodo" + temp.id + " -> " + "nodo" + temp.next.id;
            temp = temp.next;
        }
        cadena += "\nnodo" + temp.id + " [label = \"ID: " + temp.id + " | Nombre: " + temp.nombre + "\"]";
        return cadena;
    }

    public String graficadora(Node_Clientes inicio) {
        String cadena = "";

        if (this.start == null) {
            System.out.println("Error: Rutas Vacio");
            return cadena;
        }
        Node_Clientes temp = this.start;
        while (temp.next != null) {
            //cadena += "\nnodo" + temp.id;
            cadena += "\nnodo" + temp.dpi + " [label = \"" + temp.nombre_Completo + " | " + temp.dpi + "\"]";
            cadena += "\nnodo" + temp.dpi + " -> " + "nodo" + temp.next.dpi;
            temp = temp.next;
        }
        cadena += "\nnodo" + temp.dpi + " [label = \"" + temp.nombre_Completo + " | " + temp.dpi + "\"]";
        return cadena;
    }
}
