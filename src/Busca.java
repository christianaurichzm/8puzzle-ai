import java.util.*;

public class Busca {
    private static boolean tabuleiroEstaNaColecao(Collection<Tabuleiro> tabuleiros, Tabuleiro filho) {
        for (Tabuleiro tabuleiro : tabuleiros) {
            if (tabuleiro.tabuleiroEhIgual(filho.getEstado())) {
                return true;
            }
        }
        return false;
    }

    public void busca(Tabuleiro tabuleiroInicial, TabuleiroComparator algoritmoDesejado) {
        PriorityQueue<Tabuleiro> fronteira;

        List<Tabuleiro> nodosVisitados = new ArrayList<>();
        fronteira = new PriorityQueue<>(algoritmoDesejado);
        int tamanhoMaximoFronteira = fronteira.size();
        fronteira.add(tabuleiroInicial);
        Tabuleiro tabuleiroAtual;

        while (!fronteira.isEmpty()) {
            if (fronteira.size() > tamanhoMaximoFronteira) {
                tamanhoMaximoFronteira = fronteira.size();
            }

            tabuleiroAtual = fronteira.poll();
            nodosVisitados.add(tabuleiroAtual);
            if (tabuleiroAtual.ehSolucao()) {
                Stack<Tabuleiro> caminho = new Stack<>();
                Tabuleiro auxiliar = tabuleiroAtual;
                while (auxiliar.getPai() != null) {
                    caminho.push(auxiliar);
                    auxiliar = auxiliar.getPai();
                }
                List<Tabuleiro> totalNodosCriados = new ArrayList<>();
                totalNodosCriados.addAll(new ArrayList<>(fronteira));
                totalNodosCriados.addAll(nodosVisitados);
                Resultado resultado = new Resultado(caminho, nodosVisitados.size(), totalNodosCriados.size(), tamanhoMaximoFronteira);
                Controlador.getInstance().finalizaJogo(resultado);
                return;
            }

            tabuleiroAtual.criaFilhos();

            for (Tabuleiro filho : tabuleiroAtual.getFilhos()) {
                if (!tabuleiroEstaNaColecao(nodosVisitados, filho)) {
                    if (!tabuleiroEstaNaColecao(fronteira, filho)) {
                        fronteira.add(filho);
                    }
                }
            }

        }

    }
}
