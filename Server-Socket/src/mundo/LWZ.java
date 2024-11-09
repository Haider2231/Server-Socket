package mundo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class LWZ {

    private static String pe = ""; // Prefijo actual en la compresión
    private static int dictSize; // Tamaño actual del diccionario
    private static Hashtable<String, Integer> diccionario = new Hashtable<>();

    public static void initializeDictionary() {
        diccionario.clear();
        for (int i = 0; i < 256; i++) {
            diccionario.put("" + (char) i, i);
        }
        dictSize = diccionario.size();
        pe = ""; // Reiniciar prefijo
    }

    // Método para comprimir una cadena
    public static int compress3(char se) {
        String ps = pe + se;

        if (diccionario.containsKey(ps)) {
            pe = ps;
            return -1; // No emite código aún, acumula más caracteres
        } else {
            int output = diccionario.get(pe);
            diccionario.put(ps, dictSize++);
            pe = String.valueOf(se);
            return output;
        }
    }

    
    public static int getFinalCode() {
        return !pe.isEmpty() ? diccionario.get(pe) : -1;
    }
    

}
