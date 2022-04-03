package edd_proyecto_fase2_201612174;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Interfaz.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JFrame;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        String usuarioID = "3999062130101";
        
        ArbolB usuarios = new ArbolB();
        String path = "src\\edd_proyecto_fase2_201612174\\clients.json";
        File f = new File(path);
        Carga_Masiva.cargaMasivaUsuarios(f, usuarios);

        ABB capas = Carga_Masiva.carga_Masiva_Capa(usuarioID);
        usuarios.Search(usuarioID, capas); // Add Capas
        
        path = "src\\edd_proyecto_fase2_201612174\\Imagenes - Sonic.json";
        File file = new File(path);
        Carga_Masiva.cargaMasivaImages(file, usuarioID, usuarios);
        usuarios.graph("Usuarios");
        
        path = "src\\edd_proyecto_fase2_201612174\\albumes.json";
        file = new File(path);
        Carga_Masiva.cargaMasivaAlbums(usuarioID, usuarios, file);
        
        System.out.println("test");

//        test frame = new test();
//        Login log = new Login();
//        frame.setLayout(new BorderLayout());
//        frame.setVisible(true);
//        frame.add(log, BorderLayout.CENTER);
//        log.setVisible(true);
//        System.out.println("test");
//        Matriz_Dispersa MD = new Matriz_Dispersa();
//        ABB arbol = carga_Masiva_Capa();
//        System.out.println("\nARBOL:");
//        arbol.displayIO(arbol.raiz);
//        System.out.println("\nARBOL:");
//        arbol.displayPostO(arbol.raiz);
//        System.out.println("\nARBOL:");
//        arbol.displayPreO(arbol.raiz);
//        arbol.graficar(arbol.raiz);
        //MD.add_Node(0,0,"test");
        //MD.add_headers("t",5,5);
        //MD.display();
//        MD.add_Node(4, 3, "CCAA22");
//        MD.add_Node(1, 3, "000000");
//        MD.add_Node(5, 4, "321FFF");
//        MD.add_Node(2, 3, "333333");
//        MD.add_Node(3, 2, "FFF321");
//        MD.add_Node(1, 3, "FFFF00"); // Duplicates are taking into account the col pointers but not adding the row ones
//        MD.add_Node(5, 1, "FF321F");
//        MD.add_Node(3, 1, "F321FF");
//        
//        System.out.println("New");
//        MD.display();
//        System.out.println("New");
//        MD.display2();
//        
//        //MD.display2();
//        MD.graph("MD");
        //AVL
        //AVL avl = new AVL();
        //int[] x = {75, 40, 23, 8, 6, 37, 32, 45, 25, 1, 88, 29, 4, 11, 39, 14, 66, 24, 12, 7};
        //Node_AVL temp = new Node_AVL(0);
        //for(int i = 0; i < x.length; i++){
        //    avl.raiz = avl.insert(avl.raiz, x[i]);
        //}
        //avl.graficar(avl.raiz);
        //avl.imprimir("reporte");
    }

    

    
}
