/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Graphing {

    public Graphing() {

    }

    public void graph(String fileName) {
        try {
            String dotPath = "D:\\Program Files (x86)\\Graphviz\\bin\\dot.exe";
            String fileInputPath = "D:\\Documents\\Projects\\EDD_PROYECTO_FASE1_201612174\\src\\EDD_PROYECTO_FASE1_201612174\\" + fileName + ".txt";
            String fileOutputPath = "D:\\Documents\\Projects\\EDD_PROYECTO_FASE1_201612174\\src\\EDD_PROYECTO_FASE1_201612174\\" + fileName + ".jpg";

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (IOException e) {
        } finally {
        }
    }

    public void document_(Lista colaInicial, Lista espera, Lista ventC, Lista ventT, Lista colaBW, Lista colaC, String fileName) throws IOException {
        Node current = colaInicial.inicio;
        try {
            FileWriter myWriter = new FileWriter("D:\\Documents\\Projects\\EDD_PROYECTO_FASE1_201612174\\src\\edd_proyecto_fase1_201612174\\" + fileName + ".txt");
            myWriter.write("digraph G\n{\n\trankdir=\"TB\"\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola Inicial
            int i = 0;
            while (current != null) {
                myWriter.write("\tCI" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c + "\"];\n");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("CI" + (j));
                } else {
                    myWriter.write("CI" + (j) + " -> ");
                }
            }
///////////////////////////////////////////////////////////////////////////////////////////////////// Ventanillas
            i = 0;
            int k = 0;
            current = ventC.inicio;
            Node current_ = ventT.inicio;
            while (current != null) {
                myWriter.write("\nVC" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c +  "\"];");
                Node temp = current_.info.inicio;
                myWriter.write("\nVT" + i + " [label=\"Ventanilla: " + (i + 1) + "\"];\n");

                while (temp != null) {
                    myWriter.write("\nIMG" + k + " [label=\"" + (temp.data == 1 ? "IMG BW" : "IMG C") + "\"];");
                    if (temp != current_.info.inicio) {
                        myWriter.write("\nIMG" + (k - 1) + " -> " + "IMG" + k);
                    } else {
                        myWriter.write("\nVT" + i + " -> " + "IMG" + k);
                    }
                    k++;
                    temp = temp.next;
                }
                i++;
                current = current.next;
                current_ = current_.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("VT" + j);

                } else {
                    myWriter.write("VT" + j + " -> ");
                }
            }
            myWriter.write("\n");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("VC" + j);
                } else {
                    myWriter.write("VC" + j + " -> ");
                }
            }
            myWriter.write("\n");
            for (int j = 0; j < i; j++) {
                myWriter.write("\nVC" + j + " -> VT" + j);
            }
///////////////////////////////////////////////////////////////////////////////////////////////////// Lista de Espera
            myWriter.write("\n");
            i = 0;
            current = espera.inicio;
            while (current != null) {
                myWriter.write("\tE" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c  + "\"];\n");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("E" + (j) + " -> E0" );
                } else {
                    myWriter.write("E" + (j) + " -> ");
                }
            }
            myWriter.write("\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola BW
            myWriter.write("\n");
            i = 0;
            myWriter.write("\nCBW" + i + " [label=\"Impresora Black & White\"];");
            current = colaBW.inicio;
            while (current != null) {
                myWriter.write("\nCBW" + (i+1) + " [label=\"IMG BW\"];");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i+1; j++) {
                if (j == i) {
                    myWriter.write("CBW" + (j));
                } else {
                    myWriter.write("CBW" + (j) + " -> ");
                }
            }
            myWriter.write("\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola C
            myWriter.write("\n");
            i = 0;
            myWriter.write("\nCC" + i + " [label=\"Impresora Colour\"];");
            current = colaC.inicio;
            while (current != null) {
                myWriter.write("\nCC" + (i+1) + " [label=\"IMG C\"];");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i+1; j++) {
                if (j == i) {
                    myWriter.write("CC" + (j));
                } else {
                    myWriter.write("CC" + (j) + " -> ");
                }
            }
            myWriter.write("\n");
            
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
