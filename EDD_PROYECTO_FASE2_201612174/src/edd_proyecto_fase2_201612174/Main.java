package edd_proyecto_fase2_201612174;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import Interfaz.*;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {

        ArbolB usuarios = new ArbolB();

        Login log = new Login(usuarios, "");
        log.setVisible(true);
        System.out.println("test");

    }
}
