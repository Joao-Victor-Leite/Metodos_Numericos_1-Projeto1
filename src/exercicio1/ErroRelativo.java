package exercicio1;

public class ErroRelativo {
    /**
     * Calcula o vetor de erros relativos de cada posição.
     * @param resultado     vetor calculado pelo metodo numérico
     * @param n             tamanho do vetor
     * @return vetor contendo o erro relativo de cada posição
     */
    public static double[] calcularErroRelativoPorPosicao(double[] resultado, int n) {
        double[] xReal = new double[n];
        double[] erroRelativo = new double[n];

        for (int i = 0; i < n; i++) {
            xReal[i] = 1.0;
            erroRelativo[i] = Math.abs((resultado[i] - xReal[i]) / xReal[i]);
        }

        return erroRelativo;
    }

    /**
     * Calcula o erro relativo global usando norma 2.
     * @param resultado     vetor calculado pelo metodo numérico
     * @param n             tamanho do vetor
     * @return valor do erro relativo total do vetor
     */
    public static double calcularErroRelativoTotal(double[] resultado, int n) {
        double[] xReal = new double[n];
        for (int i = 0; i < n; i++) {
            xReal[i] = 1.0;
        }

        double somaNumerador = 0.0;
        double somaDenominador = 0.0;

        for (int i = 0; i < n; i++) {
            somaNumerador += Math.pow(resultado[i] - xReal[i], 2);
            somaDenominador += Math.pow(xReal[i], 2);
        }

        return Math.sqrt(somaNumerador) / Math.sqrt(somaDenominador);
    }

    /**
     * Imprime os erros relativos de cada posição e o total.
     * @param resultado vetor calculado pelo metodo numérico
     * @param n tamanho do vetor
     */
    public static void imprimirErros(double[] resultado, int n) {
        double[] erros = calcularErroRelativoPorPosicao(resultado, n);
        System.out.println("\n--- Erro Relativo por Posição ---");
        for (int i = 0; i < n; i++) {
            System.out.printf("Erro relativo x[%d]: %.10e%n", i + 1, erros[i]);
        }

        double erroTotal = calcularErroRelativoTotal(resultado, n);
        System.out.printf("%nErro relativo total (norma 2): %.10e%n", erroTotal);
    }
}
