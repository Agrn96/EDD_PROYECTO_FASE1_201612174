/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase2_201612174;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Node_ABB {
    Node_ABB left;
    Node_ABB right;
    int data;
    Matriz_Dispersa node_Access;
    public Node_ABB(){
        this.left = null;
        this.right = null;
        this.data = -1;
        node_Access = null;
    }
    
    public Node_ABB(Matriz_Dispersa temp){
        this.left = null;
        this.right = null;
        this.data = Integer.parseInt(temp.inicio.data);
        node_Access = temp;
    }
}
