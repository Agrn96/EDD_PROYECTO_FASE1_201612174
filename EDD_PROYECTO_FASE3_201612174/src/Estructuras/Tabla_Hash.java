/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Tabla_Hash {

    public int[] primos = {37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499};
    public int size;
    public double minimo, agregados, carga;
    public String[] dpi;
    public String[] nombres;
    public String[] apellidos;
    public char[] tipo_Licencia;
    public char[] genero;
    public String[] telefono;
    public String[] direccion;

    public class NodoCont { //Agrega todo los arrays aqui

        String dpi;

        public NodoCont(String dpi) { 
            this.dpi = dpi;
        }
    }

    public Tabla_Hash(int size) {
        this.size = size;
        this.dpi = new String[this.primos[this.size]];
        this.nombres = new String[this.primos[this.size]];
        this.apellidos = new String[this.primos[this.size]];
        this.tipo_Licencia = new char[this.primos[this.size]];
        this.genero = new char[this.primos[this.size]];
        this.telefono = new String[this.primos[this.size]];
        this.direccion = new String[this.primos[this.size]];
        this.agregados = 0;

        System.out.println(this.dpi.length);
        this.minimo = primos[this.size] * 0.75;
        this.agregados = 0;
        insertar("0");
    }

    public void insertar(String dpi) {
        long llave = Long.parseLong(dpi);
        int llave_ = getHash(llave);
//        System.out.println(llave_);
//        System.out.println(this.size);
//        System.out.println(this.dpi.length);
        if(llave == 0 && this.dpi[0] != null){
            return;
        }

        if (this.dpi[llave_] == null) {
            this.dpi[llave_] = dpi;
        } else {
            for (int i = 1; i < 100; i++) {
                llave_ = getHash(llave, i);
                if (llave_ > this.primos[this.size]) {
                    llave_ = llave_ % this.primos[this.size];
                }
                if (this.dpi[llave_] == null) {
                    this.dpi[llave_] = dpi;
                    break;
                }
            }
        }
        agregados++;
        carga = agregados / this.primos[this.size];
    }

    private int getHash(long llave) {
        int key = (int) (llave % this.primos[this.size]);
        return key;
    }

    private int getHash(long llave, int i) {
        int key = (int) (((llave % 7) + 1) * i);
        return key;
    }
    
    public void buscar(String dpi){
        long llave = Long.parseLong(dpi);
        int llave_ = getHash(llave);
//        System.out.println(llave_);
//        System.out.println(this.size);
//        System.out.println(this.dpi.length);
        if (this.dpi[llave_].equals(dpi)) {
            System.out.println("Encontrado: " + this.dpi[llave_] + " en posicion: " + llave_);
        } else {
            for (int i = 1; i < 100; i++) {
                llave_ = getHash(llave, i);
                if (llave_ > this.primos[this.size]) {
                    llave_ = llave_ % this.primos[this.size];
                }
                if (this.dpi[llave_].equals(dpi)) {
                    System.out.println("Encontrado: " + this.dpi[llave_] + " en posicion: " + llave_);
                    break;
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < dpi.length; i++) {
            System.out.println(this.dpi[i]);
        }
    }   
}