package processamento_conversor;

import java.util.ArrayList;
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
        int decimais_acc = 0;

        ArrayList<ArrayList<Character>> romanos_decompostos;
        romanos_decompostos = decompor(romanos.toCharArray());

        for (int i = 0; i < romanos_decompostos.size(); i++) {
            decimais_acc += monta_decimais(romanos_decompostos.get(i));
        }

        return decimais_acc;

    }

    // MÉTODOS AUXILIARES PARA A CONVERSÃO
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

    public static boolean logicamente_em_romanos(String romanos) {
        // BASEADO NA LÓGICA DE CONVERSÃO PARA DECIMAIS IMPLEMENTADA, O MÉTODO VERIFICASEEROMANOS FOI
        // MUDADO PARA LOGICAMENTE ROMANOS.
        // AGORA ESSE MÉTODO TRATA CADA SEQUÊNCIA DE CARACTERES ROMANOS COMO UMA ORDEM DECIMAL (UNIDADES,
        // DEZENAS, CENTENAS...
        /*
        EXEMPLOS:
        logicamente_em_romanos("IV")
        true
        ----------------------------------------
        logicamente_em_romanos("MMMCMXCIX")
        true
        ----------------------------------------
        logicamente_em_romanos("XIV")
        true
        ----------------------------------------
        logicamente_em_romanos("XIVI")
        false, porque seria 10 + 4, mas o ultimo 1 sobrando tenta competir com o 4 pela ordem das UNIDADES.
        as sequências romanas válidas são tratadas como um único número de uma ordem decimal. Se sobrar, é inválido.
        ----------------------------------------
        logicamente_em_romanos("XXXIII")
        true, porque seria 30 + 3
        
         */
        boolean e_romano = true;
        ArrayList<ArrayList<Character>> romanos_decompostos = decompor(romanos.toCharArray());
        int decimais_esq;
        int decimais_dir;
        int decimais_ordem_esq;
        int decimais_ordem_dir;
        int i = 0;

        System.out.println(romanos_decompostos);
        while ((i < romanos_decompostos.size()) && e_romano) {
            decimais_esq = monta_decimais(romanos_decompostos.get(i));

            // Tem próximo?
            if ((i + 1) <= (romanos_decompostos.size() - 1)) {
                // Sim.
                decimais_dir = monta_decimais(romanos_decompostos.get(i + 1));
                decimais_ordem_esq = NumerosDecimais.descobre_ordem(decimais_esq);
                decimais_ordem_dir = NumerosDecimais.descobre_ordem(decimais_dir);

                if ((decimais_ordem_esq <= decimais_ordem_dir)) {
                    e_romano = false;
                }

            }

            i++;
        }

        return e_romano;

    }

    public static int monta_decimais(ArrayList<Character> parte_romana) {
        // ESSE MÉTODO RETORNA O VALOR EM DECIMAIS DE CADA PARTE ROMANA DECOMPOSTA, NÃO CONSIDERANDO
        // A VALIDADE LÓGICA DOS MESMOS.

        /* RACIOCÍNIO: A PARTE ROMANA PODERÁ SER:
            NUMERO 1 -> 1 MULTIPLO DE 1
            NUMERO 2 -> 2 MULTIPLOS DE 1
            NUMERO 3 -> 3 MULTIPLOS DE 1
            NUMERO 4 -> 1 MULTIPLO DE 1 ANTES DE UM MULTIPLO DE 5
            NUMERO 5 -> 1 MULTIPLO DE 5
            NUMERO 6 -> 1 MULTIPLO DE 5 E 1 MULTIPLO DE 1
            NUMERO 7 -> 1 MULTIPLO DE 5 E 2 MULTIPLOS DE 1
            NUMERO 8 -> 1 MULTIPLO DE 5 E 3 MULTIPLOS DE 1
            NUMERO 9 -> 1 MULTIPLO DE 1 ANTES DE UM MULTIPLO DE 10
         */
        int qtd_digitos_pt_romana = parte_romana.size();
        int decimais_temp = 0;

        switch (qtd_digitos_pt_romana) {
            case 1:
                decimais_temp = valor_romanos.get(parte_romana.get(0));
                break;
            case 2:
                int numero_esquerda = valor_romanos.get(parte_romana.get(0));
                int numero_direita = valor_romanos.get(parte_romana.get(1));
                if (numero_esquerda == numero_direita) {
                    decimais_temp = numero_esquerda * 2;
                } else if (numero_esquerda > numero_direita) {
                    decimais_temp = numero_esquerda + numero_direita;
                } else if (numero_esquerda < numero_direita) {
                    decimais_temp = numero_direita - numero_esquerda;
                }

                break;
            case 3:
                int numero1 = valor_romanos.get(parte_romana.get(0));
                int numero2 = valor_romanos.get(parte_romana.get(1));
                int numero3 = valor_romanos.get(parte_romana.get(2));
                decimais_temp = numero1 + numero2 + numero3;
                break;
            case 4:
                numero1 = valor_romanos.get(parte_romana.get(0));
                numero2 = valor_romanos.get(parte_romana.get(1));
                numero3 = valor_romanos.get(parte_romana.get(2));
                int numero4 = valor_romanos.get(parte_romana.get(3));
                decimais_temp = numero1 + numero2 + numero3 + numero4;

                break;
        }

        return decimais_temp;
    }

    private static ArrayList<ArrayList<Character>> decompor(char[] romanos) {
        // ESSE MÉTODO DECOMPÕE UM NÚMERO ROMANO EM UMA LISTA DE PARTES DE NÚMEROS ROMANOS,
        // CADA UM REPRESENTANDO UMA ORDEM NUMÉRICA. ELE É VITAL PARA O FUNCIONAMENTO DO
        // CONVERSOR ROMANOS-DECIMAL E VALIDAÇÃO. EXEMPLOS:
        /*
        decompor([X, X, X]) -> [[X, X, X]] 
        (uma lista de listas de ordens. Nesse caso retorna uma única ordem, que seria a do 10
        
        decompor([X, X, X, X]) -> [[X, X, X], [X]]
        (duas ordens, ambas de 10, uma sendo 30 e outra 10. Casos como esse são usados no validador
        para descobrir se é inválido, já que não podem ter duas ordens numéricas iguais no sistema
        numérico romano.
        
        decompor([V, V]) -> [[V], [V])
        
        decompor([M, M, M, CC, X, X, X, I, I]) -> [[M, M, M], [C, C], [X, X, X], [I, I]])
        
         */

        ArrayList<ArrayList<Character>> romanos_decompostos = new ArrayList<ArrayList<Character>>();

        int i = 0;

        if (romanos.length > 1) {

            while (i < romanos.length) {
                ArrayList<Character> parte_romana = new ArrayList<Character>();
                int numero_atual = valor_romanos.get(romanos[i]);
                boolean formou_parte_romana = false;
                boolean incrementar = true;

                // Variável responsável por contar a quantidade de dígitos de 1, 10, 100 ou 1000 acumulados
                // em uma parte romana, para que caso exceda 3 partes, ele crie uma nova parte romana,
                // separando essa parte das outras. Por exemplo:
                // XXXX seria separado em [[XXX], [X]]
                while (!formou_parte_romana && (i < romanos.length)) {

                    // Tem próximo?
                    if ((i + 1) <= (romanos.length - 1)) {
                        // Sim
                        numero_atual = valor_romanos.get(romanos[i]);
                        int proximo_numero = valor_romanos.get(romanos[i + 1]);

                        // O próximo é igual?
                        if (numero_atual == proximo_numero) {
                            int ordem_atual = NumerosDecimais.descobre_ordem(numero_atual);
                            int ordem_anterior = 0;

                            // Esse numero atual possui um anterior?
                            if ((i - 1) >= 0) {
                                int numero_anterior = valor_romanos.get(romanos[i - 1]);
                                ordem_anterior = NumerosDecimais.descobre_ordem(numero_anterior);
                            }

                            if (ordem_anterior != 0) {
                                // O anterior é da mesma ordem numérica?

                                if (ordem_anterior == ordem_atual) {
                                    // O próximo não é múltiplo de 5 de 10 em 10?
                                    if (!eUm5de10em10(proximo_numero)) {
                                        // Se não é um 5 de 10 em 10, então ele pode ser
                                        // 1, 10, 100, 1000...
                                        // Tem mais algum além do próximo?
                                        if ((i + 2) <= (romanos.length - 1)) {
                                            // Sim.
                                            int prox_do_prox = valor_romanos.get(romanos[i + 2]);

                                            // O próximo do próximo é menor que o próximo?
                                            if (prox_do_prox < proximo_numero) {
                                                // Sim, então acumula ambos
                                                parte_romana.add(romanos[i]);
                                                parte_romana.add(romanos[i + 1]);
                                                formou_parte_romana = true;
                                            } else if (prox_do_prox == proximo_numero) {
                                                parte_romana.add(romanos[i]);
                                                parte_romana.add(romanos[i + 1]);
                                                parte_romana.add(romanos[i + 2]);
                                                formou_parte_romana = true;

                                                i++;

                                            }

                                            if ((i + 1) <= (romanos.length - 1)) {
                                                i++;
                                            }

                                        } else if ((parte_romana.size() + 2) <= 4) {
                                            // Não tem.

                                            parte_romana.add(romanos[i]);
                                            parte_romana.add(romanos[i + 1]);

                                            // Se tiver como avançar o ponteiro, avança 1
                                            i++;

                                            formou_parte_romana = true;

                                        } else {
                                            parte_romana.add(romanos[i]);

                                            formou_parte_romana = true;
                                        }

                                        // Certo. O número atual é maior que o próximo?
                                    } else {
                                        // Se ele é um 5 de 10 em 10, então ele pode ser
                                        // 5, 50, 500...
                                        parte_romana.add(romanos[i]);
                                        formou_parte_romana = true;

                                    }

                                } else {
                                    if (!parte_romana.isEmpty()) {
                                        formou_parte_romana = true;
                                        incrementar = false;
                                    } else {
                                        // O próximo não é múltiplo de 5 de 10 em 10?
                                        if (!eUm5de10em10(proximo_numero)) {
                                            // Se não é um 5 de 10 em 10, então ele pode ser
                                            // 1, 10, 100, 1000...
                                            // Tem mais algum além do próximo?
                                            if ((i + 2) <= (romanos.length - 1)) {
                                                // Sim.
                                                int prox_do_prox = valor_romanos.get(romanos[i + 2]);

                                                // O próximo do próximo é menor ou igual ao próximo?
                                                if (prox_do_prox < proximo_numero) {
                                                    // Sim, então acumula ambos
                                                    parte_romana.add(romanos[i]);
                                                    parte_romana.add(romanos[i + 1]);

                                                    formou_parte_romana = true;
                                                } else if (prox_do_prox == proximo_numero) {
                                                    parte_romana.add(romanos[i]);
                                                    parte_romana.add(romanos[i + 1]);
                                                } else if (prox_do_prox > proximo_numero) {
                                                    parte_romana.add(romanos[i]);
                                                    formou_parte_romana = true;
                                                }

                                                if ((i + 1) <= (romanos.length - 1)) {
                                                    i++;
                                                }

                                            } else {
                                                // Não tem.

                                                parte_romana.add(romanos[i]);
                                                parte_romana.add(romanos[i + 1]);

                                                // Se tiver como avançar o ponteiro, avança 1
                                                if ((i + 1) <= (romanos.length - 1)) {
                                                    i++;
                                                }

                                                formou_parte_romana = true;

                                            }

                                            // Certo. O número atual é maior que o próximo?
                                        } else {
                                            // Se ele é um 5 de 10 em 10, então ele pode ser
                                            // 5, 50, 500...
                                            parte_romana.add(romanos[i]);
                                            formou_parte_romana = true;

                                        }
                                    }

                                }
                            } else {
                                // Esse número tem um além do próximo?
                                if ((i + 2) <= (romanos.length - 1)) {
                                    // Sim.
                                    int prox_do_prox = valor_romanos.get(romanos[i + 2]);

                                    // O próximo do próximo é menor que o próximo?
                                    if (prox_do_prox < proximo_numero) {
                                        // Sim, então acumula ambos
                                        parte_romana.add(romanos[i]);
                                        parte_romana.add(romanos[i + 1]);
                                        i++;
                                        formou_parte_romana = true;
                                    } else if (prox_do_prox == proximo_numero) {
                                        parte_romana.add(romanos[i]);
                                        parte_romana.add(romanos[i + 1]);
                                        parte_romana.add(romanos[i + 2]);
                                        i++;
                                    } else if (prox_do_prox > proximo_numero) {
                                        parte_romana.add(romanos[i]);
                                        formou_parte_romana = true;
                                    }

                                } else {
                                    // Não tem.

                                    parte_romana.add(romanos[i]);
                                    parte_romana.add(romanos[i + 1]);

                                    // Se tiver como avançar o ponteiro, avança 1
                                    if ((i + 1) <= (romanos.length - 1)) {
                                        i++;
                                    }

                                    formou_parte_romana = true;

                                }

                            }

                        } else if (numero_atual > proximo_numero) {
                            // Sim

                            numero_atual = valor_romanos.get(romanos[i]);
                            int ordem_atual = NumerosDecimais.descobre_ordem(numero_atual);
                            int ordem_anterior = 0;
                            int ordem_proximo = NumerosDecimais.descobre_ordem(proximo_numero);

                            if (ordem_proximo == ordem_atual) {
                                // Esse numero atual possui um anterior?
                                if ((i - 1) >= 0) {
                                    int numero_anterior = valor_romanos.get(romanos[i - 1]);
                                    ordem_anterior = NumerosDecimais.descobre_ordem(numero_anterior);
                                }

                                // Ele tem um anterior?
                                if (ordem_anterior != 0) {
                                    // Sim

                                    if (ordem_anterior == ordem_atual) {
                                        int numero_anterior = valor_romanos.get(romanos[i - 1]);

                                        // O anterior é igual?
                                        if (numero_atual == numero_anterior) {
                                            // Sim
                                            // Então acumula o atual, e formou uma parte romana
                                            parte_romana.add(romanos[i]);
                                            formou_parte_romana = true;
                                        } else {
                                            // Não. Se ele não é igual ao anterior. Então ele vai acumulando
                                            // a parte romana, desde que os números façam parte da mesma ordem

                                            parte_romana.add(romanos[i]);
                                            parte_romana.add(romanos[i + 1]);

                                            if ((i + 1) <= (romanos.length - 1)) {
                                                i++;
                                            }

                                        }
                                    } else {
                                        if (!parte_romana.isEmpty()) {
                                            formou_parte_romana = true;
                                            incrementar = false;
                                        } else {
                                            int numero_anterior = valor_romanos.get(romanos[i - 1]);

                                            // O anterior é igual?
                                            if (numero_atual == numero_anterior) {
                                                // Sim
                                                // Então acumula o atual, e formou uma parte romana
                                                parte_romana.add(romanos[i]);
                                                formou_parte_romana = true;
                                            } else {
                                                // Não. Se ele não é igual ao anterior. Então ele vai acumulando
                                                // a parte romana, desde que os números façam parte da mesma ordem

                                                parte_romana.add(romanos[i]);
                                                parte_romana.add(romanos[i + 1]);

                                                if ((i + 1) <= (romanos.length - 1)) {
                                                    i++;
                                                }

                                            }
                                        }
                                    }
                                } else {
                                    parte_romana.add(romanos[i]);
                                }

                            } else {
                                parte_romana.add(romanos[i]);
                                formou_parte_romana = true;
                            }

                            // O número atual é menor que o próximo número?
                        } else if (numero_atual < proximo_numero) {
                            // Sim

                            if (!parte_romana.isEmpty()) {
                                formou_parte_romana = true;
                                incrementar = false;
                            } else {
                                // Os dois possuem a mesma ordem, ou uma ordem está uma acima da outra?
                                if (NumerosDecimais.descobre_ordem(numero_atual) == NumerosDecimais.descobre_ordem(proximo_numero)
                                        || (NumerosDecimais.descobre_ordem(numero_atual) * 10) == NumerosDecimais.descobre_ordem(proximo_numero)) {
                                    // Sim, então acumula-se ambos e incrementa
                                    parte_romana.add(romanos[i]);
                                    parte_romana.add(romanos[i + 1]);

                                    if ((i + 1) <= (romanos.length - 1)) {
                                        i++;
                                    }

                                    formou_parte_romana = true;

                                } else {
                                    // Certo. Se não estão nas ordens válidas, (uma acima da outra), então o método vai formar uma ordem separada
                                    // para cada, mesmo já sabendo que está numericamente errado para os números romanos.
                                    // Logo:

                                    parte_romana.add(romanos[i]);
                                    formou_parte_romana = true;
                                }

                            }

                        }

                        // Não formou uma parte romana? Então incrementa
                        if (!formou_parte_romana) {
                            i++;
                        }

                    } else {
                        // Não tem próximo. 
                        numero_atual = valor_romanos.get(romanos[i]);
                        int numero_anterior = valor_romanos.get(romanos[i - 1]);

                        if (numero_atual == numero_anterior) {
                            parte_romana.add(romanos[i]);
                        } else if (parte_romana.isEmpty()) {
                            parte_romana.add(romanos[i]);
                        } else if (numero_atual < numero_anterior) {
                            parte_romana.add(romanos[i]);
                            incrementar = true;
                        }

                        formou_parte_romana = true;

                    }

                }

                romanos_decompostos.add(parte_romana);

                if (incrementar) {
                    i++;
                }

            }
        } else {
            ArrayList<Character> parte_romana = new ArrayList<Character>();
            parte_romana.add(romanos[0]);
            romanos_decompostos.add(parte_romana);

        }

        return romanos_decompostos;

    }

    private static boolean eUm5de10em10(int numero) {
        boolean e_um5 = false;
        int cinco_10_em_10_acc = 5;

        while (cinco_10_em_10_acc <= numero) {
            if (cinco_10_em_10_acc == numero) {
                e_um5 = true;
            }

            cinco_10_em_10_acc *= 10;
        }

        return e_um5;

    }

    /*
    private static boolean ordem_por_ordem_da_10(int decimais_ordem_esq, int decimais_ordem_dir) {
        // ESSE MÉTODO RETORNA SE A ORDEM ESQUERDA DIVIDIDA PELA DIREITA DÁ 10
        
        return (decimais_ordem_esq / decimais_ordem_dir) == 10;

    }
     */
}
