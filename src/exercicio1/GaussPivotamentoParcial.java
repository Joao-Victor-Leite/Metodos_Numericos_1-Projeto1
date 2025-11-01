package exercicio1;

public class GaussPivotamentoParcial {
    /**
     * Resolve o sistema linear A·x = b pelo metodo da Eliminação de Gauss
     * com pivotamento parcial.
     *
     * @param n     tamanho da matriz
     * @param A     matriz dos coeficientes (A)
     * @param b     vetor dos termos independentes
     * @return      vetor solução x, ou null se não houver solução única
     */
    public static double[] executarGPP (int n, double[][] A, double[] b){
        double[] x = new double[n];

        double[][] Ab = MatrizBase.getMatrizAumentada(n, A, b);

        System.out.println("Matriz aumentada inicial (Ab):");
        MatrizBase.imprimirMatrizAumentada(Ab);

        for (int i = 0; i < n - 1; i++) {
            int p = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(Ab[k][i]) > Math.abs(Ab[p][i])) {
                    p = k;
                }
            }

            if (Ab[p][i] == 0) {
                throw new RuntimeException("Não existe solução única!");
            }

            if (p != i) {
                double[] temp = Ab[i];
                Ab[i] = Ab[p];
                Ab[p] = temp;

                System.out.printf("%nTroca de linhas %d <-> %d:%n", i + 1, p + 1);
                MatrizBase.imprimirMatrizAumentada(Ab);
            }

            for (int j = i + 1; j < n; j++) {
                double m = Ab[j][i] / Ab[i][i];
                for (int k = i; k <= n; k++) {
                    Ab[j][k] -= m * Ab[i][k];
                }
            }

            System.out.printf("%nApós eliminação na coluna %d:%n", i + 1);
            MatrizBase.imprimirMatrizAumentada(Ab);

        }

        if (Ab[n - 1][n - 1] == 0) {
            throw new RuntimeException("Não existe solução única!");
        }


        for (int i = n - 1; i >= 0; i--) {
            double soma = 0;
            for (int j = i + 1; j < n; j++) {
                soma += Ab[i][j] * x[j];
            }
            x[i] = (Ab[i][n] - soma) / Ab[i][i];
        }

        System.out.println("\nMatriz aumentada final (após eliminação):");
        MatrizBase.imprimirMatrizAumentada(Ab);

        return x;

    }

}
