/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase3_201612174;

import Estructuras.Tabla_Hash;
import java.util.Scanner;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Tabla_Hash hash_ = new Tabla_Hash(0);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");
        String userName = "";
        userName = myObj.nextLine();
        while (!userName.equals("0")) {
              // Read user input
            hash_.insertar(userName);
            System.out.println("Carga: " + hash_.carga);
            if(hash_.carga > 0.75){
                Tabla_Hash hash_0 = new Tabla_Hash(hash_.size+1);
                for(int i = 0; i < hash_.primos[hash_.size]; i++){
                    if(hash_.hash.dpi[i] == null){
                        continue;
                    } else {
                        hash_.insertar(hash_.hash.dpi[i]);
                    }
                }
                hash_ = hash_0;
            }
            
            System.out.println("New Addition: " + userName + "\n");
            hash_.display();
            userName = myObj.nextLine();
        }
        
        hash_.buscar("56");
    }

}
