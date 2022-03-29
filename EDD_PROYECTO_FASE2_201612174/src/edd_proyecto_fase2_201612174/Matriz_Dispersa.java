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
public class Matriz_Dispersa {
    int col_max,row_max;
    Node_MD inicio;
    
    Matriz_Dispersa(){
        this.col_max = 0;
        this.row_max = 0;
        this.inicio = null;
    }
    
    void add_Header_Row(int x){
        Node_MD temp = this.inicio.row;
        while(temp.row != null){
            temp = temp.row;
        }
        while(x > row_max){
            ++row_max;
            Node_MD newNode = new Node_MD(row_max);
            temp.row = newNode;
            temp = temp.row;
        }
    }
    
    void add_Header_Col(int y){
        Node_MD temp = this.inicio.col;
        while(temp.col != null){
            temp = temp.col;
        }
        while(y > col_max){
            ++col_max;
            Node_MD newNode = new Node_MD(col_max);
            temp.col = newNode;
            temp = temp.col;
        }
    }
    
    
    
}
