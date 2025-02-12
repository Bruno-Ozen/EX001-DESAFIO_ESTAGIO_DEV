package processamento_conversor;

import java.util.HashMap;

public class NumerosRomanos {
    // Esta é uma classe responsável por converter numericamente
    // de romanos para decimais,

    private static final HashMap<Character, Integer> valor_romanos;

    static {
        // INICIALIZANDO O HASHMAP COM O VALOR NUMÉRICO DE CADA NÚMERO ROMANO

        valor_romanos = new HashMap<>();
        valor_romanos.put('I', 1);
        valor_romanos.put('V', 5);
        valor_romanos.put('X', 10);
        valor_romanos.put('G', 50);
        valor_romanos.put('C', 100);
        valor_romanos.put('D', 500);
        valor_romanos.put('M', 1000);
    }

    static Character[] simbolos_romanos = {'I', 'V', 'X', 'G', 'C', 'D', 'M'};

    // MÉTODOS PARA CONVERSÃO
    public static int converter_para_decimais(String romanos) {
        char[] romanos_decompostos = romanos.toCharArray();

        if (romanos_decompostos.length == 1) {
            return valor_romanos.get(romanos_decompostos[0]);
        } else {
            int result_acc = 0;
            int i = romanos_decompostos.length - 1;

            while ((i - 1) >= 0) {
                int num_direita = valor_romanos.get(romanos_decompostos[i]);
                int num_esquerda = valor_romanos.get(romanos_decompostos[i - 1]);
                if (num_direita > num_esquerda) {
                    result_acc += num_direita - num_esquerda;
                    i--;
                } else {
                    if (i > 1) {
                        result_acc += num_direita;
                    } else {
                        result_acc += num_esquerda + num_direita;
                    }
                }

                i--;
            }

            return result_acc;
        }

    }

    // MÉTODO AUXILIAR PARA A CONVERSÃO
    public static boolean simbolos_sao_romanos(String romanos) {
        // Esse método verifica se todos os símbolos são romanos, mas não
        // valida logicamente a cadeia de caracteres
        char[] romanos_decompostos = romanos.toCharArray();
        boolean e_romano = false;
        int i = 0;

        do {
            e_romano = false;
            int j = 0;

            while (j < simbolos_romanos.length && !e_romano) {
                if (romanos_decompostos[i] == simbolos_romanos[j]) {
                    e_romano = true;
                }

                j++;
            }

            i++;
        } while (i < romanos_decompostos.length && e_romano);

        return e_romano;
    }

    public static boolean esta_em_romanos(String romanos) {
        // Esse método verifica se o numero inserido, já considerado em caracteres romanos,
        // está logicamente ordenado em romanos.

        // O raciocínio adotado foi esse:
        // Tem que ser IV ou IX, ou seja:
        // Ser divisível por múltiplos de 1 e 5 (multiplicados de 10 em 10);
        // Ser divisível por múltiplos de 1 e 10 (multiplicados de 10 em 10);
        // Qualquer alternativa diferente desta é inválida
        boolean e_romano = false;
        char[] romanos_decompostos = romanos.toCharArray();

        if (romanos_decompostos.length == 1) {
            // 1o Caso: O numero possui apenas 1 digito
            return true;
        } else {
            // 2o caso: O numero possui vários dígitos, mas está em ordem decrescente

            if (!e_decrescente(romanos_decompostos)) {
                // 3o caso: O número não é decrescente, porém está logicamente correto em
                // termos de ser divisivel por 1 e 5, ou 1 e 10 de 10 em 10

                // ANOTAÇÃO -> VERIFICAR SE HÁ MAIS DE 1 SÍMBOLO NUMERICAMENTE MENOR ANTES
                // DE UM GRANDE. SE HOUVER, ENTÃO É INVÁLIDO. POR EXEMPLO:
                // IIIIX É UM NÚMERO INVÁLIDO
                int i = romanos_decompostos.length - 1;
                boolean par_valido = true;

                do {
                    if (i > 0) {
                        int num_direita = valor_romanos.get(romanos_decompostos[i]);
                        int num_esquerda = valor_romanos.get(romanos_decompostos[i - 1]);
                        boolean antes_esq_valido = true;

                        if (i > 1) {
                            int num_antes_da_esq = valor_romanos.get(romanos_decompostos[i - 2]);

                            if ((num_direita > num_esquerda) && (num_antes_da_esq <= num_esquerda)) {
                                antes_esq_valido = false;
                            }
                        }

                        int ordem = 1;
                        boolean acertou = false;

                        // Verificando se esse par é válido
                        if ((num_direita > num_esquerda) && antes_esq_valido) {
                            while (((((5 * ordem) < num_direita)
                                    && ((1 * ordem) < num_esquerda))
                                    || (((10 * ordem) < num_direita)
                                    && ((1 * ordem) < num_esquerda))) && !acertou) {

                                if ((((5 * ordem) == num_direita)
                                        && ((1 * ordem) == num_esquerda))
                                        || (((10 * ordem) == num_direita)
                                        && ((1 * ordem) == num_esquerda))) {
                                    acertou = true;
                                }

                                ordem *= 10;
                            }

                            if (acertou) {
                                par_valido = true;

                                if ((i - 1) >= 0) {
                                    i--;
                                }

                            } else {
                                par_valido = false;
                            }

                        } else {
                            par_valido = true;
                        }

                        if ((i - 1) >= 0) {
                            i--;
                        }
                    }
                } while (((i - 1) >= 0) && par_valido) ;

                if (i == 0) {
                    e_romano = true;
                }
            } else {
                e_romano = true;
            }

            return e_romano;
        }

    }

    private static boolean e_decrescente(char[] romanos_decompostos) {
        int i = 0;
        boolean e_decrescente = true;
        
        while (i < (romanos_decompostos.length - 1)) {
            int num_direita = valor_romanos.get(romanos_decompostos[i + 1]);
            int num_esquerda = valor_romanos.get(romanos_decompostos[i]);

            if (num_direita > num_esquerda) {
                e_decrescente = false;
            }

            i++;
        }
        
        return e_decrescente;
    }

}
