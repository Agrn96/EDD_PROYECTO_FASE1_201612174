/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase3_201612174;

import Estructuras.Lista;
import Estructuras.Lista_Adyacencia;
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
        //Lista de lugares
        //Lista lugares = new Lista();
        Lista clientes = new Lista();
        clientes.insert("3001554250101", "Alberto Reyes", "Codeprentice", "agrn96p@gmail.com", "1234", "123456", "random", "123");
        clientes.insert("123","123","123","123","123","123","123","123");
        clientes.display_Clientes();
        
        Lista lugares = new Lista();
        lugares.insert(17, "Guatemala", "Amatitlan", true);
        lugares.insert(13,"Guatemala", "Villa Nueva", false);
        lugares.display_Lugares();
        
        System.out.println("");
        Lista_Adyacencia rutas = new Lista_Adyacencia();
        rutas.insertar(8, 5, 2);
        rutas.insertar(17,13,5);
        rutas.insertar(17,16,3);
        rutas.insertar(14,13,3);
        rutas.insertar(14, 16, 1);
        rutas.insertar(8, 13, 5);
        rutas.insertar(8, 16, 2);
        rutas.display();
        
        rutas.ruta(5, 17);
        
        System.out.println("\nTest");
        //Tabla Hash si funciona
//        Tabla_Hash hash_ = new Tabla_Hash(0);
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username");
//        String userName = "";
//        userName = myObj.nextLine();
//        while (!userName.equals("0")) {
//              // Read user input
//            hash_.insertar(userName);
//            System.out.println("Carga: " + hash_.carga);
//            if(hash_.carga > 0.75){
//                Tabla_Hash hash_0 = new Tabla_Hash(hash_.size+1);
//                for(int i = 0; i < hash_.primos[hash_.size]; i++){
//                    if(hash_.hash.dpi[i] == null){
//                        continue;
//                    } else {
//                        hash_0.insertar(hash_.hash.dpi[i]);
//                    }
//                }
//                hash_ = hash_0;
//            }
//            
//            System.out.println("New Addition: " + userName + "\n");
//            hash_.display();
//            userName = myObj.nextLine();
//        }
//        
//        hash_.buscar("56");
//    }
    }
}
