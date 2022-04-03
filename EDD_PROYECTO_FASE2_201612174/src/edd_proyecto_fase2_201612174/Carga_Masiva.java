/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase2_201612174;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Carga_Masiva {

    public static void cargaMasivaUsuarios(File f, ArbolB usuarios) throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject image = iterator.next();
            String id = (String) image.get("dpi");
            String nombreC = (String) image.get("nombre_cliente");
            String pass = (String) image.get("password");
            usuarios.insertar(id, nombreC, pass);
        }
        usuarios.graph("Usuarios");
    }

    public static void cargaMasivaImages(File f, String usuarioID, ArbolB usuarios) throws FileNotFoundException, IOException, ParseException {
        String path = "src\\Salidas\\" + usuarioID + "\\Images";
        String[] top = new String[5];
        int[] top5 = new int[5];
        int count = 0;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        AVL avl = new AVL();
        NodoB usuario = usuarios.find(usuarioID);
        ABB random = usuario.node_Capas;
        while (iterator.hasNext()) {
            
            JSONObject image = iterator.next();
            long id = (long) image.get("id");
            JSONArray capas = (JSONArray) image.get("capas");

            ABB arbol = new ABB();
            arbol.raiz.data = id;
            avl.raiz = avl.insert(avl.raiz, arbol);
            Long t = (Long) id;
            Matriz_Dispersa MD = new Matriz_Dispersa();
            MD.add_Node(0, 0, t.toString());

            capas.forEach(x -> {
                Long idCapa = (Long) x;
                System.out.println("Node: " + idCapa);
                arbol.insert(idCapa);
                Matriz_Dispersa MD_ = random.search(idCapa);
                MD.add_Matrix(MD_);
            });
            MD.graph2(usuarioID, id);
            avl.graficar(avl.raiz);
        }
        usuario.node_Img = avl;
        System.out.println("pause");
    }

    public static ABB carga_Masiva_Capa(File f, String usuarioID) throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        Matriz_Dispersa MD_Main = new Matriz_Dispersa();
        MD_Main.add_Node(0, 0, "TEST");
        ABB arbol = new ABB();
        while (iterator.hasNext()) {
            JSONObject capa = iterator.next();
            Long id = (Long) capa.get("id_capa");
            String id_ = id.toString();
            JSONArray pixeles = (JSONArray) capa.get("pixeles");
            Iterator<JSONObject> iterator2 = pixeles.iterator();
            Matriz_Dispersa MD = new Matriz_Dispersa();
            MD.add_Node(0, 0, id_);
            while (iterator2.hasNext()) {
                JSONObject obj = iterator2.next();
                long row = (long) obj.get("fila");
                int row_ = (int) row;
                long col = (long) obj.get("columna");
                int col_ = (int) col;
                String color = (String) obj.get("color");
                MD.add_Node(row_, col_, color);
                MD_Main.add_Node(row_, col_, color);
            }
            System.out.println("NEW MD: " + MD.inicio.data);
            arbol.insert(MD);
            MD.graph(usuarioID, id);
        }
        MD_Main.graph(usuarioID, 123L);
        arbol.graficar(arbol.raiz, "1234567890101", 25L);
        return arbol;
    }

    public static void cargaMasivaAlbums(String usuarioID, ArbolB usuarios, File f) throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject capa = iterator.next();
            String nombre_Album = (String) capa.get("nombre_album");
            JSONArray imgs = (JSONArray) capa.get("imgs");
            Lista_DE alb = new Lista_DE();
            alb.add(nombre_Album);
            String path = "src\\Salidas\\" + usuarioID + "\\" + nombre_Album;
            File album = new File(path);
            album.mkdir();
            NodoB usuario = usuarios.find(usuarioID);
            imgs.forEach(t -> {
                Long idImagen = (Long) t;
                ABB x = usuario.node_Img.search(idImagen);
                alb.add_Images(nombre_Album, x);
                String path1 = "src\\Salidas\\" + usuarioID + "\\Images\\" + idImagen + ".png";
                String path2 = "src\\Salidas\\" + usuarioID + "\\" + nombre_Album + "\\" + idImagen + ".png";
                File src = new File(path1);
                File dest = new File(path2);
                try {
                    copyFile(path1, path2);
                } catch (IOException ex) {
                    Logger.getLogger(Carga_Masiva.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            usuario.node_Album = alb;
        }
    }

    public static void copyFile(String from, String to) throws IOException {
        Path pathIn = (Path) Paths.get(from);
        Path pathOut = (Path) Paths.get(to);
        System.out.println("Path of target file: "
                + pathIn.toString());

        System.out.println("Path of source file: "
                + pathOut.toString());
        try {
            Files.copy(pathIn, pathOut);
        } // Catch block to handle the exceptions
        catch (IOException e) {
        }
    }
}
