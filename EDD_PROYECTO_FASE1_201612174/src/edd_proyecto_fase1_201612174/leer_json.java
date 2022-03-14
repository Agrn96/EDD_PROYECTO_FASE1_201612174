/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase1_201612174;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class leer_json {
    
    public leer_json(){
        
    }

    public void leer(Lista cI, String fileName) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("JSON LEIDO" + jsonObject);

            int i = 1;
            while (jsonObject.containsKey("Cliente" + i)) {
                Map address = ((Map)jsonObject.get("Cliente" + i));
                
                Iterator<Map.Entry> itr1 = address.entrySet().iterator();
                String[] store = new String[4];
                int j = 0;
                while(itr1.hasNext()) {
                    Map.Entry pair = itr1.next();//System.out.println(pair.getKey() + " : " + pair.getValue());
                    store[j] = pair.getValue().toString(); 
                    j++;
                }
                cI.add(store[3], Integer.parseInt(store[0]), Integer.parseInt(store[2]), j);
                i++;
            }
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            Logger.getLogger(leer_json.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(leer_json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
