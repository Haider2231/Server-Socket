package controlador;

import java.util.ArrayList;
import mundo.LWZ;
import javax.swing.JOptionPane;
import mundo.ReadText;
import vista.PanelSeleccionar;

/**
 *
 * @author gonza
 */
public class Controlador {

    private PanelSeleccionar pnlSeleccionar; // Panel de selección de archivo

    public Controlador(PanelSeleccionar pnlSeleccionar) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this); // Inyectar el controlador en el panel
    }

    public void procesarArchivo(String archivoSeleccionado) {
        ReadText readText = new ReadText(archivoSeleccionado); // Crear instancia para leer el archivo
        LWZ lwz = new LWZ();
        try {
            ArrayList<Character> list = readText.leerArchivo(archivoSeleccionado);
            // Inicializar el diccionario una vez
            lwz.initializeDictionary();

            // Bucle para recorrer todos los caracteres
            for (int i = 0; i < list.size(); i++) {
                char se = list.get(i);
                int code = lwz.compress3(se);
                if (code != -1) {
                    JOptionPane.showMessageDialog(null, "Código comprimido enviado: " + code);

                }
            }
            
        } catch (Exception ex) {
            // Manejar errores en la lectura del archivo
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
