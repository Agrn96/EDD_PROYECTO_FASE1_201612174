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
    int id;
    Node_AVL left, right;
    int height;
    Long data;
    ABB node_Access;

    Node_AVL(Long data) {
        this.data = data;
        height = 1;
    }

    public Node_AVL(ABB newNode) {
        this.data = newNode.raiz.data;
        height = 1;
        this.node_Access = newNode;
    }
}
