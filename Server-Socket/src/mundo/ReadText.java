package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadText se encarga de leer un archivo de texto y devolver su contenido
 * como una lista de caracteres.
 */
public class ReadText {

    private String filePath;

    /**
     * Constructor que inicializa la ruta del archivo.
     * @param filePath Ruta del archivo a leer.
     */
    public ReadText(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Lee el archivo y devuelve una lista de caracteres.
     * @param archivo Ruta del archivo.
     * @return Lista de caracteres leídos.
     */
    public ArrayList<Character> leerArchivo(String archivo) {
        ArrayList<Character> caracteres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int c;
            while ((c = br.read()) != -1) {
                caracteres.add((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caracteres;
    }
}
