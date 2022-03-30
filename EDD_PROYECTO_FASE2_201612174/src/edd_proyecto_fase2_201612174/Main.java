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
        Matriz_Dispersa MD = new Matriz_Dispersa();
        carga_Masiva_Capa();
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
    private static void carga_Masiva_Capa() throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src\\edd_proyecto_fase2_201612174\\sonic.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        
        while(iterator.hasNext()) {
            JSONObject capa = iterator.next();
            Long id = (Long) capa.get("id_capa");
            String id_ = id.toString();
            JSONArray pixeles = (JSONArray) capa.get("pixeles");
            Iterator<JSONObject> iterator2 = pixeles.iterator();
            System.out.println(id_ + " CAPA NUEVO MATRIZ");
            Matriz_Dispersa MD = new Matriz_Dispersa();
            MD.add_Node(0, 0, id_);
            while (iterator2.hasNext()){
                JSONObject obj = iterator2.next();
                long row = (long) obj.get("fila");
                int row_ = (int) row;
                long col = (long) obj.get("columna");
                int col_ = (int) col;
                String color = (String) obj.get("color");
                System.out.println("F: " + row_ + " C: " + col_ + " D: " + color);
                MD.add_Node(row_, col_, color);
            }
            MD.display();
            MD.graph(id_);
        }
    }
}
