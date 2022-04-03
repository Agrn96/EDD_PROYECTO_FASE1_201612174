/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase2_201612174;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class ABB {

    public Node_ABB raiz;

    public ABB() {
        raiz = new Node_ABB();
    }

    public void insert(Matriz_Dispersa MD) {
        Long data = Long.parseLong(MD.inicio.data); 
        System.out.println("TEST::::" + data);
        Node_ABB temp = this.raiz;
        Node_ABB rand = new Node_ABB(MD);
        Boolean status = false;

        while (status == false) {
            if (temp.data == -1) {
                temp.data = data;
                this.raiz.node_Access = MD;
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
    
    public void insert(Long data) {
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
    
    public Matriz_Dispersa search(Long data){
        Node_ABB temp = this.raiz;
        while(temp != null){
            if(Objects.equals(data, temp.data)){
                return temp.node_Access;
            } 
            if(data > temp.data){
                temp = temp.right;
            } 
            if(data < temp.data){
                temp = temp.left;
            }
        }
        return temp.node_Access;
    }

    public void displayIO(Node_ABB temp, Matriz_Dispersa MD, int go) { //EnOrden
        if (temp == null) {
            return;
        }
        if(MD.count < go){
            MD.add_Matrix(temp.node_Access);
            MD.count++;
        }

        displayIO(temp.left, MD, go);
        System.out.print(temp.data + " ");
        
        displayIO(temp.right, MD, go);
    }

    public void displayPreO(Node_ABB temp, Matriz_Dispersa MD, int go) {
        if (temp == null) {
            return;
        }
        if(MD.count < go){
            MD.add_Matrix(temp.node_Access);
            MD.count++;
        }
        System.out.print(temp.data + " ");
        
        displayPreO(temp.left, MD, go);
        displayPreO(temp.right, MD, go);
    }

    public void displayPostO(Node_ABB temp, Matriz_Dispersa MD, int go) {
        
        if (temp == null) {
            return;
        }
        if(MD.count < go){
            MD.add_Matrix(temp.node_Access);
            MD.count++;
        }
        displayPostO(temp.left, MD, go);
        displayPostO(temp.right, MD, go);
        System.out.print(temp.data + " ");
        
    }
    //Graph ABB
    public void graficar(Node_ABB node, String usuarioID, Long id) {
        try {
            String path = "src\\Salidas\\" + usuarioID + "\\Images\\" + id +".txt";
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora(node));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir(path, usuarioID, id);
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
}
