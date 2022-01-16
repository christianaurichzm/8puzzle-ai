import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Tela {
    private final Scanner sc;
    private Tabuleiro tabuleiro;

    public Tela() {
        this.sc = new Scanner(System.in);
    }

    public Tabuleiro criaTabuleiro() {
        this.tabuleiro = new Tabuleiro(new int[3][3]);
        int[][] estadoTabuleiro = tabuleiro.getEstado();
        Set<Integer> jaDigitados = new HashSet<>();

        System.out.println("Oi, sou a I.A fraca que vai resolver suas partidas de 8 puzzle.");
        System.out.println("Vamos começar criando a configuração inicial do tabuleiro.");
        System.out.println("Os valores digitados devem estar no intervalo fechado de 0 a 8 e não podem se repetir." + "\n");

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.println("Digite o valor da posição " + i + ", " + j + ":");
                verificaSeEhNumero();
                int valorDigitado = sc.nextInt();
                sc.nextLine();
                while (valorDigitado < 0 || valorDigitado > 8 || jaDigitados.contains(valorDigitado)) {
                    System.out.println("\u001B[31m" + "Valor inválido!" + "\u001B[0m");
                    System.out.println("Por favor, tente novamente digitando um número válido:");
                    verificaSeEhNumero();
                    valorDigitado = sc.nextInt();
                    sc.nextLine();
                }
                estadoTabuleiro[i][j] = valorDigitado;
                jaDigitados.add(valorDigitado);
                this.tabuleiro.imprimeTabuleiro();
            }
        }
        return this.tabuleiro;
    }

    public TabuleiroComparator escolheAlgoritmo() {
        System.out.println("Agora iremos escolher o algoritmo que usarei para resolver o problema.");
        System.out.println("Digite o número referente ao algoritmo desejado:");
        Arrays.stream(AlgoritmoEnum.values()).forEach(this::mostraOpcoesAlgoritmo);
        long opcaoAlgoritmo = this.sc.nextLong();

        Set<Long> opcoesAlgoritmoDisponiveis = AlgoritmoEnum.opcoesAlgoritmoDisponiveis();

        while (!opcoesAlgoritmoDisponiveis.contains(opcaoAlgoritmo)) {
            Long primeiraOpcao = opcoesAlgoritmoDisponiveis.stream().findFirst().orElse(null);
            Long ultimaOpcao = opcoesAlgoritmoDisponiveis.stream().reduce((anterior, atual) -> atual).orElse(null);
            System.out.println("Só é possível escolher um número entre " + primeiraOpcao + " e " + ultimaOpcao + ":");
            opcaoAlgoritmo = this.sc.nextLong();
        }

        AlgoritmoEnum algoritmoEscolhido = AlgoritmoEnum.getEnumById(opcaoAlgoritmo);

        return new TabuleiroComparator(algoritmoEscolhido);
    }

    public void configuracaoInteligencia() {
        Tabuleiro tabuleiroInicial = this.criaTabuleiro();
        TabuleiroComparator algoritmoDesejado = this.escolheAlgoritmo();
        Controlador.getInstance().iniciaJogo(tabuleiroInicial, algoritmoDesejado);
    }

    public void mostraResultado(Resultado resultado) {
        int tamanhoPilha = resultado.getCaminho().size();
        System.out.println("Tabuleiro inicial:");
        this.tabuleiro.imprimeTabuleiro();
        for (int i = 0; i < tamanhoPilha; i++) {
            System.out.println("Jogada número: " + (i + 1));
            resultado.getCaminho().pop().imprimeTabuleiro();
        }
        System.out.println("Total de nodos visitados: " + resultado.getTotalNodosVisitados());
        System.out.println("Total de nodos criados: " + resultado.getTotalNodosCriados());
        System.out.println("Tamanho máximo da fronteira durante a busca: " + resultado.getMaiorTamanhoFronteira());
        System.out.println("Tamanho do caminho: " + tamanhoPilha);
    }

    public void verificaSeEhNumero() {
        while (!sc.hasNextInt()) {
            System.out.println("\u001B[31m" + "O valor deve ser um número:" + "\u001B[0m");
            sc.nextLine();
        }
    }

    public void mostraOpcoesAlgoritmo(AlgoritmoEnum algoritmo) {
        System.out.println(algoritmo.getId() + " - " + algoritmo.getNome());
    }
}
