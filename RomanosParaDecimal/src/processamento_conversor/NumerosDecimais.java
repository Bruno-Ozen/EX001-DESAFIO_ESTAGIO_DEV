package processamento_conversor;

import java.util.HashMap;

public class NumerosDecimais {
    static HashMap<Integer, String> valor_decimais;
    
    public NumerosDecimais() {
        // INICIALIZANDO O HASHMAP COM O VALOR NUMÉRICO DE CADA NÚMERO ROMANO
        valor_decimais.put(1, "I");
        valor_decimais.put(5, "V");
        valor_decimais.put(10, "X");
        valor_decimais.put(50, "G");
        valor_decimais.put(100, "C");
        valor_decimais.put(500, "D");
        valor_decimais.put(1000, "M");
    }

    public static String converter(int decimais) {
        int[] valores = {1, 5, 10, 50, 100, 500, 1000};
        int decimais_decompostos = decimais;

        String romanos_acc = "";
        int i = valores.length;
        while (decimais_decompostos > 0) {
            if ((decimais_decompostos - valores[i] > 0)) {
                decimais_decompostos -= valores[i];
                romanos_acc += valor_decimais.get(valores[i]);
            } else {
                i--;
            }
        }

        return romanos_acc;
    }
    
}
