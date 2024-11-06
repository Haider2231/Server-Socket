package controlador;

import java.util.List;
import javax.swing.JOptionPane;
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

    }
}
