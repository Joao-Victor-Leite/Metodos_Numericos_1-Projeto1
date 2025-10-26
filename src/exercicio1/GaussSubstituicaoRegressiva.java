package exercicio1;

public class GaussSubstituicaoRegressiva {
    /*
     * Resolve o sistema linear A·x = b pelo metodo da Eliminação de Gauss
     * com substituição regressiva.
     *
     * @param n tamanho da matriz
     * @param matriz matriz dos coeficientes (A)
     * @param b vetor dos termos independentes
     * @return vetor solução x, ou null se não houver solução única
     */
    public static double[] executtarGSR(int n, double[][] matriz, double[] b) {

        double m;
        double[][] Ab = MatrizBase.getMatrizAumentada(n, matriz, b);

        System.out.println("Matriz aumentada inicial (Ab):");
        MatrizBase.imprimirMatrizAumentada(Ab);

        for (int i = 0; i < n - 1; i++) {
            if (Ab[i][i] == 0.0) {
                System.out.println("Método falhou!");
                return null;
            }

            for (int j = i + 1; j < n; j++) {
                m = Ab[j][i] / Ab[i][i];
                for (int k = i; k <= n; k++) {
                    Ab[j][k] = Ab[j][k] - m * Ab[i][k];
                }
            }

            System.out.println("\nApós a iteração " + (i + 1) + ":");
            MatrizBase.imprimirMatrizAumentada(Ab);
        }

        if (Ab[n - 1][n - 1] == 0.0) {
            System.out.println("Não existe solução única!");
            return null;
        }

        double[] x = new double[n];
        x[n - 1] = Ab[n - 1][n] / Ab[n - 1][n - 1];

        for (int i = n - 2; i >= 0; i--) {
            double soma = 0.0;
            for (int j = i + 1; j < n; j++) {
                soma += Ab[i][j] * x[j];
            }
            x[i] = (Ab[i][n] - soma) / Ab[i][i];
        }

        System.out.println("\nMatriz final após eliminação:");
        MatrizBase.imprimirMatrizAumentada(Ab);

        return x;
    }

}
