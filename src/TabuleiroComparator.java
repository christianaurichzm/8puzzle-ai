import java.util.Comparator;

public class TabuleiroComparator implements Comparator<Tabuleiro> {
    private AlgoritmoEnum algoritmo;

    public TabuleiroComparator(AlgoritmoEnum algoritmo) {
        this.algoritmo = algoritmo;
    }

    @Override
    public int compare(Tabuleiro o1, Tabuleiro o2) {
        switch (algoritmo) {
            case CUSTO_UNIFORME:
                return this.custoUniforme(o1, o2);
            case A_ESTRELA_SIMPLES:
                return this.pecasIguais(o1, o2);
            case A_ESTRELA_MANHATTAN:
                return this.manhattan(o1, o2);
        }
        return 0;
    }

    private int custoUniforme(Tabuleiro o1, Tabuleiro o2) {
        return (o1.getCustoTotal() - o2.getCustoTotal());
    }

    private int pecasIguais(Tabuleiro o1, Tabuleiro o2) {
        return (o1.getCustoTotal() + o1.quantidadePecasDiferentes()) - (o2.getCustoTotal() + o2.quantidadePecasDiferentes());
    }

    private int manhattan(Tabuleiro o1, Tabuleiro o2) {
        return (o1.getCustoTotal() + o1.distanciaManhattan()) - (o2.getCustoTotal() + o2.distanciaManhattan());
    }
}
