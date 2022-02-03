/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;
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
        Scanner myObj = new Scanner(System.in);
        int n, x=1;
        Lista colaInicial = new Lista();
        System.out.println("Ingresar Parametros Iniciales: ");
        System.out.println("Numero de Ventanillas (n): ");
        n = myObj.nextInt();
        
        System.out.println("Cantidad de ventanillas: " + n );
        System.out.println("1 para seguir, 0 para detener");
        
        while(x!=0){
            colaInicial.addRandom();
            colaInicial.display();
            x = myObj.nextInt();
        }
        
    }
}
