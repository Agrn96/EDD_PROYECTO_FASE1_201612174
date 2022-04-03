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
public class AVL {
    Long id;
    Node_AVL raiz;

    public AVL() {
        raiz = null;
    }

    public int height(Node_AVL x) {
        if (x == null) {
            return 0;
        } else {
            return x.height;
        }
        //Math.max(3, 5);
    }

    public Node_AVL rightRotate(Node_AVL x) {
        Node_AVL temp = x.left;
        Node_AVL temp_ = temp.right;
        temp.right = x;
        x.left = temp_;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;
        return temp;
    }

    public Node_AVL leftRotate(Node_AVL x) {
        Node_AVL temp = x.right;
        Node_AVL temp_ = temp.left;
        temp.left = x;
        x.right = temp_;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    public int getBalance(Node_AVL x) {
        if (x == null) {
            return 0;
        } else {
            return height(x.left) - height(x.right);
        }
    }
    
    public Node_AVL insert(Node_AVL x, ABB newNode){
        Long data = newNode.raiz.data;
        if(x == null){
            return (new Node_AVL(newNode));
        }
        
        if(data < x.data){
            x.left = insert(x.left, newNode);
        } else if (data > x.data){
            x.right = insert(x.right, newNode);
        } else {
            return x;
        }
        
        x.height = 1 + Math.max(height(x.left), height(x.right));
        
        int balance = getBalance(x);
        
        if( balance > 1 && data < x.left.data){
            return rightRotate(x);
        }
        if(balance < -1 && data > x.right.data){
            return leftRotate(x);
        }
        if(balance > 1 && data > x.left.data){
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }
        if(balance < -1 && data < x.right.data){
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }
        return x;
    }
    
    public Node_AVL insert(Node_AVL x, Long data){
        if(x == null){
            return (new Node_AVL(data));
        }
        
        if(data < x.data){
            x.left = insert(x.left, data);
        } else if (data > x.data){
            x.right = insert(x.right, data);
        } else {
            return x;
        }
        
        x.height = 1 + Math.max(height(x.left), height(x.right));
        
        int balance = getBalance(x);
        
        if( balance > 1 && data < x.left.data){
            return rightRotate(x);
        }
        
        if(balance < -1 && data > x.right.data){
            return leftRotate(x);
        }
        
        if(balance > 1 && data > x.left.data){
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }
        
        if(balance < -1 && data < x.right.data){
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }
        
        return x;
    }
    
    public void preOrder(Node_AVL x){
        if(x != null){
            System.out.print(x.data + " ");
            preOrder(x.left);
            preOrder(x.right);
        }
    }
    
    public void postOrder(Node_AVL x){
        if(x != null){
            postOrder(x.left);
            postOrder(x.right);
            System.out.print(x.data + " ");
        }
    }
    
    public void enOrden(Node_AVL x){
        if(x != null){
            enOrden(x.left);
            System.out.print(x.data + " ");
            enOrden(x.right);
        }
    }
    
    public ABB search(Long data){
        Node_AVL temp = this.raiz;
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
}
