/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase3_201612174;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class GraphViz {
    GraphViz(){
        
    }
    
    public static void imprimir(String path, String usuarioID, Long id) {//Images
        try {
            String dotPath = "D:\\Program Files (x86)\\Graphviz\\bin\\dot.exe";
            String fileInputPath = path;
            String fileOutputPath = "src\\Salidas\\" + usuarioID + "\\Images\\" +  id + ".png";

            String tParam = "-Tpng";
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
    
    public static void imprimir(String path, String usuarioID, Long id, int x) {//Capas
        try {
            String dotPath = "D:\\Program Files (x86)\\Graphviz\\bin\\dot.exe";
            String fileInputPath = path;
            String fileOutputPath = "src\\Salidas\\" +usuarioID + "\\Capas\\" +  id + ".png";

            String tParam = "-Tpng";
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
    
    public static void imprimir(String fileName) { //Only used for image of usuarios
        try {
            String dotPath = "D:\\Program Files (x86)\\Graphviz\\bin\\dot.exe";
            String fileInputPath = "src\\Salidas\\" + fileName + ".txt";
            String fileOutputPath = "src\\Salidas\\" + fileName + ".png";

            String tParam = "-Tpng";
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
    /*
    public void graficar(Node_AVL node) {
        try {
            FileWriter myWriter = new FileWriter("src\\Salidas\\reporte1.txt");
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora(node));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("reporte1");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Node_AVL node) {
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
*/
}
