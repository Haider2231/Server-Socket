package controlador;

import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import mundo.LWZ;
import mundo.ReadText;
import vista.PanelText;

/**
 * ClientHandler maneja la conexión y comunicación con un cliente específico.
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private String archivoSeleccionado;
    private PanelText pnlText;

    /**
     * Constructor de ClientHandler.
     * @param clientSocket Socket del cliente.
     * @param archivoSeleccionado Ruta del archivo seleccionado.
     * @param pnlText Panel para mostrar el progreso de compresión.
     */
    public ClientHandler(Socket clientSocket, String archivoSeleccionado, PanelText pnlText) {
        this.clientSocket = clientSocket;
        this.archivoSeleccionado = archivoSeleccionado;
        this.pnlText = pnlText;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            if (archivoSeleccionado != null) {
                LWZ.initializeDictionary();
                ArrayList<Character> list = new ReadText(archivoSeleccionado).leerArchivo(archivoSeleccionado);

                for (char se : list) {
                    int code = LWZ.compress3(se);
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

