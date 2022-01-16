public class Controlador {
    private static Controlador controlador;
    private Tela tela;

    public static Controlador getInstance() {
        if (controlador == null) {
            controlador = new Controlador();
        }
        return controlador;
    }

    public void abreTelaInicial() {
        this.tela = new Tela();
        tela.configuracaoInteligencia();
    }

    public void iniciaJogo(Tabuleiro tabuleiroInicial, TabuleiroComparator algoritmoDesejado) {
        Busca busca = new Busca();
        long tempoInicial = System.currentTimeMillis();
        busca.busca(tabuleiroInicial, algoritmoDesejado);
        long tempoTotal = System.currentTimeMillis() - tempoInicial;
        System.out.println("Caminho encontrado em " + tempoTotal + " milissegundos");
    }

    public void finalizaJogo(Resultado resultado) {
        this.tela.mostraResultado(resultado);
    }
}
