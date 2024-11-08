package controlador;


import mundo.LWZ;
import javax.swing.JOptionPane;
import mundo.ReadText;
import vista.PanelSeleccionar;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.PanelText;

/**
 *
 * @author gonza
 */
public class Controlador {

    private PanelSeleccionar pnlSeleccionar;
    private PanelText pnlText;

    public Controlador(PanelSeleccionar pnlSeleccionar, PanelText pnlText) {
        this.pnlSeleccionar = pnlSeleccionar;
        this.pnlSeleccionar.setControlador(this); // Inyectar el controlador en el panel
        this.pnlText = pnlText;
    }

    public void procesarArchivo(String archivoSeleccionado) {
        ReadText readText = new ReadText(archivoSeleccionado); // Crear instancia para leer el archivo
        LWZ lwz = new LWZ();
      
        try (ServerSocket serverSocket = new ServerSocket(5000);
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());) {

            // Leer archivo y procesar datos
            ArrayList<Character> list = readText.leerArchivo(archivoSeleccionado);
            lwz.initializeDictionary();

            // Bucle para recorrer todos los caracteres
            for (int i = 0; i < list.size(); i++) {
                char se = list.get(i);
                int code = lwz.compress3(se);
                if (code != -1) {
                   out.writeObject(code);
                 
                   pnlText.addMessage(code);
                   
                    
                }
            }

            // Enviar el último código si queda un prefijo pendiente
            int lastCode = LWZ.getFinalCode();
            if (lastCode != -1) {
                out.writeObject(lastCode);
                out.flush();
                pnlText.addMessage(lastCode);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al enviar datos por socket: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
