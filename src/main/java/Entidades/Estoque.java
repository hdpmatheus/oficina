package Entidades;

import Comparator.ProdutoIdComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Json.Jsonproduto;
/**
 * Estoque de produtos da oficina mecânica.
 */
public class Estoque {

    private List<Produto> produtos;

    // Construtor
    public Estoque() {
        this.produtos = new ArrayList<>();
    }
    public void carregarProdutosDoArquivo() {
        this.produtos = Jsonproduto.carregarProdutos();
}


    // Método para adicionar novo produto ao estoque
    public void criarProduto(Produto p) {
        this.produtos.add(p);
    }

    // Método para buscar um produto pelo ID usando binarySearch
    public Produto buscarProduto(int id) {
        Produto produtoBusca = new Produto(id, null, 0, 0.0);
        int index = Collections.binarySearch(this.produtos, produtoBusca, new ProdutoIdComparator());

        if (index >= 0) {
            return this.produtos.get(index);
        } else {
            return null;
        }
    }

    // Método para alterar os dados de um produto
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

    // Método para remover quantidade de um produto
    public void removerQuantidade(int id, int quantidade) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }
    }

    // ✅ Método para listar todos os produtos
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
