package mundo;

import java.util.Hashtable;

/**
 * LWZ implementa un compresor usando el algoritmo LZW.
 * Comprime una cadena de caracteres y usa un diccionario para almacenar combinaciones.
 */
public class LWZ {

    private static String pe = ""; // Prefijo actual
    private static int dictSize; // Tamaño del diccionario
    private static Hashtable<String, Integer> diccionario = new Hashtable<>();

    /**
     * Inicializa el diccionario con los primeros 256 caracteres ASCII.
     */
    public static void initializeDictionary() {
        diccionario.clear();
        for (int i = 0; i < 256; i++) {
            diccionario.put("" + (char) i, i);
        }
        dictSize = diccionario.size();
        pe = ""; // Reiniciar prefijo
    }

    /**
     * Comprime el carácter proporcionado y actualiza el diccionario.
     * @param se Carácter a comprimir.
     * @return Código comprimido, o -1 si aún no emite código.
     */
    public static int compress3(char se) {
        String ps = pe + se;

        if (diccionario.containsKey(ps)) {
            pe = ps;
            return -1;
        } else {
            int output = diccionario.get(pe);
            diccionario.put(ps, dictSize++);
            pe = String.valueOf(se);
            return output;
        }
    }

    /**
     * Obtiene el código final en caso de que haya un prefijo acumulado.
     * @return Código final, o -1 si no hay prefijo.
     */
    public static int getFinalCode() {
        return !pe.isEmpty() ? diccionario.get(pe) : -1;
    }
}

