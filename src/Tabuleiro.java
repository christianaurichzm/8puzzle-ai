import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    public static final int[][] estadoDesejado = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    private int[][] estado;
    private Tabuleiro pai;
    private List<Tabuleiro> filhos;
    private int xZero;
    private int yZero;
    private int custoTotal;

    public Tabuleiro(int[][] estado) {
        this.estado = estado;
        this.filhos = new ArrayList<>();
		this.custoTotal = 0;
    }

    private int encontraLinha(int numeroDesejado, int[][] estadoBuscado) {
        return (numeroDesejado - 1) / estadoBuscado.length;
    }

    private int encontraColuna(int numeroDesejado, int[][] estadoBuscado) {
        return (numeroDesejado - 1) % estadoBuscado.length;
    }

    public boolean tabuleiroEhIgual(int[][] tabuleiro) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (tabuleiro[i][j] != estado[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean ehSolucao() {
        return this.tabuleiroEhIgual(estadoDesejado);
    }

    public int quantidadePecasDiferentes() {
        int quantidade = 0;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (estado[i][j] != estadoDesejado[i][j]) {
                    quantidade++;
                }
            }
        }
        return quantidade;
    }

    public int distanciaManhattan() {
        int distancia = 0;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                int valor = estado[i][j];
                distancia += Math.abs(i - encontraLinha(valor, estadoDesejado)) + Math.abs(j - encontraColuna(valor, estadoDesejado));
            }
        }
        return distancia;
    }

    private void encontraVazio() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (estado[i][j] == 0) {
                    this.xZero = i;
                    this.yZero = j;
                }
            }
        }
    }

    private int[][] copiaMatrizAtual() {
        int[][] novaMatriz = new int[3][3];

        for (int i = 0; i <= 2; i++) {
            System.arraycopy(estado[i], 0, novaMatriz[i], 0, 3);

        }
        return novaMatriz;
    }

    public void criaFilhos() {
        encontraVazio();

        if (xZero != 0) {
            int[][] novoEstado = copiaMatrizAtual();
            novoEstado[xZero][yZero] = novoEstado[xZero - 1][yZero];
            novoEstado[xZero - 1][yZero] = 0;
            Tabuleiro novoTabuleiro = new Tabuleiro(novoEstado);
            novoTabuleiro.setPai(this);
            novoTabuleiro.setCustoTotal(this.custoTotal + 1);
            this.filhos.add(novoTabuleiro);
        }

        if (xZero != 2) {
            int[][] novoEstado = copiaMatrizAtual();
            novoEstado[xZero][yZero] = novoEstado[xZero + 1][yZero];
            novoEstado[xZero + 1][yZero] = 0;
            Tabuleiro novoTabuleiro = new Tabuleiro(novoEstado);
            novoTabuleiro.setPai(this);
            novoTabuleiro.setCustoTotal(this.custoTotal + 1);
            this.filhos.add(novoTabuleiro);
        }

        if (yZero != 0) {
            int[][] novoEstado = copiaMatrizAtual();
            novoEstado[xZero][yZero] = novoEstado[xZero][yZero - 1];
            novoEstado[xZero][yZero - 1] = 0;
            Tabuleiro novoTabuleiro = new Tabuleiro(novoEstado);
            novoTabuleiro.setPai(this);
            novoTabuleiro.setCustoTotal(this.custoTotal + 1);
            this.filhos.add(novoTabuleiro);
        }

        if (yZero != 2) {
            int[][] novoEstado = copiaMatrizAtual();
            novoEstado[xZero][yZero] = novoEstado[xZero][yZero + 1];
            novoEstado[xZero][yZero + 1] = 0;
            Tabuleiro novoTabuleiro = new Tabuleiro(novoEstado);
            novoTabuleiro.setPai(this);
            novoTabuleiro.setCustoTotal(this.custoTotal + 1);
            this.filhos.add(novoTabuleiro);
        }
    }

    public void imprimeTabuleiro() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (this.estado[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(this.estado[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int[][] getEstado() {
        return this.estado;
    }

    public List<Tabuleiro> getFilhos() {
        return this.filhos;
    }

    public Tabuleiro getPai() {
        return this.pai;
    }

    public void setPai(Tabuleiro pai) {
        this.pai = pai;
    }

    public int getCustoTotal() {
        return this.custoTotal;
    }

    public void setCustoTotal(int custo) {
        this.custoTotal = custo;
    }
}
