package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ReadText se encarga de leer archivos de texto y devolver su
 * contenido.
 */
public class ReadText {

    private String filePath; // Ruta del archivo a leer

    /**
     * Constructor que inicializa la ruta del archivo.
     *
     * @param filePath Ruta del archivo a leer.
     */
    public ReadText(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Character> leerArchivo(String archivo) {
    ArrayList<Character> caracteres = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Agregar cada carácter de la línea a la lista
            for (char c : linea.trim().toCharArray()) {
                caracteres.add(c);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    return caracteres;
}
}
