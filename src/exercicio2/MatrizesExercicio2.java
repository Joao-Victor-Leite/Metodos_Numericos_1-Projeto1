package exercicio2;

public class MatrizesExercicio2 {

    public double[][] matriz1 = {
            {10, 2, -1},
            {-3, -6, 2},
            {1, 1, 5}
    };

    public double[][] matriz2 = {
            {1, 4, 9, 16},
            {4, 9, 16, 25},
            {9, 16, 25, 36},
            {16, 25, 36, 49}
    };

    /**
     * Imprime uma matriz genérica
     *
     * @param matriz matriz a ser impressa.
     */
    public void imprimirMatriz(double[][] matriz) {
        for (double[] linha : matriz) {
            for (double elemento : linha) {
                System.out.printf("%.5f \t", elemento);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Retorna a ordem (número de linhas) da matriz fornecida.
     *
     * @param matriz matriz cuja dimensão será obtida.
     * @return número de linhas (ou colunas, se quadrada) da matriz.
     */
    public int getMatrizTamanho (double[][] matriz) {
        return matriz.length;
    }
}
