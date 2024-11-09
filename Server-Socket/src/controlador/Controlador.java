package controlador;

import mundo.LWZ;
import vista.PanelSeleccionar;
import vista.PanelText;

import javax.swing.*;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import mundo.ReadText;

public class Controlador {

    private PanelSeleccionar pnlSeleccionar;
    private PanelText pnlText;
    private String archivoSeleccionado;

    public Controlador(PanelSeleccionar pnlSeleccionar, PanelText pnlText) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this); // Inyectar el controlador en el panel
        this.pnlText = pnlText;
        iniciarServidor(); 
    
    }

    // Método llamado desde el PanelSeleccionar cuando se elige un archivo
    public void archivoSeleccionado(String archivo) {
        this.archivoSeleccionado = archivo;
    }

    
    private void iniciarServidor() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                System.out.println("Servidor iniciado en el puerto 5000...");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> manejarCliente(clientSocket)).start();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar el servidor: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }

    private void manejarCliente(Socket clientSocket) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            if (archivoSeleccionado != null) {
                LWZ lwz = new LWZ();
                ArrayList<Character> list = new ReadText(archivoSeleccionado).leerArchivo(archivoSeleccionado);
                lwz.initializeDictionary();

                for (char se : list) {
                    int code = lwz.compress3(se);
                    if (code != -1) {
                        out.writeObject(code);
                        pnlText.addMessage(code);
                        Thread.sleep(1000);       
                    }
                }

                int lastCode = LWZ.getFinalCode();
                if (lastCode != -1) {
                    out.writeObject(lastCode);
                    pnlText.addMessage(lastCode);
                    pnlText.limpiar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al manejar el cliente: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
