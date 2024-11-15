package vista;

import java.awt.Color;
import javax.swing.JFrame;
import controlador.Controlador;

/**
 * InterfazApp es la ventana principal de la aplicación del servidor.
 * Configura los paneles de selección y visualización de texto, y crea el controlador para manejar la lógica.
 */
public class InterfazApp extends JFrame {

    private PanelSeleccionar pnlSeleccionar;
    private PanelText pnlChat;
    private Controlador controlador;

    /**
     * Constructor de InterfazApp.
     * Configura los componentes de la interfaz y el controlador.
     */
    public InterfazApp() {
        setTitle("Emisor");
        setSize(680, 290);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(224, 242, 241));

        pnlSeleccionar = new PanelSeleccionar();
        pnlSeleccionar.setBounds(50, 100, 150, 50);
        add(pnlSeleccionar);
        
        pnlChat = new PanelText();
        pnlChat.setBounds(230, 10, 400, 230);
        add(pnlChat);

        controlador = new Controlador(pnlSeleccionar, pnlChat);

        setVisible(true);
    }

    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        new InterfazApp();
    }
}
