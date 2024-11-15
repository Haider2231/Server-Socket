package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import controlador.Controlador;

/**
 * PanelSeleccionar es el panel que contiene el botón para seleccionar un archivo.
 * Muestra un cuadro de diálogo para que el usuario elija un archivo de texto.
 */
public class PanelSeleccionar extends JPanel {

    private JButton btnSeleccionar;
    private String archivoSeleccionado;
    private Controlador controlador;

    /**
     * Constructor de la clase PanelSeleccionar. Inicializa el botón de selección y el diseño del panel.
     */
    public PanelSeleccionar() {
        setLayout(new BorderLayout());

        btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.setBackground(new Color(174, 214, 241));
        btnSeleccionar.setForeground(Color.BLACK);
        btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSeleccionar.addActionListener(createFileChooserListener());

        add(btnSeleccionar, BorderLayout.CENTER);
    }

    /**
     * Inyecta el controlador en el panel para manejar la lógica.
     * @param controlador Controlador de la aplicación.
     */
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Crea un ActionListener para abrir un cuadro de diálogo de selección de archivos.
     * @return ActionListener para manejar el evento de selección de archivo.
     */
    private ActionListener createFileChooserListener() {
        return e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt", "text", "in", "out");
            fileChooser.setFileFilter(filter);

            int resultado = fileChooser.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                archivoSeleccionado = fileChooser.getSelectedFile().getAbsolutePath();
                controlador.archivoSeleccionado(archivoSeleccionado);
            }
        };
    }

    /**
     * Obtiene la ruta del archivo seleccionado.
     * @return Ruta del archivo seleccionado.
     */
    public String getArchivoSeleccionado() {
        return archivoSeleccionado;
    }
}
