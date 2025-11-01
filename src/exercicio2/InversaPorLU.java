package exercicio2;

public class InversaPorLU {
    /**
     * Calcula a matriz inversa a partir das matrizes L e U.
     *
     * @param L     matriz triangular inferior obtida da decomposição LU
     * @param U     matriz triangular superior obtida da decomposição LU
     * @param n     tamanho da matriz (ordem)
     * @return matriz inversa A⁻¹
     */

    public static double[][] calcularInversa(double[][] L, double[][] U, int n) {

        double[][] matrizInversa = new double[n][n];

        for (int i = 0; i < n; i++) {
            double[] e = new double[n];
            e[i] = 1.0;

            double[] y = new double[n];
            for (int j = 0; j < n; j++) {
                double soma = 0.0;
                for (int k = 0; k < j; k++) {
                    soma += L[j][k] * y[k];
                }
                y[j] = e[j] - soma;
            }

            double[] x = new double[n];
            for (int j = n - 1; j >= 0; j--) {
                double soma = 0.0;
                for (int k = j + 1; k < n; k++) {
                    soma += U[j][k] * x[k];
                }
                x[j] = (y[j] - soma) / U[j][j];
            }

            for (int j = 0; j < n; j++) {
                matrizInversa[j][i] = x[j];
            }

        }

        return matrizInversa;

    }

    /**
     * Verifica e imprime A * A^{-1} e checa se é (aproximadamente) a identidade.
     *
     * @param A         matriz original
     * @param inversa   matriz inversa calculada
     * @param n         ordem da matriz
     */
    public static void verificarInversa(double[][] A, double[][] inversa, int n) {
        double[][] produto = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double soma = 0.0;
                for (int k = 0; k < n; k++) {
                    soma += A[i][k] * inversa[k][j];
                }
                produto[i][j] = soma;
            }
        }

        System.out.println("\nProduto A · A⁻¹:");
        DecomposicaoLU.imprimirMatriz(produto);

    }
}
