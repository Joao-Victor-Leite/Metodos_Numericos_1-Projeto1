package exercicio2;

public class DecomposicaoLU {
    /**
     * Realiza a decomposição LU de uma matriz A.
     * A decomposição LU tem como objetivo escrever uma matriz A como o produto:
     *      A = L · U
     * onde:
     *  - L é uma matriz triangular inferior (com 1s na diagonal principal)
     *  - U é uma matriz triangular superior.
     * @param n     tamanho da matriz quadrada (n x n)
     * @param A     matriz dos coeficientes (A)
     * @return um vetor tridimensional contendo {L, U}, onde:
     *         resultado[0] = L e resultado[1] = U
     * @throws RuntimeException se for encontrado pivô nulo durante o processo
     */
    public static double[][][] executarLU (int n, double[][] A) {

        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        L[0][0] = 1.0;
        U[0][0] = A[0][0];

        for (int j = 1; j < n; j++) {
            U[0][j] = A[0][j];
            if (Math.abs(U[0][0]) < 1e-15) {
                throw new RuntimeException("Pivô nulo na inicialização (U[1,1] é zero)!");
            }
            L[j][0] = A[j][0] / U[0][0];
        }

        double soma;

        for (int i = 1; i < n-1; i++) {
            L[i][i] = 1;
            soma = 0;
            for (int k = 0; k < i; k++) {
                soma += L[i][k]*U[k][i];
            }
            U[i][i] = A[i][i] - soma;

            if (Math.abs(U[i][i]) < 1e-15) {
                throw new RuntimeException("Pivô nulo encontrado (U[" + (i+1) + "," + (i+1) + "] é zero)!");
            }

            double soma1;
            for (int j = i + 1; j < n; j++) {
                soma1 = 0;
                for (int k = 0; k < i; k++) {
                    soma1 += L[i][k] * U[k][j];
                }
                U[i][j] = A[i][j] - soma1;
            }

            double soma2;
            for (int j = i + 1; j < n; j++) {
                soma2 = 0;
                for (int k = 0; k < i; k++) {
                    soma2 += L[j][k] * U[k][i];
                }
                L[j][i] = (A[j][i] - soma2) / U[i][i];
            }

        }

        L[n-1][n-1] = 1;
        double soma3 = 0;
        for (int k = 0; k < n - 1; k++) {
            soma3 += L[n - 1][k] * U[k][n - 1];
        }

        U[n-1][n-1] = A[n-1][n-1] - soma3;

        if (Math.abs(U[n - 1][n - 1]) < 1e-15) {
            throw new RuntimeException("Pivô nulo encontrado (U[n,n] é zero)!");
        }

        return new double[][][]{L, U};

    }

    /**
     * Imprime uma matriz bidimensional formatada.
     *
     * @param matriz matriz a ser impressa
     */
    public static void imprimirMatriz(double[][] matriz) {
        for (double[] row : matriz) {
            for (double val : row) {
                System.out.printf("%.15f \t", val);
            }
            System.out.println();
        }
    }
}
