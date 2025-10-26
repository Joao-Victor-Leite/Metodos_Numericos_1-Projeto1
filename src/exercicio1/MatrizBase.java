package exercicio1;

public abstract class MatrizBase {

    protected double[][] matriz;
    protected double[] b;
    protected int n;

    public MatrizBase(int n) {
        this.n = n;
        this.matriz = new double[n][n];
        this.b = new double[n];
        gerarMatriz();
        gerarVetorB();
    }

    protected abstract void gerarMatriz();

    protected void gerarVetorB() {
        for (int i = 0; i < n; i++) {
            double soma = 0.0;
            for (int j = 0; j < n; j++) {
                soma += matriz[i][j];
            }
            b[i] = soma;
        }
    }

    public void imprimirMatriz() {
        System.out.println("\nMatriz de Hilbert (" + n + "x" + n + "):");
        for (double[] linha : matriz) {
            for (double val : linha)
                System.out.printf("%.7f \t", val);
            System.out.println();
        }
    }

    public void imprimirVetorB() {
        System.out.println("Vetor B:");
        for (int i = 0; i < n; i++)
            System.out.printf("b[%d] = %.7f%n", i + 1, b[i]);
    }

    public static double[][] getMatrizAumentada(int n, double[][] matriz, double[]b) {
        double[][] Ab = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matriz[i], 0, Ab[i], 0, n);
            Ab[i][n] = b[i];
        }
        return Ab;
    }

    public static void imprimirMatrizAumentada(double[][] Ab) {
        for (double[] linha : Ab) {
            for (int j = 0; j < linha.length; j++) {
                if (j == linha.length - 1)
                    System.out.printf("| %.7f ", linha[j]);
                else
                    System.out.printf("%.7f\t", linha[j]);
            }
            System.out.println();
        }
    }

    public double[][] getMatriz() { return matriz; }
    public double[] getVetorB() { return b; }

}
