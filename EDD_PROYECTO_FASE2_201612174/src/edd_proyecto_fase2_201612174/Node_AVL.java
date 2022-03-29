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
public class Node_AVL {
    Node_AVL left, right;
    int data, height;
    public Node_AVL(int x){
        this.data = x;
        height = 1;
    }
}
