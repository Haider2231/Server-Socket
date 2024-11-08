package vista;

import java.awt.Color;
import javax.swing.JFrame;
import controlador.Controlador;

public class InterfazApp extends JFrame {

    private PanelSeleccionar pnlSeleccionar;
    private PanelText pnlChat;
    private Controlador controlador; 
    
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

        // Crear el controlador y conectar la vista
        controlador = new Controlador(pnlSeleccionar, pnlChat);

        setVisible(true);
    }

    public static void main(String[] args) {
        InterfazApp main = new InterfazApp();
    }

}
