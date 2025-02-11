package processamento_conversor;

import java.util.Collection;
import java.util.HashMap;

public class ConversorNumerico {
    // Esta é uma classe responsável por converter numericamente
    // de romanos para decimais e vice-versa.
    // Ela apenas converte numericamente, não considerando
    // a etapa de verificação da validade dos valores.
    
    static HashMap<String, Integer> valor_romanos;

    public ConversorNumerico() {
        // INICIALIZANDO O HASHMAP COM O VALOR NUMÉRICO DE CADA NÚMERO ROMANO
        valor_romanos.put("I", 1);
        valor_romanos.put("V", 5);
        valor_romanos.put("X", 10);
        valor_romanos.put("G", 50);
        valor_romanos.put("C", 100);
        valor_romanos.put("D", 500);
        valor_romanos.put("M", 1000);
    }

    public char[] decompor(String romanos){
        return romanos.toCharArray();
    }
    
    public int converter(String romanos) {
        char[] romanos_decompostos = decompor(romanos);
        int result_acc = 0;
        
        for(int i = 0; i < romanos_decompostos.length; i++){
            result_acc += valor_romanos.get(romanos_decompostos[i]);
        }
        
        return result_acc;
    }

    public String converter(int decimais) {
        int[] valores = {1, 5, 10, 50, 100, 500, 1000};
        String[] simbolos_romanos = {"I", "V", "X", "G", "C", "D", "M"};
        int decimais_decompostos = decimais;
        
        String romanos_acc = "";
        int i = valores.length;
        while(decimais_decompostos > 0){
            if((decimais_decompostos - valores[i] > 0)){
                decimais_decompostos -= valores[i];
                romanos_acc += simbolos_romanos[i];
            } else{
                i--;
            }
        }
        
        return romanos_acc;
    }

}
