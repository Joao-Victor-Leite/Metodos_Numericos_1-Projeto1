package exercicio1;

public class SobreRelaxamento {
    /*
     * Resolve o sistema linear A·x = b pelo metodo iterativo de Sobre-relaxamento (SOR).
     *
     * @param n       tamanho da matriz (número de equações/variáveis)
     * @param A       matriz dos coeficientes do sistema (A)
     * @param b       vetor dos termos independentes (b)
     * @param w       fator de relaxação (0 < w < 2)
     * @param tol     tolerância para o critério de parada (erro máximo permitido)
     * @param maxIter número máximo de iterações permitidas
     * @return vetor solução aproximada x, ou o último vetor calculado caso o metodo
     *         não convirja dentro do número máximo de iterações
     */
    public static double[] executarSR(int n, double[][] A, double[] b, double w, double tol, int maxIter) {
        double[] x = new double[n];
        double[] xAnterior = new double[n];
        double erro;

        for (int i = 0; i < n; i++) {
            x[i] = b[i] / A[i][i];
        }

        System.out.println("\nIterações do Método Sobre-relaxamento (w = " + w + "):");

        for (int k = 1; k <= maxIter; k++) {
            System.arraycopy(x, 0, xAnterior, 0, n);

            for (int i = 0; i < n; i++) {
                double soma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) soma += A[i][j] * x[j];
                }

                double xNovo = (b[i] - soma) / A[i][i];
                x[i] = (1 - w) * x[i] + w * xNovo;  // Relaxação
            }

            erro = 0;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs((x[i] - xAnterior[i]) / x[i]);
                if (diff > erro) erro = diff;
            }

            System.out.printf("Iteração %d, erro = %.8f%n", k, erro);

            if (erro < tol) {
                System.out.println("Convergência atingida!\n");
                return x;
            }
        }

        System.out.println("Método de Sobre-relaxamento não convergiu após " + maxIter + " iterações.");
        return x;

    }
}
