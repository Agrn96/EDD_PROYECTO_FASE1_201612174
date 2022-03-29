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
public class Node_MD {
    Node_MD col, row, node_Access, right, left, up, down;
    int col_No, row_No, id;
    String data;
    
    Node_MD(int x, int y, String data){
        //Nodes inside Matrix
        this.right = null;
        this.left = null;
        this.up = null;
        this.down = null;
        this.row_No = x;
        this.col_No = y;
        this.data = data;
    }
    
    Node_MD(int id){
        //Header Nodes
        this.col = null;
        this.row = null;
        this.node_Access = null;
        this.id = id;
    }
    
}
