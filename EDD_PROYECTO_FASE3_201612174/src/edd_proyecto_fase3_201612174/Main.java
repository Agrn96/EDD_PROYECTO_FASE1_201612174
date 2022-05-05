/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase3_201612174;

import Estructuras.Lista;
import Estructuras.Lista_Adyacencia;
import Estructuras.Tabla_Hash;
import Interfaz.Login;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    public static Lista lugares = new Lista();
    public static Lista_Adyacencia rutas = new Lista_Adyacencia();
    public static Tabla_Hash hash_ = new Tabla_Hash(0);
    public static Lista clientes = new Lista();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Login log = new Login(clientes, lugares, rutas, hash_, "");
        log.setVisible(true);

    }
}
