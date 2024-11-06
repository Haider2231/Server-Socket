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

    public List<String> leerArchivo(String archivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea.trim());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }
}
