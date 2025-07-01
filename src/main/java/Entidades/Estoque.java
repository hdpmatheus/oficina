package Entidades;

import Comparator.ProdutoIdComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Json.Jsonproduto;

/**
 * Representa o estoque de produtos da oficina mecânica.
 * 
 * Esta classe gerencia a lista de produtos, permitindo operações como carregar
 * produtos de arquivo, adicionar, buscar, alterar, remover quantidade e listar produtos.
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Estoque {

    private List<Produto> produtos;

    /**
     * Construtor que inicializa o estoque com uma lista vazia de produtos.
     */
    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    /**
     * Carrega os produtos do arquivo JSON para a lista de produtos do estoque.
     */
    public void carregarProdutosDoArquivo() {
        this.produtos = Jsonproduto.carregarProdutos();
    }

    /**
     * Adiciona um novo produto ao estoque.
     * 
     * @param p Produto a ser adicionado
     */
    public void criarProduto(Produto p) {
        this.produtos.add(p);
    }

    /**
     * Busca um produto no estoque pelo seu ID utilizando busca binária.
     * 
     * @param id ID do produto a ser buscado
     * @return Produto encontrado ou null se não existir
     */
    public Produto buscarProduto(int id) {
        Produto produtoBusca = new Produto(id, null, 0, 0.0);
        int index = Collections.binarySearch(this.produtos, produtoBusca, new ProdutoIdComparator());

        if (index >= 0) {
            return this.produtos.get(index);
        } else {
            return null;
        }
    }

    /**
     * Altera o nome e o preço de um produto existente no estoque.
     * 
     * @param id ID do produto a ser alterado
     * @param novoNome Novo nome do produto
     * @param novoPreco Novo preço do produto
     */
    public void alterarProduto(int id, String novoNome, double novoPreco) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            System.out.println("✅ Produto alterado com sucesso: " + produto);
        } else {
            System.out.println("❌ Produto não encontrado.");
        }
    }

    /**
     * Remove uma certa quantidade de um produto no estoque.
     * 
     * @param id ID do produto
     * @param quantidade Quantidade a ser removida
     */
    public void removerQuantidade(int id, int quantidade) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }
    }

    /**
     * Lista todos os produtos atualmente presentes no estoque,
     * mostrando seus IDs, nomes, quantidades e preços.
     * 
     * @return Lista de produtos no estoque
     */
    public List<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("⚠️ Nenhum produto no estoque.");
        } else {
            System.out.println("=== Produtos no Estoque ===");
            for (Produto p : produtos) {
                System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() +
                                   ", Quantidade: " + p.getQuantidade() + ", Preço: R$ " + p.getPreco());
            }
        }
        return produtos;
    }
}
