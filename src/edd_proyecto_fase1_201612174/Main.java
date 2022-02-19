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
        
        Graphing graph = new Graphing();

        opcion = menu();
        while (opcion != 6) {
            if (opcion == 1) {
                System.out.println("--------------------------");
                System.out.println("1. Carga masiva de clientes\n"
                        + "2. Cantidad de ventanillas");
                System.out.println("--------------------------");
                opcion = myObj.nextInt();
                if (opcion == 1) {
                    System.out.println("Placeholder");
                    opcion = menu();
                } else if (opcion == 2) {
                    System.out.println("--------------------------");
                    System.out.println("Ingresar Parametros Iniciales: \n"
                            + "Numero de Ventanillas (n): ");
                    System.out.println("--------------------------");
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
                System.out.println("--------------------------");
                System.out.println("2. Ejecutar paso");
                System.out.println("--------------------------");
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
                graph.document_(colaInicial, espera, ventanillaC, ventanillaT, colaBW, colaC, "graph1");
                graph.graph("graph1");
                opcion = menu();
            } else if (opcion == 4) {
                System.out.println("--------------------------");
                System.out.println("4. Reportes");
                System.out.println("--------------------------");
                historia.display();
                System.out.println("--------------------------");
                System.out.println("1. Top 5 Clientes con mayor cantidad de imagenes a color.\n"
                        + "2. Top 5 Clientes con menor cantidad de imagenes en blanco y negro.\n"
                        + "3. Informacion del Cliente que mas pasos estuvo en el sistema.\n"
                        + "4. Datos de un cliente en especifico.\n");
                System.out.println("--------------------------");
                opcion = myObj.nextInt();
                if (opcion == 1) {
                    Lista rand = historia.bubbleSort1();
                    Node temp = rand.inicio;
                    int i = 0;
                    while (i < 5 && temp != null) {
                        System.err.println((i + 1) + ") " + temp.nombre + ": ID:" + temp.id + " ---Colores: " + temp.c);
                        temp = temp.next;
                        i++;
                    } pressAnyKeyToContinue(); //rand.display();
                    opcion = menu();
                } else if (opcion == 2) {
                    Lista rand = historia.bubbleSort2();
                    Node temp = rand.inicio;
                    int i = 0;
                    while (i < 5 && temp != null) {
                        System.err.println((i + 1) + ") " + temp.nombre + ": ID:" + temp.id + " ---BW: " + temp.bw);
                        temp = temp.next;
                        i++;
                    } pressAnyKeyToContinue(); //rand.display();
                    opcion = menu();
                } else if (opcion == 3) {
                    Node high = historia.maxTime();
                    System.out.println(high.id + " : " + high.nombre + " : Turnos-" + (high.turnoF - high.turnoI));
                    pressAnyKeyToContinue();
                    opcion = menu();
                } else if (opcion == 4) {
                    System.out.println("ID del cliente: ");
                    int u = myObj.nextInt();
                    Node search = historia.search(u);
                    System.out.println("ID: " + search.id + "\nNombre:" + search.nombre + "\nIMG Colores: " + search.c + "\nIMG BW: " + search.bw);
                    pressAnyKeyToContinue();
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
        System.out.println("\n--------------------------");
        System.out.println("1. Parametros Iniciales\n"
                + "2. Ejecutar Paso\n"
                + "3. Estado en memoria de las estructuras\n"
                + "4. Reportes\n"
                + "5. Acerca De\n"
                + "6. Salir");
        System.out.println("--------------------------\n");
        try {
            opcion = myObj.nextInt();
        } catch (Exception e) {
            throw e;
        }
        return opcion;
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

}
