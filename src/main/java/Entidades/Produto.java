package Entidades;

/**
 * Representa um produto do estoque da oficina mecânica
 */
public class Produto {
    // Atributos do produto
    private int id;
    private String nome;
    private int quantidade;
    private Double preco;

    // Construtor
    public Produto(int id, String nome, int quantidade, Double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Métodos get e set
    public int getId() {
        return id;
    }

    // ✅ Método adicional solicitado
    public int getIdProduto() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
