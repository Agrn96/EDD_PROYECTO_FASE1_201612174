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

        String dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion, id_Municipio;
        Node_Clientes next;
        public Node_Clientes() {
            dpi = null;
            nombre_Completo = null;
            nombre_Usuario = null;
            correo = null;
            pass = null;
            tel = null;
            direccion = null;
            id_Municipio = null;
            next = null;
        }
        
        public Node_Clientes(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, String id_Municipio){
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
    public void insert(String dpi, String nombre_Completo, String nombre_Usuario, String correo, String pass, String tel, String direccion, String id_Municipio) {
        Node_Clientes newNode = new Node_Clientes(dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion, id_Municipio);
        if (start == null) {
            start = newNode;
        } else if (fin == null) {
            end = newNode;
            start.next = end;
        } else {
            end.next = newNode;
            end = newNode;
        }
    } 

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
    //Test
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

    public void display() {
        Node temp = this.inicio;
        System.out.println("Peso Total: " + this.peso_Total);
        if(inicio == null){
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
}
