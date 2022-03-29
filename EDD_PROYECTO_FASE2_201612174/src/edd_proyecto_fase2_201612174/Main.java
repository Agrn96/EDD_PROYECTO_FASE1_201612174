package edd_proyecto_fase2_201612174;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        
        //Lista_Orthogonal MD = new Lista_Orthogonal();
        
        //MD.add_headers("t",5,5);
        //MD.display();
        //MD.add_nodes(4, 3, 2);
        //MD.add_nodes(1, 3, 1);
        //MD.add_nodes(5, 4, 3);
        //MD.add_nodes(2, 2, 4);
        //MD.add_nodes(3, 2, 5);
        //MD.add_nodes(1, 3, 7); // Duplicates are taking into account the col pointers but not adding the row ones
        //MD.add_nodes(5, 1, 6);
        //MD.add_nodes(3, 1, 2);
        
        System.out.println("New");
        //MD.display();
        System.out.println("New");
        //MD.display2();
        //MD.graph("MD");
        
        
        //AVL
        AVL avl = new AVL();
        int[] x = {75, 40, 23, 8, 6, 37, 32, 45, 25, 1, 88, 29, 4, 11, 39, 14, 66, 24, 12, 7};
        Node_AVL temp = new Node_AVL(0);
        for(int i = 0; i < x.length; i++){
            avl.raiz = avl.insert(avl.raiz, x[i]);
        }
        avl.graficar(avl.raiz);
        avl.imprimir("reporte");
    }
    
    
}
