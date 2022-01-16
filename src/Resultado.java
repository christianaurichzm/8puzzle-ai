import java.util.Stack;

public class Resultado {
    private final Stack<Tabuleiro> caminho;
    private final int totalNodosVisitados;
    private final int totalNodosCriados;
    private final int maiorTamanhoFronteira;

    public Resultado(Stack<Tabuleiro> caminho, int totalNodosVisitados, int totalNodosCriados, int maiorTamanhoFronteira) {
        this.caminho = caminho;
        this.totalNodosVisitados = totalNodosVisitados;
        this.totalNodosCriados = totalNodosCriados;
        this.maiorTamanhoFronteira = maiorTamanhoFronteira;
    }

    public Stack<Tabuleiro> getCaminho() {
        return caminho;
    }

    public int getTotalNodosVisitados() {
        return totalNodosVisitados;
    }

    public int getTotalNodosCriados() {
        return totalNodosCriados;
    }

    public int getMaiorTamanhoFronteira() {
        return maiorTamanhoFronteira;
    }
}
