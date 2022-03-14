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

        Lista colaInicial = new Lista();        //Cola 
        Lista espera = new Lista();             //Lista DOblemente Enlazada Circular
        Lista ventanillaC = new Lista(0);       //Lista
        Lista ventanillaT = new Lista(0, 0);    //Lista de Pilas
        Lista colaBW = new Lista();             //Cola
        Lista colaC = new Lista();              //Cola
        Lista historia = new Lista();           //Lista
        leer_json leer = new leer_json();
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
                    System.out.println("Ingresar nombre del archivo");
                    myObj.nextLine();
                    String t = myObj.nextLine();
                    //String fileName = "D:\\Documents\\Projects\\EDD_PROYECTO_FASE1_201612174\\src\\edd_proyecto_fase1_201612174\\" + t;
                    String fileName = "src\\edd_proyecto_fase1_201612174\\" + t;
                    System.out.println(fileName);
                    leer.leer(colaInicial, fileName);
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
                    graph.reportes(rand, opcion);
                    graph.graph("reporte");
                    opcion = menu();
                } else if (opcion == 2) {
                    Lista rand = historia.bubbleSort2();
                    graph.reportes(rand, opcion);
                    graph.graph("reporte");
                    opcion = menu();
                } else if (opcion == 3) {
                    Node high = historia.maxTime();
                    graph.reportes(high);
                    graph.graph("reporte");
                    opcion = menu();
                } else if (opcion == 4) {
                    System.out.println("ID del cliente: ");
                    int u = myObj.nextInt();
                    Node search = historia.search(u);
                    graph.reportes(search);
                    graph.graph("reporte");
                    opcion = menu();
                } else {
                    System.out.println("Opcion invalido: Regresando al Menu Principal");
                }
            } else if (opcion == 5) {
                System.out.println("5. Acerca De:\nAlberto Gabriel Reyes Ning\n201612174");
                opcion = menu();
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
}
