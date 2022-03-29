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
        
        MD.add_Node(0,0,"test");
        
        //MD.add_headers("t",5,5);
        //MD.display();
        MD.add_Node(4, 3, "First");
        MD.add_Node(1, 3, "Second");
        MD.add_Node(5, 4, "Third");
        MD.add_Node(2, 2, "Fourth");
        MD.add_Node(3, 2, "Fifth");
        MD.add_Node(1, 3, "Sixth"); // Duplicates are taking into account the col pointers but not adding the row ones
        MD.add_Node(5, 1, "Seventh");
        MD.add_Node(3, 1, "Eighth");
        
        System.out.println("New");
        MD.display();
        System.out.println("New");
        MD.display2();
        
        //MD.display2();
        MD.graph("MD");
        
        
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
    private static void cargaMasivaCapa() throws FileNotFoundException, IOException, ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src\\edd_proyecto_fase2_201612174\\sonic.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            JSONObject capa = iterator.next();
            //long id = (long) capa.get("id_capa");
            JSONArray pixeles = (JSONArray) capa.get("pixeles");
            Iterator<JSONObject> iterator2 = pixeles.iterator();
                
            Lista_Orthogonal MD = new Lista_Orthogonal(50,50);
            //Matriz m = new Matriz((int) id, "Capa");
            while (iterator2.hasNext()){
                JSONObject obj = iterator2.next();
                long fila = (long) obj.get("fila");
                long columna = (long) obj.get("columna");
                String color = (String) obj.get("color");
                System.out.println("F: " + fila + " C: " + columna + " D: " + color);
                MD.add_nodes(fila, columna, color);
                //m.insert((int) columna, (int) fila, color);
            }
            MD.display();
            //MD.graph("MD2");
            //generarDot("capa" + id, m.graph());
        }
    }
    
    /*
    private static void cargaMasivaClientes() throws FileNotFoundException, IOException, ParseException{
        //BTreeClients b = new BTreeClients(2);
        
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Clientes.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JSONObject cliente = iterator.next();
            String dpi = (String) cliente.get("dpi");
            String nombre_cliente = (String) cliente.get("nombre_cliente");
            String password = (String) cliente.get("password");
            b.insert(new Cliente(dpi, nombre_cliente, password));
        }
        generarDot("BTreeClients", b.graph());
    }
    */
    /*
    private static void cargaMasivaClientes() throws FileNotFoundException, IOException, ParseException{
        //BTreeClients b = new BTreeClients(2);
        
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./Clientes.json"));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while(iterator.hasNext()){
            JSONObject cliente = iterator.next();
            String dpi = (String) cliente.get("dpi");
            String nombre_cliente = (String) cliente.get("nombre_cliente");
            String password = (String) cliente.get("password");
            b.insert(new Cliente(dpi, nombre_cliente, password));
        }
        generarDot("BTreeClients", b.graph());
    }
*/
    
}
