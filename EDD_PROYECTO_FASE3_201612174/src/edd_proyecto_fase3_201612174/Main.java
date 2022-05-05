/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase3_201612174;

import Estructuras.Lista;
import Estructuras.Lista_Adyacencia;
import Estructuras.Tabla_Hash;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lista lugares = new Lista();
        Lista_Adyacencia rutas = new Lista_Adyacencia();
        Tabla_Hash hash_ = new Tabla_Hash(0);
        Lista clientes = new Lista();
        // TODO code application logic here
        //Lista de lugares
        //Lista lugares = new Lista();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            Carga_Masiva.loadFromFile(f, clientes, 1);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No folder selected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
//        clientes.insert("3001554250101", "Alberto Reyes", "Codeprentice", "agrn96p@gmail.com", "1234", "123456", "random", 124);
//        clientes.insert("123", "123", "123", "123", "123", "123", "123", 123);
//        clientes.display_Clientes();

        //Lugares
        fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            Carga_Masiva.loadFromFile(f, lugares);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No folder selected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

        System.out.println("");
        //Rutas
        fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            Carga_Masiva.loadFromFile(f, rutas, lugares);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No folder selected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

        System.out.println("\nTest");
        
        //Hash
        fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            File f = new File(ruta);
            Carga_Masiva.loadFromFile(f, hash_);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No folder selected");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}

