package processamento_conversor;

import java.util.ArrayList;
import java.util.HashMap;

public class NumerosDecimais {

    static HashMap<Integer, String> valor_decimais;

    static {
        // INICIALIZANDO O HASHMAP COM O VALOR NUMÉRICO DE CADA NÚMERO ROMANO
        valor_decimais = new HashMap<>();
        valor_decimais.put(1, "I");
        valor_decimais.put(5, "V");
        valor_decimais.put(10, "X");
        valor_decimais.put(50, "G");
        valor_decimais.put(100, "C");
        valor_decimais.put(500, "D");
        valor_decimais.put(1000, "M");
    }

    public static String converter_para_romanos(int decimais) {
        /*IDÉIA INICIAL: DECOMPOR O NÚMERO INTEIRO (UNIDADE, DEZENA, CENTENA...)
        E PARA CADA ORDEM, MONTAR UM NÚMERO ROMANO, E ENCAIXAR NO ACUMULADOR DE
        ROMANOS.
         */
        // PASSO 1: DECOMPONDO O NUMERO DECIMAL:

        String romanos_acc = "";
        int ordem = 1;
        while (Math.floor(decimais / ordem) != 0) {
            ordem *= 10;
        }
        ordem /= 10;

        ArrayList<Integer> decimais_decompostos = decompor(decimais, ordem);
        // PASSO 2: COM A LISTA DE ORDENS NUMÉRICAS, AGORA PARA CADA ORDEM SERÁ MONTADO
        // UMA SEQUÊNCIA DE NÚMEROS ROMANOS, E JOGADO AO ACUMULADOR
        for (int i = 0; i < decimais_decompostos.size(); i++) {
            romanos_acc += monta_romanos(decimais_decompostos.get(i), ordem);
            ordem /= 10;
        }

        return romanos_acc;
    }

    public static boolean eNumerico(String numero_digitado) {
        // VERIFICA SE O NÚMERO DIGITADO É NUMÉRICO E INTEIRO
        return isInteger(numero_digitado);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> decompor(int numero, int ordem) {
        // ESSE MÉTODO DECOMPÕE UM NÚMERO INTEIRO EM UMA LISTA DE INTEIROS, CADA UM REPRESENTANDO
        // UMA ORDEM NUMÉRICA.
        ArrayList<Integer> decimais_decompostos = new ArrayList<Integer>();

        while (ordem >= 1) {
            int digitos_atuais = numero / ordem;
            digitos_atuais %= 10;

            if (digitos_atuais == 0) {
                decimais_decompostos.add(0);
            } else {
                decimais_decompostos.add((int) Math.floor(digitos_atuais));
            }
            
            ordem /= 10;
        }

        return decimais_decompostos;

    }

    public static String monta_romanos(int numero, int ordem) {
        String romanos_temp = "";

        switch (numero) {
            case 1:
                romanos_temp = valor_decimais.get(1 * ordem);
                break;
            case 2:
                romanos_temp = valor_decimais.get(1 * ordem) + valor_decimais.get(1 * ordem);
                break;
            case 3:
                romanos_temp = valor_decimais.get(1 * ordem)
                        + valor_decimais.get(1 * ordem)
                        + valor_decimais.get(1 * ordem);
                break;
            case 4:
                romanos_temp = valor_decimais.get(1 * ordem) + valor_decimais.get(5 * ordem);
                break;
            case 5:
                romanos_temp = valor_decimais.get(5 * ordem);
                break;
            case 6:
                romanos_temp = valor_decimais.get(5 * ordem) + valor_decimais.get(1 * ordem);
                break;
            case 7:
                romanos_temp = valor_decimais.get(5 * ordem)
                        + valor_decimais.get(1 * ordem) + valor_decimais.get(1 * ordem);
                break;
            case 8:
                romanos_temp = valor_decimais.get(5 * ordem)
                        + valor_decimais.get(1 * ordem)
                        + valor_decimais.get(1 * ordem)
                        + valor_decimais.get(1 * ordem);
                break;
            case 9:
                romanos_temp = valor_decimais.get(1 * ordem) + valor_decimais.get(10 * ordem);
                break;
        }

        return romanos_temp;
    }

}
