/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author ASUS
 */
public class PanelText  extends JPanel{
    
     private JTextArea txtArena;

    public PanelText() {
   
       setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 240)); // Fondo neumórfico

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
    public void addMessage(int message) {
        txtArena.append(message + "\n");
        txtArena.setCaretPosition(txtArena.getDocument().getLength()); 
    }
     
    public void limpiar(){
        txtArena.setText("");
    }
}