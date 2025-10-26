package exercicio1;

public class HilbertMatrix extends MatrizBase{

    public HilbertMatrix(int n) {
        super(n);
    }

    @Override
    protected void gerarMatriz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = 1.0 / (i + j + 1);
            }
        }
    }

}
