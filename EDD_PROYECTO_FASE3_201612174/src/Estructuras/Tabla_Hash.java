/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import edd_proyecto_fase3_201612174.GraphViz;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Tabla_Hash {

    public int[] primos = {37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499};
    public int size, agregados;
    public Hash hash;
    public double carga;

    public class Hash { //Agrega todo los arrays aqui o crear otro clase global para guardar el informacion

        public String[] dpi;
        public String[] nombres;
        public String[] apellidos;
        public String[] tipo_Licencia;
        public String[] genero;
        public String[] telefono;
        public String[] direccion;

        public Hash() {
            this.dpi = new String[1];
            this.nombres = new String[1];
            this.apellidos = new String[1];
            this.tipo_Licencia = new String[1];
            this.genero = new String[1];
            this.telefono = new String[1];
            this.direccion = new String[1];
        }

        public Hash(int amount) {
            this.dpi = new String[amount];
            this.nombres = new String[amount];
            this.apellidos = new String[amount];
            this.tipo_Licencia = new String[amount];
            this.genero = new String[amount];
            this.telefono = new String[amount];
            this.direccion = new String[amount];
        }
    }

    public Tabla_Hash(int size) {
        this.size = size;
        int amount = this.primos[this.size];
        hash = new Hash(amount);
        this.agregados = 0;
    }

    public void insertar(String dpi, String nombre, String apellidos, String tipo_Licencia, String genero, String direccion, String telefono) {
        long llave = Long.parseLong(dpi);
        int llave_ = getHash(llave);

        if (hash.dpi[llave_] == null) {
            hash.dpi[llave_] = dpi;
            hash.nombres[llave_] = nombre;
            hash.apellidos[llave_] = apellidos;
            hash.tipo_Licencia[llave_] = tipo_Licencia;
            hash.genero[llave_] = genero;
            hash.direccion[llave_] = direccion;
            hash.telefono[llave_] = telefono;
        } else {
            for (int i = 1; i < 100; i++) {
                llave_ = getHash(llave, i);
                if (llave_ > this.primos[this.size]) {
                    llave_ = llave_ % this.primos[this.size];
                }
                if (hash.dpi[llave_] == null) {
                    hash.dpi[llave_] = dpi;
                    hash.nombres[llave_] = nombre;
                    hash.apellidos[llave_] = apellidos;
                    hash.tipo_Licencia[llave_] = tipo_Licencia;
                    hash.genero[llave_] = genero;
                    hash.direccion[llave_] = direccion;
                    hash.telefono[llave_] = telefono;
                    break;
                }
            }
        }
        agregados++;
        carga = (double) agregados / (this.primos[this.size]);
    }

    private int getHash(long llave) {
        int key = (int) (llave % this.primos[this.size]);
        return key;
    }

    private int getHash(long llave, int i) {
        int key = (int) (((llave % 7) + 1) * i);
        return key;
    }

    public void buscar(String dpi) {
        long llave = Long.parseLong(dpi);
        int llave_ = getHash(llave);
        if (hash.dpi[llave_].equals(dpi)) {
            System.out.println("Encontrado: " + hash.dpi[llave_] + " en posicion: " + llave_);
        } else {
            for (int i = 1; i < 100; i++) {
                llave_ = getHash(llave, i);
                if (llave_ > this.primos[this.size]) {
                    llave_ = llave_ % this.primos[this.size];
                }
                if (hash.dpi[llave_].equals(dpi)) {
                    System.out.println("Encontrado: " + hash.dpi[llave_] + " en posicion: " + llave_);
                    break;
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < hash.dpi.length; i++) {
            System.out.println("Posicion " + (i + 1) + ": " + hash.dpi[i]);
        }
    }

    public void graficar() {
        try {
            FileWriter myWriter = new FileWriter("src\\salidas\\Tabla_Hash.txt");//
            myWriter.write("digraph structs\n{\nrankdir=\"TB\"\nlabel=\"Carnet: 201612174\"\nnode [shape=none];\n");
            myWriter.write(graficadora(hash));
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("Tabla_Hash");//"Tabla_Hash"
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora(Hash hash) {
        String cadena = "";
        cadena += "n1 [label = < \n<table>\n<tr><td colspan = \"2\" >Mensajeros</td></tr>";
        for (int i = 0; i < hash.dpi.length; i++) {
            cadena += "\n<tr><td> Nombre:" + hash.nombres[i] + " " + hash.apellidos[i] + "</td><td>" + hash.dpi[i] + "</td></tr>";
        }
        cadena += "\n</table>\n> ]";
        return cadena;
    }
}
