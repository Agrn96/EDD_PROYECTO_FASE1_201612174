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
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Carga_Masiva {

    public static void loadFromFile(File f, Tabla_Hash hash) throws FileNotFoundException, IOException, ParseException { //Carga Masiva Mensajeros
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject m = iterator.next();
            String dpi = (String) m.get("dpi");
            String nombre = (String) m.get("nombres");
            String apellidos = (String) m.get("apellidos");
            String tipo_Licencia = (String) m.get("tipo_licencia");
            String genero = (String) m.get("genero");
            String direccion = (String) m.get("direccion");
            String telefono = (String) m.get("telefono");
            hash.insertar(dpi, nombre, apellidos, tipo_Licencia, genero, direccion, telefono);
            if (hash.carga > 0.75) {
                Tabla_Hash hash_0 = new Tabla_Hash(hash.size + 1);
                for (int i = 0; i < hash.primos[hash.size]; i++) {
                    if (hash.hash.dpi[i] == null) {
                        continue;
                    } else {
                        hash_0.insertar(hash.hash.dpi[i], hash.hash.nombres[i], hash.hash.apellidos[i], hash.hash.tipo_Licencia[i], hash.hash.genero[i], hash.hash.direccion[i], hash.hash.telefono[i]);
                    }
                }
                hash = hash_0;
            }
        }
        hash.graficar();
    }

    public static void loadFromFile(File f, Lista_Adyacencia rutas, Lista lugares) throws FileNotFoundException, IOException, ParseException { //Carga Masiva Rutas
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(f));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("Grafo");

        for (Object o : jsonArray) {
            JSONObject m = (JSONObject) o;
            Long inicio = (Long) m.get("inicio");
            Long fin = (Long) m.get("final");
            Long peso = (Long) m.get("peso");
            rutas.insertar(Math.toIntExact(inicio), Math.toIntExact(fin), Math.toIntExact(peso));
        }
        rutas.graficar();
        rutas.graficar(lugares);
        //rutas.ruta(8, 16);
    }

    public static void loadFromFile(File f, Lista lugares) throws FileNotFoundException, IOException, ParseException { //Carga Masiva Mensajeros
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(f));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("Lugares");

        for (Object o : jsonArray) {
            JSONObject m = (JSONObject) o;
            Long id = (Long) m.get("id");
            String dept = (String) m.get("departamento");
            String nombre = (String) m.get("nombre");
            String sn_suc = (String) m.get("sn_sucursal");
            lugares.insert(Math.toIntExact(id), dept, nombre, sn_suc.equals("si") ? true : false);
        }
        lugares.graficar(0);
    }
    
    public static void loadFromFile(File f, Lista usuarios, int x) throws FileNotFoundException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(f));
        Iterator<JSONObject> iterator = jsonArray.iterator();

        while (iterator.hasNext()) {
            JSONObject m = iterator.next();
            System.out.println(m);
            String dpi = (String) m.get("dpi");
            String nombre_Completo = (String) m.get("nombre_completo");
            String nombre_Usuario = (String) m.get("nombre_usuario");
            String correo = (String) m.get("correo");
            String pass = (String) m.get("contrasenia");
            String tel = (String) m.get("telefono");
            String direccion = (String) m.get("direccion");
            Long id = (Long) m.get("id_municipio");
            usuarios.insert(dpi, nombre_Completo, nombre_Usuario, correo, pass, tel, direccion, Math.toIntExact(id));
        }
        usuarios.display_Clientes();
        usuarios.graficar(1);
    }
}
