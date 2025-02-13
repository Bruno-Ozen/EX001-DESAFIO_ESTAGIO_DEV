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
            /*
            RACIOCÍNIO: PERCORRER DA ESQUERDA PARA A DIREITA DE 1 EM 1. CASO SEJA LOCALIZADO
            UM NÚMERO MENOR ANTES DE UM MAIOR (POR EXEMPLO, IV ou XIV), FAZER A
            SUBTRAÇÃO
             */
            int result_acc = 0;
            int i = 0;

            while (i <= romanos_decompostos.length - 1) {
                int numero_atual = valor_romanos.get(romanos_decompostos[i]);

                if (i < (romanos_decompostos.length - 1)) {
                    int proximo_numero = valor_romanos.get(romanos_decompostos[i + 1]);

                    // 1o Caso: Os dois números são iguais. Logo, apenas se soma eles
                    if (proximo_numero == numero_atual) {
                        result_acc += numero_atual + proximo_numero;

                        if ((i + 1) < romanos_decompostos.length) {
                            i++;
                        }
                    } else if (proximo_numero < numero_atual) {
                        // 2o Caso: O próximo número é menor que o atual. Nesse caso, é necessário
                        // verificar se há um maior depois do menor, para fazer a subtração.
                        // Caso não houver, apenas soma os dois números e joga no acumulador
                        boolean maior_depois_do_menor = false;
                        // VERIFICANDO SE EXISTE UM NÚMERO ALÉM DO PRÓXIMO
                        if ((i + 2) < romanos_decompostos.length) {
                            // CERTO, EXISTE. O NÚMERO ALÉM DO PRÓXIMO É MAIOR QUE O PRÓXIMO?
                            int numero_alem_do_proximo = valor_romanos.get(romanos_decompostos[i + 2]);

                            if (numero_alem_do_proximo > proximo_numero) {
                                maior_depois_do_menor = true;
                            }
                        }

                        if (maior_depois_do_menor) {
                            int numero_alem_do_proximo = valor_romanos.get(romanos_decompostos[i + 2]);
                            result_acc += numero_atual + (numero_alem_do_proximo - proximo_numero);
                            i++;
                            i++;
                        } else {
                            result_acc += numero_atual + proximo_numero;

                            if ((i + 1) < romanos_decompostos.length) {
                                i++;
                            }
                        }
                    } else if (proximo_numero > numero_atual) {
                        // 3o Caso: O próximo número é maior do que o número atual. Assim, subtrai-se do próximo
                        // o número atual, e joga no acumulador
                        result_acc += proximo_numero - numero_atual;

                        if ((i + 1) < romanos_decompostos.length) {
                            i++;
                        }

                    }

                } else if (i == (romanos_decompostos.length - 1)) {
                    // Soma o último número que sobrar
                    result_acc += numero_atual;
                }

                i++;
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
        } else if (infringiu_repeticoes(romanos_decompostos) || teve_maior_apos_menor(romanos_decompostos)) {
            return false;
        } else {
            // 2o caso: O numero possui vários dígitos, mas está em ordem decrescente

            if (!e_decrescente(romanos_decompostos)) {
                // 3o caso: O número não é decrescente, porém está logicamente correto em
                // termos de ser divisivel por 1 e 5, ou 1 e 10 em potencias de 10, de 2 em 2.

                // ANOTAÇÃO -> VERIFICAR SE HÁ MAIS DE 1 SÍMBOLO NUMERICAMENTE MENOR ANTES
                // DE UM GRANDE. SE HOUVER, ENTÃO É INVÁLIDO. POR EXEMPLO:
                // IIIIX É UM NÚMERO INVÁLIDO
                int i = romanos_decompostos.length - 1;
                boolean par_valido = true;

                do {
                    if (i > 0) {
                        int num_direita = valor_romanos.get(romanos_decompostos[i]);
                        int num_esquerda = valor_romanos.get(romanos_decompostos[i - 1]);

                        if (num_direita > num_esquerda) {
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
                            if (!antes_esq_valido) {
                                par_valido = false;
                            } else {
                                while (((((1 * ordem) <= num_esquerda) && ((5 * ordem) <= num_direita))
                                        || (((1 * ordem) <= num_esquerda) && ((10 * ordem) <= num_direita)))
                                        && !acertou) {

                                    if (((((1 * ordem) == num_esquerda) && (5 * ordem) == num_direita))
                                            || (((1 * ordem) == num_esquerda)) && ((10 * ordem) == num_direita)) {
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

                            }
                        }
                        if ((i - 1) >= 0) {
                            i--;
                        }
                    }
                } while (((i - 1) >= 0) && par_valido);

                if (i == 0 && par_valido) {
                    e_romano = true;
                }
            } else {
                e_romano = true;
            }

            return e_romano;
        }

    }

    private static boolean e_decrescente(char[] romanos_decompostos) {
        // RETORNA SE A SEQUENCIA DE CARACTERES ROMANOS É NUMÉRICAMENTE DECRESCENTE.
        // POR EXEMPLO: CXVIII

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

    private static boolean infringiu_repeticoes(char[] romanos_decompostos) {
        // RETORNA SE A SEQUENCIA DE NUMEROS ROMANOS REPETIU UM SÍMBOLO A PONTO
        // DE SE TORNAR UMA SEQUENCIA ERRADA. POR EXEMPLO:
        // XIIII É INVÁLIDO, POIS UM SÍMBOLO REPETIU MAIS QUE 3 VEZES

        int infracao_acc = 1;
        int i = 0;
        boolean repetiu_multiplo_de_5 = false;

        while ((i < (romanos_decompostos.length - 1)) && infracao_acc <= 3 && !repetiu_multiplo_de_5) {
            int numero = valor_romanos.get(romanos_decompostos[i]);
            int proximo_num = valor_romanos.get(romanos_decompostos[i + 1]);

            if (numero == proximo_num) {

                repetiu_multiplo_de_5 = verifica_se_e_multiplo_de_5(numero);

                infracao_acc++;
            } else {
                infracao_acc = 0;
            }

            i++;
        }

        return infracao_acc > 3 || repetiu_multiplo_de_5 || repetiu_apos_menor(romanos_decompostos);
    }

    private static boolean verifica_se_e_multiplo_de_5(int numero) {
        int ordem = 1;
        boolean e_multiplo_de_5 = false;

        while ((5 * ordem) <= numero) {

            if ((5 * ordem) == numero) {
                e_multiplo_de_5 = true;
            }

            ordem *= 10;
        }

        return e_multiplo_de_5;

    }

    private static boolean repetiu_apos_menor(char[] romanos_decompostos) {
        /*Raciocínio: Depois que um símbolo é usado uma vez, 
        o mesmo não poderá ser repetido após um símbolo
        menor que ele, a menos que seja para representar o número 9.
        Por exemplo: VIV é inválido, porém XIX é válido.
         */

        boolean repetiu_apos_menor = false;
        int i = 0;

        while ((i < romanos_decompostos.length - 1) && !repetiu_apos_menor) {
            int numero_esquerda = valor_romanos.get(romanos_decompostos[i]);
            int numero_direita = valor_romanos.get(romanos_decompostos[i + 1]);

            if (numero_direita < numero_esquerda) {
                int j = i + 2;

                while ((j < romanos_decompostos.length) && !repetiu_apos_menor) {
                    if (romanos_decompostos[i] == romanos_decompostos[j]) {
                        if (verifica_se_e_multiplo_de_5(romanos_decompostos[i])) {
                            repetiu_apos_menor = true;
                        }
                    }

                    j++;
                }
            }

            i++;
        }

        return repetiu_apos_menor;
    }

    private static boolean teve_maior_apos_menor(char[] romanos_decompostos) {
        /*Raciocínio: Se aparecer um símbolo maior após um símbolo menor, então esse método
        retorna verdadeiro, sinalizando que houve uma infração, pois:
        VIX não é um número romano válido, já que o mesmo possui o simbolo X (10) após
        o menor, no caso o V (5).
        Nesse exemplo, se I é menor que V, então todos os números depois de V devem ser menores que V.
         */

        boolean infringiu_maior_apos_menor = false;
        int i = 0;

        while ((i < romanos_decompostos.length - 1) && !infringiu_maior_apos_menor) {
            int numero_esquerda = valor_romanos.get(romanos_decompostos[i]);
            int numero_direita = valor_romanos.get(romanos_decompostos[i + 1]);

            if (numero_esquerda > numero_direita) {
                int j = i + 2;

                while ((j < romanos_decompostos.length) && !infringiu_maior_apos_menor) {
                    if (valor_romanos.get(romanos_decompostos[j]) > valor_romanos.get(romanos_decompostos[i])) {
                        infringiu_maior_apos_menor = true;
                    }

                    j++;
                }
            }

            i++;
        }

        return infringiu_maior_apos_menor;
    }

}
