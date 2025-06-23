package Entidades;

import java.util.*;

public class MapaServicoProduto {
    private static final Map<String, List<Integer>> servicoParaProdutos = new HashMap<>();

    static {
        servicoParaProdutos.put("Troca de oleo e filtro", Arrays.asList(1, 2));
        servicoParaProdutos.put("Troca de pastilhas de freio", Collections.singletonList(3));
        servicoParaProdutos.put("Troca de amortecedores", Collections.singletonList(4));
        servicoParaProdutos.put("Troca de pneus", Collections.singletonList(5));
        servicoParaProdutos.put("Troca de bateria", Collections.singletonList(6));
    }

    public static List<Integer> getIdsProdutosParaServico(String descricaoServico) {
        return servicoParaProdutos.getOrDefault(descricaoServico, Collections.emptyList());
    }
}
