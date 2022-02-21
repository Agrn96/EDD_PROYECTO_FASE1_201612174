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
            String fileInputPath = "src\\edd_proyecto_fase1_201612174\\" + fileName + ".txt";
            String fileOutputPath = "src\\edd_proyecto_fase1_201612174\\" + fileName + ".jpg";

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

    public void reportes(Lista temp, int x) throws IOException {
        String fileName = "reporte";
        FileWriter myWriter = new FileWriter("src\\edd_proyecto_fase1_201612174\\" + fileName + ".txt");
        int i = 0;
        Node current = temp.inicio;
        myWriter.write("digraph G\n{\n\trankdir=\"TB\"\n");
        if (x == 1) {
            while (current != null && i < 5) {
                myWriter.write("\nR" + (i + 1) + " [label=\"Cliente : " + current.id + " IMG C :" + current.c + "\"];");
                current = current.next;
                i++;
            }
        } else if (x==2) {
            while (current != null && i < 5) {
                myWriter.write("\nR" + (i + 1) + " [label=\"Cliente : " + current.id + " IMG BW :" + current.bw + "\"];");
                current = current.next;
                i++;
            }
        }
        for (int j = 1; j < 5; j++) {
            myWriter.write("\nR" + (j) + " -> R" + (j + 1));
        }

        myWriter.write("\n");
        myWriter.write("}");
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

    public void reportes(Node temp) throws IOException {
        String fileName = "reporte";
        FileWriter myWriter = new FileWriter("src\\edd_proyecto_fase1_201612174\\" + fileName + ".txt");
        myWriter.write("digraph G\n{\n\trankdir=\"TB\"\n");
        myWriter.write("R1 [label=\"Cliente " + temp.id + " IMG C: " + temp.c + " IMG BW: " + temp.bw + " TURNOS: " + (temp.turnoF - temp.turnoI) + "\"];\n\tR1");
        myWriter.write("\n");
        myWriter.write("}");
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

    public void document_(Lista colaInicial, Lista espera, Lista ventC, Lista ventT, Lista colaBW, Lista colaC, String fileName) throws IOException {
        Node current = colaInicial.inicio;
        try {
            FileWriter myWriter = new FileWriter("src\\edd_proyecto_fase1_201612174\\" + fileName + ".txt");
            myWriter.write("digraph G\n{\n\trankdir=\"TB\"\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola Inicial
            int i = 0;
            while (current != null) {
                myWriter.write("\tCI" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c + "\",style=filled, color=white];\n");
                i++;
                current = current.next;
            }
            myWriter.write("\n\nsubgraph cluster_CI {\n\tstyle=filled;\n\tcolor=lightgrey;\n\tlabel=\"Cola Inicial\"\n\t");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("CI" + (j) + ";");
                } else {
                    myWriter.write("CI" + (j) + " -> ");
                }
            }
            myWriter.write("\n}");
///////////////////////////////////////////////////////////////////////////////////////////////////// Ventanillas
            i = 0;
            int k = 0;
            current = ventC.inicio;
            Node current_ = ventT.inicio;
            myWriter.write("\nsubgraph cluster_vent" + i + "{\n");
            while (current != null) {
                myWriter.write("subgraph cluster_v" + i + "{");
                myWriter.write("\nVC" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c + "\"];");
                Node temp = current_.info.inicio;
                myWriter.write("\nVT" + i + " [label=\"Ventanilla: " + (i + 1) + "\"];");

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
                myWriter.write("}\n");
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
            myWriter.write("\nlabel=\"Ventanillas\";\n}");
///////////////////////////////////////////////////////////////////////////////////////////////////// Lista de Espera
            myWriter.write("\n");
            i = 0;
            current = espera.inicio;
            
            while (current != null) {
                myWriter.write("\tE" + i + " [label=\"Cliente: " + current.id + " IMG BW: " + current.bw + " IMG C: " + current.c + "\"];\n");
                i++;
                current = current.next;
            }
            myWriter.write("\nsubgraph cluster_Espera {\nstyle=filled\ncolor=lightyellow\n");
            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    myWriter.write("E" + (j) + " -> E0");
                } else {
                    myWriter.write("E" + (j) + " -> ");
                }
            }
            myWriter.write("\nlabel=\"Lista de Espera\"\n}\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola BW
            myWriter.write("\n");
            i = 0;
            myWriter.write("subgraph cluster_CBW {\nstyle=filled\ncolor=lightgrey\n");
            myWriter.write("\nCBW" + i + " [label=\"Impresora Black & White\"];");
            current = colaBW.inicio;
            while (current != null) {
                myWriter.write("\nCBW" + (i + 1) + " [label=\"IMG BW\"];");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i + 1; j++) {
                if (j == i) {
                    myWriter.write("CBW" + (j));
                } else {
                    myWriter.write("CBW" + (j) + " -> ");
                }
            }
            myWriter.write("\n}\n");
///////////////////////////////////////////////////////////////////////////////////////////////////// Cola C
            myWriter.write("\n");
            i = 0;
            myWriter.write("subgraph cluster_CC {\nstyle=filled\ncolor=lightblue\n");
            myWriter.write("\nCC" + i + " [label=\"Impresora Colour\"];");
            current = colaC.inicio;
            while (current != null) {
                myWriter.write("\nCC" + (i + 1) + " [label=\"IMG C\"];");
                i++;
                current = current.next;
            }
            myWriter.write("\n");
            for (int j = 0; j < i + 1; j++) {
                if (j == i) {
                    myWriter.write("CC" + (j));
                } else {
                    myWriter.write("CC" + (j) + " -> ");
                }
            }
            
            myWriter.write("}\nImpresora -> CC0\n");
            myWriter.write("Impresora -> CBW0\n");

            myWriter.write("label=\"Impresoras\"\n}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
