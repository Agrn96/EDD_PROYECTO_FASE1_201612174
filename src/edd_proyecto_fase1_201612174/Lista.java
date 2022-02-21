package edd_proyecto_fase1_201612174;

import java.lang.Math;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista {

    public Node inicio = null;
    public Node fin = null;
    public String[] fName = {"Azhar", "Pippin", "Acantha", "Sarpedon", "Redd", "Salome", "Karoline", "Gobnat", "Hamnet", "Lyydia"};
    public String[] lName = {"Aparna", "Romana", "Jedidiah", "Darya", "Vishal", "Dipa", "Nicomedes", "Haven", "Feidlimid", "Fiene"};
    public int id = 0, turno = 0;
    Pila store = new Pila();

    Lista() {
    }

    Lista(int n) {
        for (int i = 0; i < n; i++) {
            Node newNode = new Node();
            add(newNode, 0);
        }
    }

    Lista(int n, int t) {
        for (int i = 0; i < n; i++) {
            Pila store = new Pila();
            add(store);
        }
    }

    public void add(String nombre, int c, int bw, int turno) { //Add a node to the initial queue and waiting queue
        this.id++;
        Node newNode = new Node(this.id, nombre, c, bw, turno);
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

    public void add(Node temp, int x) { //Creates the initial window list and adds to the waiting list
        temp.id = x;
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

    public void add(Pila temp) { //Creates the initial window list and adds to the waiting list
        Node pila = new Node(temp);
        if (inicio == null) { //Agrega al inicio
            inicio = pila;
        } else if (inicio.next == null) { //Agregar despues del inicio
            fin = pila;
            inicio.next = fin;
        } else { //Agregar al ultimo del circulo
            fin.next = pila;
            fin = pila;
        }
    }

    public boolean place(Node temp) { // if place == true; pop from initial list;
        Node current = inicio;
        int i = 1;
        while (current != null) {
            if (current.id == 0) {
                current.Copy(temp);
                System.out.println("\nCliente " + temp.id + " ha sido movido a la Ventanilla " + i);
                return true;
            } else {
                i++;
                current = current.next;
            }
        }
        System.out.println("No hay ventanilla disponible");
        return false;
    }

    public void upload(Lista espera, Lista ventanillaT, Lista colaC, Lista colaBW) {
        Node current = this.inicio; //VentanillaC           Lista de Pilas
        Node currentT = ventanillaT.inicio; //VentanillaT   Lista Normal
        int i = 1;
        while (current != null) {
            if (current.id == 0) {
                i++;
                current = current.next;
                currentT = currentT.next;
                continue;
            } else {
                if (current.tempBW == current.bw && current.tempC == current.c) {//pila vaciar
                    Node temp = new Node();
                    temp.Copy(current);
                    System.out.println("\nCliente " + current.id + " ha sido movido a la Lista de Espera");
                    espera.add(temp, current.id);  //Moved to the waiting list;
                    current.Clear(); //Clearing client information as he has been passed to the waiting list;
                    while (currentT.info.inicio != null) {//1 for BW, 2 for Color
                        try {
                            if (currentT.info.fin.data == 1) {
                                colaBW.add(currentT.info.fin, -1);
                                currentT.info.pop();
                            } else if (currentT.info.fin.data == 2) {
                                colaC.add(currentT.info.fin, -1);
                                currentT.info.pop();
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                    i++;
                    current = current.next;
                    currentT = currentT.next;
                    continue;
                }

                if (current.tempBW != current.bw) {
                    System.out.println("\nCliente " + current.id + " ha Entregado un IMG BW a la Ventanilla " + i);
                    current.tempBW++;
                    currentT.info.push(1);
                    i++;
                    current = current.next;
                    currentT = currentT.next;
                    continue;
                }
                if (current.tempC != current.c) {
                    System.out.println("\nCliente " + current.id + " ha Entregado un IMG C a la Ventanilla " + i);
                    current.tempC++;
                    currentT.info.push(2);
                    i++;
                    current = current.next;
                    currentT = currentT.next;
                    continue;
                }
            }
        }
    }

    public void addRandom(int turno) {//Adds random people to the waiting queue
        int x = ((int) (Math.random() * 10)) % 4;
        if (x == 0) {
            System.out.println("Clientes Nuevos: " + x);
        } else {
            System.out.println("Clientes Nuevos: " + x);
            for (int i = 0; i < x; i++) {
                int c = ((int) (Math.random() * 10)) % 6;
                int bw = (c == 0) ? (((int) (Math.random() * 10)) % 5) + 1 : ((int) (Math.random() * 10)) % 6;
                String nombre = fName[((int) (Math.random() * 10))] + " " + lName[((int) (Math.random() * 10))];
                add(nombre, c, bw, turno);
                
                System.out.println("\nCliente " + (id+1) + ": \nNombre: " + nombre
                        + "\nIMG C: " + c + " \nIMG BW: " + bw);
            }
        }
    }

    public void pop() { //Removes people from the initial/waiting queue
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else {
            Node temp = inicio.next;
            inicio.next = null;
            inicio = temp;
        }

    }

    public void delete(int id) {//Assuming user starts counting from 1, change i to 0 otherwise
        Node current = inicio;
        Node temp = current;
        if (inicio == null) {
            System.out.println("Lista esta vacio");
        } else if (current.id == id) {
            current = inicio.next;
            inicio = current;
        } else {
            while (current != null) {
                if (current.id == id) {
                    temp.next = current.next;
                    current.next = null;
                    break;
                }
                temp = current;
                current = current.next;
            }
        }
    }

    public void display() {
        Node current = inicio;
        if (inicio == null) {
            System.out.println("Ventanilla esta disponible");
        } else {
            do {
                if (current.id == 0) {
                    current.info.display();
                } else if (current.id == -1) {
                    if (current.data == 1) {
                        System.out.println("Imagen BW");
                    } else {
                        System.out.println("Imagen C");
                    }
                } else {
                    System.out.println("Cliente " + current.id + ": " + current.nombre + " " + current.bw + ":" + current.c + " || " + current.tempBW + ":" + current.tempC);
                    current.info.display();
                }

                current = current.next;
            } while (current != null);
            System.out.println();
        }
    }

    public void update(Lista espera, Lista historia, int turno, int x) { //Cola BW y C (Listas)
        Node current = espera.inicio;
        if (x == 0) {
            while (current != null) {
                if (current.tempBW != current.bw) {
                    current.tempBW++;
                    System.out.println("\nIMG BW ha sido entregado a Cliente " + current.id + ".");
                    pop();
                    if (current.tempBW == current.bw && current.tempC == current.c) {
                        System.out.println("\nCliente " + current.id + " ha sido movido a la lista de atendidos.");
                        Node temp = new Node();
                        temp.Copy(current);
                        temp.turnoF = turno;
                        historia.add(temp, temp.id);
                        espera.delete(temp.id);
                    } break;
                } current = current.next;
            }
        } else if (x == 1) {
            ++turno;
            if (turno % 2 == 0) {
                while (current != null) {
                    if (current.tempC != current.c) {
                        current.tempC++;
                        System.out.println("\nIMG C ha sido entregado a Cliente " + current.id + ".");
                        pop();
                        if (current.tempBW == current.bw && current.tempC == current.c) {
                            System.out.println("\nCliente " + current.id + " ha sido movido a la lista de atendidos.");
                            Node temp = new Node();
                            temp.Copy(current);
                            temp.turnoF = turno;
                            historia.add(temp, temp.id);
                            espera.delete(temp.id);
                        } break;
                    } current = current.next;
                }
            } else if (turno % 2 == 1){
                System.out.println("\nUn Turno mas para imprimir imagen de color");
            }
        }
    }
    
    public Lista bubbleSort1() { // Colores
        Lista temp = this;
        Node current = temp.inicio;
        Node trail = current;
        Boolean sorted = false;

        if (temp.inicio == null) {
            System.out.println("Lista esta vacio");
        } else if (current.next == null) {
            return temp;
        } else if (current.next.next == null) {
            if (current.c < current.next.c) {
                Node rand = current.next;
                rand.next = current;
                current.next = null;
                temp.inicio = rand;
                return temp;
            }
        } else {
            while (sorted == false) {
                sorted = true;
                current = temp.inicio;
                while (current.next != null) {
                    if (temp.inicio.c < temp.inicio.next.c) {
                        Node rand = temp.inicio.next.next;
                        temp.inicio.next.next = temp.inicio;
                        temp.inicio = temp.inicio.next;
                        temp.inicio.next.next = rand;
                        sorted = false;
                        break;
                    }
                    if (current.c < current.next.c) {
                        Node rand = current.next.next;
                        trail.next = current.next;
                        trail.next.next = current;
                        current.next = rand;
                        sorted = false;
                        break;
                    }
                    trail = current;
                    current = current.next;
                }
            }
        }
        return temp;
    }
    
    public Lista bubbleSort2() { // BW
        Lista temp = this;
        Node current = temp.inicio;
        Node trail = current;
        Boolean sorted = false;

        if (temp.inicio == null) {
            System.out.println("Lista esta vacio");
        } else if (current.next == null) {
            return temp;
        } else if (current.next.next == null) {
            if (current.bw > current.next.bw) {
                Node rand = current.next;
                rand.next = current;
                current.next = null;
                temp.inicio = rand;
                return temp;
            }
        } else {
            while (sorted == false) {
                sorted = true;
                current = temp.inicio;
                while (current.next != null) {
                    if (temp.inicio.bw > temp.inicio.next.bw) {
                        Node rand = temp.inicio.next.next;
                        temp.inicio.next.next = temp.inicio;
                        temp.inicio = temp.inicio.next;
                        temp.inicio.next.next = rand;
                        sorted = false;
                        break;
                    }
                    if (current.bw > current.next.bw) {
                        Node rand = current.next.next;
                        trail.next = current.next;
                        trail.next.next = current;
                        current.next = rand;
                        sorted = false;
                        break;
                    }
                    trail = current;
                    current = current.next;
                }
            }
        }
        return temp;
    }
    
    public Node maxTime() {
        int max = 0;
        Node high = new Node();
        Node current = this.inicio;
        while (current != null) {
            if ((current.turnoF - current.turnoI) > max) {
                max = current.turnoF - current.turnoI;
                high = current;
            }
            current = current.next;
        }
        return high;
    }
    
    public Node search(int id) {
        Node current = this.inicio;
        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}