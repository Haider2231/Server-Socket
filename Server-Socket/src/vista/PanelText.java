package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * PanelText representa el área de texto donde se muestran los códigos comprimidos enviados al cliente.
 */
public class PanelText extends JPanel {

    private JTextArea txtArena;

    /**
     * Constructor de PanelText.
     * Configura el área de texto y su diseño visual.
     */
    public PanelText() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 240));

        txtArena = new JTextArea();
        txtArena.setEditable(false);
        txtArena.setFont(new Font("Arial", Font.PLAIN, 14));
        txtArena.setLineWrap(true);
        txtArena.setWrapStyleWord(true);
        txtArena.setOpaque(false);
        txtArena.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrlChat = new JScrollPane(txtArena);
        scrlChat.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        add(scrlChat, BorderLayout.CENTER);
    }

    /**
     * Agrega un mensaje al área de texto.
     * @param message El mensaje a agregar.
     */
    public void addMessage(int message) {
        txtArena.append(message + "\n");
        txtArena.setCaretPosition(txtArena.getDocument().getLength());
    }
     
    /**
     * Limpia el área de texto.
     */
    public void limpiar() {
        txtArena.setText("");
    }
}
