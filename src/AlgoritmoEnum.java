import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum AlgoritmoEnum {
    CUSTO_UNIFORME(1L, "Custo uniforme"),
    A_ESTRELA_SIMPLES(2L, "A* com heurística simples"),
    A_ESTRELA_MANHATTAN(3L, "A* com heurística da distância de Manhattan");

    private final Long id;
    private final String nome;

    AlgoritmoEnum(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static AlgoritmoEnum getEnumById(Long id) {
        return Arrays.stream(values()).filter(value -> value.id.equals(id)).findFirst().orElse(null);
    }

    public static Set<Long> opcoesAlgoritmoDisponiveis() {
        return Arrays.stream(values()).map(value -> value.id).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
