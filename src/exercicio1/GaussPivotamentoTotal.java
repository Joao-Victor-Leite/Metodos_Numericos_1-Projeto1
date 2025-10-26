package exercicio1;

public class GaussPivotamentoTotal {
    /*
     * Resolve o sistema linear A·x = b pelo metodo da Eliminação de Gauss
     * com pivotamento total.
     *
     * @param n     tamanho da matriz (número de equações/variáveis)
     * @param A     matriz dos coeficientes do sistema (A)
     * @param b     vetor dos termos independentes (b)
     * @return      vetor solução x, ou lança RuntimeException se não houver solução única
     * @throws RuntimeException se existir pivô nulo (indicando sistema singular ou sem solução única)
     */
    public static double[] executarGPT (int n, double[][] matriz, double[] b){
        double[] x = new double[n];
        double max;
        double valor;
        double m;

        int[] nlin = new int[n];
        int[] ncol = new int[n];
        for (int i = 0; i < n; i++) {
            nlin[i] = i;
            ncol[i] = i;
        }

        double[][] Ab = MatrizBase.getMatrizAumentada(n, matriz, b);
        System.out.println("Matriz aumentada inicial (Ab):");
        MatrizBase.imprimirMatrizAumentada(Ab);

        for (int k = 0; k < n - 1; k++) {
            int p = k, q = k;
            max = Math.abs(Ab[nlin[k]][ncol[k]]);
            for (int i = k; i < n; i++) {
                for (int j = k; j < n; j++) {
                    valor = Math.abs(Ab[nlin[i]][ncol[j]]);
                    if (valor > max) {
                        max = valor;
                        p = i;
                        q = j;
                    }
                }
            }

            if (Math.abs(max) < 1e-15) {
                throw new RuntimeException("Não existe solução única — pivô nulo!");
            }

            if (p != k) {
                int temp = nlin[k];
                nlin[k] = nlin[p];
                nlin[p] = temp;
                System.out.printf("%nTroca de linhas %d <-> %d%n", k + 1, p + 1);
            }

            if (q != k) {
                int temp = ncol[k];
                ncol[k] = ncol[q];
                ncol[q] = temp;
                System.out.printf("Troca de colunas %d <-> %d%n", k + 1, q + 1);
            }

            for (int i = k + 1; i < n; i++) {
                m = Ab[nlin[i]][ncol[k]] / Ab[nlin[k]][ncol[k]];
                for (int j = k + 1; j < n; j++) {
                    Ab[nlin[i]][ncol[j]] -= m * Ab[nlin[k]][ncol[j]];
                }
                Ab[nlin[i]][n] -= m * Ab[nlin[k]][n];
                Ab[nlin[i]][ncol[k]] = 0;
            }

            System.out.printf("%nApós eliminação na coluna %d:%n", k + 1);
            imprimirMatriz(Ab, nlin, ncol);

        }

        if (Math.abs(Ab[nlin[n - 1]][ncol[n - 1]]) < 1e-15) {
            throw new RuntimeException("Não existe solução única — pivô nulo no final!");
        }

        for (int i = n - 1; i >= 0; i--) {
            double soma = 0;
            for (int j = i + 1; j < n; j++) {
                soma += Ab[nlin[i]][ncol[j]] * x[ncol[j]];
            }
            x[ncol[i]] = (Ab[nlin[i]][n] - soma) / Ab[nlin[i]][ncol[i]];
        }

        System.out.println("\nMatriz aumentada final (após eliminação):");
        imprimirMatriz(Ab, nlin, ncol);

        return x;

    }

    /*
    * Imprime a matriz aumentada Ab considerando os índices atuais de linhas e colunas
    * após possíveis trocas de pivotamento total.
    */
    private static void imprimirMatriz(double[][] Ab, int[] nlin, int[] ncol) {
        for (int i = 0; i < Ab.length; i++) {
            for (int j = 0; j < Ab[0].length; j++) {
                if (j == Ab[0].length - 1)
                    System.out.printf("| %.7f", Ab[nlin[i]][j]);
                else
                    System.out.printf("%.7f\t", Ab[nlin[i]][ncol[j]]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
