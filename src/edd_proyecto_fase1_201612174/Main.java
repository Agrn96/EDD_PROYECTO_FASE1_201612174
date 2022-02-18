/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        int n, opcion = 0, turno = 0;

        Lista colaInicial = new Lista();
        Lista espera = new Lista();
        Lista ventanillaC = new Lista(0);
        Lista ventanillaT = new Lista(0, 0);
        Lista colaBW = new Lista();
        Lista colaC = new Lista();
        Lista historia = new Lista();

        opcion = menu();
        while (opcion != 6) {
            if (opcion == 1) {
                System.out.println("1. Carga masiva de clientes\n"
                        + "2. Cantidad de ventanillas");
                opcion = myObj.nextInt();
                if (opcion == 1) {
                    System.out.println("Placeholder");
                    opcion = menu();
                } else if (opcion == 2) {
                    System.out.println("Ingresar Parametros Iniciales: \n"
                            + "Numero de Ventanillas (n): ");
                    n = myObj.nextInt();
                    System.out.println("Numero de Ventanillas: " + n);
                    ventanillaC = new Lista(n);
                    ventanillaT = new Lista(n, n);
                    opcion = menu();
                } else {
                    System.out.println("Valor no es valido, regresando al menu principal");
                    opcion = menu();
                }
            } else if (opcion == 2) {
                System.out.println("2. Ejecutar paso");
                colaInicial.addRandom(turno);

                if (colaBW.inicio != null) {
                    colaBW.update(espera, historia, turno, 0);//BW Printer returns 1 image per turn
                }
                if (colaC.inicio != null) {
                    colaC.update(espera, historia, turno, 1);//Color Printer returns 1 image per every 2 turns
                }

                ventanillaC.upload(espera, ventanillaT, colaC, colaBW);//Clients upload a single image to the stack

                if (colaInicial.inicio != null) {
                    if (ventanillaC.place(colaInicial.inicio)) {//Pass the head of the line to an available window
                        colaInicial.pop();
                    }
                }

                turno++;
                opcion = menu();
                continue;
            } else if (opcion == 3) {
                System.out.println("3. Estado en memoria de las estructuras");// Graphviz tomorrow
            } else if (opcion == 4) {
                System.out.println("4. Reportes");
                System.out.println("--------------------------");
                historia.display();
                System.out.println("--------------------------");
                System.out.println("1. Top 5 Clientes con mayor cantidad de imagenes a color.\n"
                        + "2. Top 5 Clientes con menor cantidad de imagenes en blanco y negro.\n"
                        + "3. Informacion del Cliente que mas pasos estuvo en el sistema.\n"
                        + "4. Datos de un cliente en especifico.\n");
                opcion = myObj.nextInt();
                if (opcion == 1) {
                    Lista rand = bubbleSort1(historia);
                    Node temp = rand.inicio;
                    int i = 0;
                    while (i < 5 && temp != null) {
                        System.err.println((i + 1) + ") " + temp.nombre + ": ID:" + temp.id + " ---Colores: " + temp.c);
                        temp = temp.next;
                        i++;
                    }
                    rand.display();
                    opcion = menu();
                } else if (opcion == 2) {
                    Lista rand = bubbleSort2(historia);
                    Node temp = rand.inicio;
                    int i = 0;
                    while (i < 5 && temp != null) {
                        System.err.println((i + 1) + ") " + temp.nombre + ": ID:" + temp.id + " ---BW: " + temp.bw);
                        temp = temp.next;
                        i++;
                    }
                    rand.display();
                    opcion = menu();
                } else if (opcion == 3) {
                    Node high = maxTime(historia);
                    System.out.println(high.id + " : " + high.nombre + " : Turnos-" + (high.turnoF - high.turnoI));
                    opcion = menu();
                } else if (opcion == 4) {
                    System.out.println("ID del cliente: ");
                    int u = myObj.nextInt();
                    Node search = search(historia, u);
                    System.out.println("ID: " + search.id + "\nNombre:" + search.nombre + "\nIMG Colores: " + search.c + "\nIMG BW: " + search.bw);
                    opcion = menu();
                } else {
                    System.out.println("Opcion invalido: Regresando al Menu Principal");
                }
            } else if (opcion == 5) {
                System.out.println("5. Acerca De:\nAlberto Gabriel Reyes Ning\n201612174");
            } else if (opcion == 6) {
                System.out.println("Saliendo del Aplicacion");
                System.exit(0);
            } else {
                System.out.println("Opcion Invalido");
                opcion = menu();
            }
        }
    }

    public static int menu() {
        Scanner myObj = new Scanner(System.in);
        int opcion = 0;
        System.out.println("1. Parametros Iniciales\n"
                + "2. Ejecutar Paso\n"
                + "3. Estado en memoria de las estructuras\n"
                + "4. Reportes\n"
                + "5. Acerca De\n"
                + "6. Salir");
        try {
            opcion = myObj.nextInt();
        } catch (Exception e) {
            throw e;
        }
        return opcion;
    }

    public static Lista bubbleSort1(Lista historial) { // Colores
        Lista temp = historial;
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

    public static Lista bubbleSort2(Lista historial) { // BW
        Lista temp = historial;
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

    public static Node maxTime(Lista historial) {
        int max = 0;
        Node high = new Node();
        Node current = historial.inicio;
        while (current != null) {
            if ((current.turnoF - current.turnoI) > max) {
                max = current.turnoF - current.turnoI;
                high = current;
            }
            current = current.next;
        }
        return high;
    }

    public static Node search(Lista historial, int id) {
        Node current = historial.inicio;
        while(current != null){
            if(current.id == id){
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
