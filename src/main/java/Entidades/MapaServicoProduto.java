package Entidades;

import java.util.*;

/**
 * Mapeia serviços oferecidos pela oficina mecânica para listas de IDs de produtos
 * necessários para a execução desses serviços.
 * <p>
 * Mantém um mapa estático associando descrições de serviços a listas de IDs de produtos.
 * Permite consulta dos IDs dos produtos relacionados a um serviço específico.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class MapaServicoProduto {
    private static final Map<String, List<Integer>> servicoParaProdutos = new HashMap<>();

    static {
        servicoParaProdutos.put("Troca de oleo e filtro", Arrays.asList(1, 2));
        servicoParaProdutos.put("Troca de pastilhas de freio", Collections.singletonList(3));
        servicoParaProdutos.put("Troca de amortecedores", Collections.singletonList(4));
        servicoParaProdutos.put("Troca de pneus", Collections.singletonList(5));
        servicoParaProdutos.put("Troca de bateria", Collections.singletonList(6));
    }

    /**
     * Retorna a lista de IDs de produtos associados a um serviço específico.
     * 
     * @param descricaoServico Descrição do serviço para consulta
     * @return Lista de IDs de produtos necessários para o serviço,
     *         ou lista vazia se o serviço não estiver mapeado
     */
    public static List<Integer> getIdsProdutosParaServico(String descricaoServico) {
        return servicoParaProdutos.getOrDefault(descricaoServico, Collections.emptyList());
    }
}
