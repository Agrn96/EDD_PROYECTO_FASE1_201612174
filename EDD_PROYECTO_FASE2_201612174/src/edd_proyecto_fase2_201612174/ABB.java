/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase2_201612174;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class ABB {

    Node_ABB raiz = new Node_ABB();

    public ABB() {
    }

    public void insert(int data) {
        Node_ABB temp = this.raiz;
        Node_ABB rand = new Node_ABB(data);
        Boolean status = false;

        while (status == false) {
            if (temp.data == -1) {
                temp.data = data;
                status = true;
                System.out.println(data + " has been inserted as the root");
            } else {
                if (data == temp.data) {
                    System.out.println("Error: Valor Duplicado");
                    break;
                } else if (data > temp.data) {
                    if (temp.right == null) {
                        temp.right = rand;
                        status = true;
                        System.out.println(data + " has been inserted on the right");
                    } else {
                        temp = temp.right;
                    }
                } else if (data < temp.data) {
                    if (temp.left == null) {
                        status = true;
                        temp.left = rand;
                        System.out.println(data + " has been inserted on the left");
                    } else {
                        temp = temp.left;
                    }
                }
            }
        }
    }

    public void displayIO(Node_ABB temp) { //EnOrden
        if (temp == null) {
            return;
        }

        displayIO(temp.left);
        System.out.println(temp.data);
        displayIO(temp.right);
    }

    public void displayPreO(Node_ABB temp) {
        if (temp == null) {
            return;
        }
        System.out.println(temp.data);
        displayPreO(temp.left);
        displayPreO(temp.right);
    }

    public void displayPostO(Node_ABB temp) {
        if (temp == null) {
            return;
        }
        displayPreO(temp.left);
        displayPreO(temp.right);
        System.out.println(temp.data);
    }
    //Graph ABB
    public void graficar(Node_ABB node) {
        try {
            FileWriter myWriter = new FileWriter("src\\edd_tarea5_201612174\\reporte.txt");
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora(node));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            imprimir("reporte");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Node_ABB node) {
        String cadena = "";
        if ((node.left == null) && (node.right == null)) {
            cadena = "nodo" + node.data + "[ label =\"" + node.data + "\"]; \n";
        } else {
            cadena = "nodo" + node.data + " [ label =\"" + node.data + "\"];\n";
        }
        if (node.left != null) {//:C0
            cadena = cadena + graficadora(node.left) + "nodo" + node.data + "->nodo" + node.left.data + "\n";
        }
        if (node.right != null) {//:C1
            cadena = cadena + graficadora(node.right) + "nodo" + node.data + "->nodo" + node.right.data + "\n";
        }
        return cadena;
    }

    public static void imprimir(String fileName) {
        try {
            String dotPath = "D:\\Program Files (x86)\\Graphviz\\bin\\dot.exe";
            String fileInputPath = "src\\edd_tarea5_201612174\\" + fileName + ".txt";
            String fileOutputPath = "src\\edd_tarea5_201612174\\" + fileName + ".jpg";

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
}
