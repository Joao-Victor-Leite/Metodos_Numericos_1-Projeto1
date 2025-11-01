import static exercicio1.GaussPivotamentoParcial.executarGPP;
import static exercicio1.GaussPivotamentoParcialEscala.executarGPPE;
import static exercicio1.GaussPivotamentoTotal.executarGPT;
import static exercicio1.GaussSiedel.executarGSiedel;
import static exercicio1.GaussSubstituicaoRegressiva.executtarGSR;
import static exercicio1.Jacobi.executarJacobi;
import static exercicio1.SobreRelaxamento.executarSR;
import static exercicio1.ErroRelativo.*;
import static exercicio2.DecomposicaoLU.executarLU;
import static exercicio2.InversaPorLU.calcularInversa;

import exercicio1.*;
import exercicio2.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int[] valoresN = {5, 9, 15};

        while (true) {
            System.out.println("==== MENU PRINCIPAL ====");
            System.out.println("1 - Exercício 1");
            System.out.println("2 - Exercício 2");
            System.out.println("9 - Limpar terminal");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }

            switch (opcao) {
                case 1 -> menuExercicio1(sc, valoresN);
                case 2 -> menuExercicio2(sc);
                case 9 -> limparTerminal();
                default -> System.out.println("Opção inválida!\n");
            }

            System.out.println("\n-------------------------------------\n");
        }
        sc.close();
    }

    private static void menuExercicio1(Scanner sc, int[] valoresN) {
        System.out.println("\n=== MÉTODOS DISPONÍVEIS ===");
        System.out.println("1 - Gauss com Substituição Regressiva");
        System.out.println("2 - Gauss com Pivotamento Parcial");
        System.out.println("3 - Gauss com Pivotamento Parcial com Escala");
        System.out.println("4 - Gauss com Pivotamento Total");
        System.out.println("5 - Jacobi");
        System.out.println("6 - Gauss-Seidel");
        System.out.println("7 - Sobre-Relaxamento");
        System.out.print("Escolha o método: ");
        int metodo = sc.nextInt();

        System.out.println("\nEscolha o valor de n:");
        for (int i = 0; i < valoresN.length; i++) {
            System.out.println((i + 1) + " - n = " + valoresN[i]);
        }

        System.out.print("Sua escolha: ");
        int escolhaN = sc.nextInt();

        if (escolhaN < 1 || escolhaN > valoresN.length) {
            System.out.println("Opção inválida para n!\n");
            return;
        }

        int n = valoresN[escolhaN - 1];
        double tol = 1e-7;
        int maxIter = 100;
        double w = 1.1;

        MatrizBase hilbert = new HilbertMatrix(n);
        hilbert.imprimirMatriz();
        System.out.println();
        hilbert.imprimirVetorB();
        System.out.println();

        double[][] A = copiarMatriz(hilbert.getMatriz());
        double[] b = hilbert.getVetorB();

        double[] resultado = null;

        switch (metodo) {
            case 1 -> resultado = executtarGSR(n, A, b);
            case 2 -> resultado = executarGPP(n, A, b);
            case 3 -> resultado = executarGPPE(n, A, b);
            case 4 -> resultado = executarGPT(n, A, b);
            case 5 -> resultado = executarJacobi(n, A, b, tol, maxIter);
            case 6 -> resultado = executarGSiedel(n, A, b, tol, maxIter);
            case 7 -> resultado = executarSR(n, A, b, w, tol, maxIter);
            default -> System.out.println("Método inválido!");
        }

        if (resultado != null) {
            System.out.println("\n--- Solução Final ---");
            if (metodo == 7) {
                System.out.println("(w = " + w + ")");
            }
            for (int i = 0; i < resultado.length; i++){
                System.out.printf("x[%d] = %.15f%n", i + 1, resultado[i]);
            }

            imprimirErros(resultado, n);

        }
    }

    private static void menuExercicio2(Scanner sc) {
        System.out.println("\n=== FATORAÇÃO LU ===");
        System.out.println("=== ESCOLHA A MATRIZ ===");

        MatrizesExercicio2 m = new MatrizesExercicio2();
        System.out.println("Matriz 1:");
        m.imprimirMatriz(m.matriz1);

        System.out.println("Matriz 2:");
        m.imprimirMatriz(m.matriz2);

        System.out.print("Escolha a matriz (1 ou 2): ");
        int matrizEscolhida = sc.nextInt();

        double[][][] resultado = null;
        int n = 0;

        switch (matrizEscolhida) {
            case 1 -> {
                n = m.getMatrizTamanho(m.matriz1);
                resultado = executarLU(n, m.matriz1);
            }
            case 2 -> {
                n = m.getMatrizTamanho(m.matriz2);
                resultado = executarLU(n, m.matriz2);
            }
            default -> System.out.println("Ecolha inválida!");
        }

        if (resultado != null) {
            double[][] L = resultado[0];
            double[][] U = resultado[1];

            System.out.println("\nMatriz L obtida:");
            DecomposicaoLU.imprimirMatriz(L);
            System.out.println("\nMatriz U obtida:");
            DecomposicaoLU.imprimirMatriz(U);

            double[][] inversa = calcularInversa(L, U, n);
            System.out.println("\nMatriz inversa A⁻¹ obtida:");
            DecomposicaoLU.imprimirMatriz(inversa);

            if (matrizEscolhida == 1) {
                InversaPorLU.verificarInversa(m.matriz1, inversa, n);
            } else {
                InversaPorLU.verificarInversa(m.matriz2, inversa, n);
            }
        }

    }

    /**
     * Recebe uma matriz como parametro e retorna sua copia
     *
     * @param original matriz original a ser copiada
     * @return matriz  cópia de mesmas dimensões da original
     */
    private static double[][] copiarMatriz(double[][] original) {
        int n = original.length;
        double[][] copia = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copia[i][j] = original[i][j];
            }
        }
        return copia;
    }

    private static void limparTerminal() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (System.console() != null) {
                if (os.contains("windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } else {
                for (int i = 0; i < 50; i++) System.out.println();
            }
        } catch (IOException | InterruptedException e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}