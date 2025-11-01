package exercicio1;

public class GaussPivotamentoParcialEscala {
    /**
     * Resolve o sistema linear A·x = b pelo metodo da Eliminação de Gauss
     * com pivotamento parcial com escala.
     *
     * @param n     tamanho da matriz
     * @param A     matriz dos coeficientes (A)
     * @param b     vetor dos termos independentes
     * @return      vetor solução x, ou null se não houver solução única
     */
    public static double[] executarGPPE (int n, double[][] A, double[] b){
        double[] x = new double[n];
        int[] nlin = new int[n];
        double[] s = new double[n];

        for (int i = 0; i < n; i++) {
            nlin[i] = i;
        }

        for (int i = 0; i < n; i++) {
            double maior = Math.abs(A[i][0]);
            for (int j = 1; j < n; j++) {
                if (Math.abs(A[i][j]) > maior)
                    maior = Math.abs(A[i][j]);
            }
            s[i] = maior;
        }

        System.out.println("\nFatores de escala (s):");
        for (int i = 0; i < n; i++) {
            System.out.printf("s[%d] = %.6f%n", i + 1, s[i]);
        }


        for (int i = 0; i < n - 1; i++) {
            int p = i;

            for (int k = i + 1; k < n; k++) {
                double razaoK = Math.abs(A[nlin[k]][i]) / s[nlin[k]];
                double razaoP = Math.abs(A[nlin[p]][i]) / s[nlin[p]];
                if (razaoK > razaoP)
                    p = k;
            }

            if (Math.abs(A[nlin[p]][i]) < 1e-15)
                throw new RuntimeException("Não existe solução única!");

            if (p != i) {
                int temp = nlin[i];
                nlin[i] = nlin[p];
                nlin[p] = temp;
            }

            for (int j = i + 1; j < n; j++) {
                double m = A[nlin[j]][i] / A[nlin[i]][i];
                A[nlin[j]][i] = 0.0;
                for (int k = i + 1; k < n; k++) {
                    A[nlin[j]][k] -= m * A[nlin[i]][k];
                }
                b[nlin[j]] -= m * b[nlin[i]];
            }

            System.out.printf("%nApós eliminação na coluna %d:%n", i + 1);
            imprimirMatriz(A, b, nlin);

        }

        for (int i = n - 1; i >= 0; i--) {
            double soma = 0;
            for (int j = i + 1; j < n; j++) {
                soma += A[nlin[i]][j] * x[j];
            }
            x[i] = (b[nlin[i]] - soma) / A[nlin[i]][i];
        }

        System.out.println("\nMatriz final após eliminação:");
        imprimirMatriz(A, b, nlin);

        return x;

    }

    /**
     * Imprime a matriz aumentada do sistema linear A·x = b
     *
     * @param A     matriz dos coeficientes (A)
     * @param b     vetor dos termos independentes
     * @param nlin  vetor de índices das linhas, indicando a ordem atual das linhas
     *              após trocas de pivotamento
     */
    private static void imprimirMatriz(double[][] A, double[] b, int[] nlin) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.printf("%.7f\t", A[nlin[i]][j]);
            }
            System.out.printf("| %.7f%n", b[nlin[i]]);
        }
        System.out.println();
    }
}
