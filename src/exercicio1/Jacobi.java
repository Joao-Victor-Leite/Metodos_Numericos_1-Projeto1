package exercicio1;

public class Jacobi {
    /**
     * Resolve o sistema linear A·x = b pelo metodo iterativo de Jacobi.
     *
     * @param n         tamanho da matriz (número de equações/variáveis)
     * @param A         matriz dos coeficientes do sistema (A)
     * @param b         vetor dos termos independentes (b)
     * @param tol       tolerância para o critério de parada (erro máximo permitido)
     * @param maxIter   número máximo de iterações permitidas
     * @return vetor solução aproximada x, ou o último vetor calculado caso o metodo
     *         não convirja dentro do número máximo de iterações
     */
    public static double[] executarJacobi (int n, double[][] A, double[] b, double tol, int maxIter){
        double[] x = new double[n];
        double[] xNovo = new double[n];
        double erro;

        for (int i = 0; i < n; i++) {
            x[i] = b[i] / A[i][i];
        }

        System.out.println("\nIterações do Método de Jacobi:");

        for (int k = 1; k <= maxIter; k++) {
            for (int i = 0; i < n; i++) {
                double soma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) soma += A[i][j] * x[j];
                }
                xNovo[i] = (b[i] - soma) / A[i][i];
            }

            erro = 0;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs((xNovo[i] - x[i]) / xNovo[i]);
                if (diff > erro) erro = diff;
            }

            System.out.printf("Iteração %d, erro = %.8f%n", k, erro);

            if (erro < tol) {
                System.out.println("Convergência atingida!\n");
                return xNovo;
            }

            System.arraycopy(xNovo, 0, x, 0, n);

        }

        System.out.println("Método de Jacobi não convergiu após " + maxIter + " iterações.");
        return xNovo;

    }
}
