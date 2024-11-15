package controlador;

import vista.PanelSeleccionar;
import vista.PanelText;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Controlador es responsable de iniciar el servidor y manejar la lógica de compresión y envío de datos.
 */
public class Controlador {

    private PanelSeleccionar pnlSeleccionar;
    private PanelText pnlText;
    private String archivoSeleccionado;

    /**
     * Constructor del Controlador.
     * Inicia el servidor y conecta el panel de selección con la lógica del controlador.
     * @param pnlSeleccionar Panel de selección de archivos.
     * @param pnlText Panel donde se muestra la compresión en progreso.
     */
    public Controlador(PanelSeleccionar pnlSeleccionar, PanelText pnlText) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this);
        this.pnlText = pnlText;
        iniciarServidor();
    }

    /**
     * Guarda la ruta del archivo seleccionado.
     * @param archivo Ruta del archivo seleccionado.
     */
    public void archivoSeleccionado(String archivo) {
        this.archivoSeleccionado = archivo;
    }

    /**
     * Inicia el servidor y espera conexiones de clientes.
     */
    private void iniciarServidor() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                System.out.println("Servidor iniciado en el puerto 5000...");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new ClientHandler(clientSocket, archivoSeleccionado, pnlText)).start();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar el servidor: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }
}
