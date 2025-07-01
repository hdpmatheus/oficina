package Entidades;

/**
 * Representa um produto do estoque da oficina mecânica,
 * contendo informações como ID, nome, quantidade disponível e preço.
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Produto {
    // Atributos do produto
    private int id;
    private String nome;
    private int quantidade;
    private Double preco;

    /**
     * Construtor que cria um produto com ID, nome, quantidade e preço especificados.
     * 
     * @param id Identificador único do produto
     * @param nome Nome do produto
     * @param quantidade Quantidade disponível em estoque
     * @param preco Preço unitário do produto
     */
    public Produto(int id, String nome, int quantidade, Double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    /**
     * Retorna o ID do produto.
     * 
     * @return ID do produto
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o ID do produto (método adicional).
     * 
     * @return ID do produto
     */
    public int getIdProduto() {
        return this.id;
    }

    /**
     * Define o ID do produto.
     * 
     * @param id Novo ID do produto
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return Nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * 
     * @param nome Novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a quantidade disponível do produto em estoque.
     * 
     * @return Quantidade disponível
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade disponível do produto em estoque.
     * 
     * @param quantidade Nova quantidade disponível
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o preço unitário do produto.
     * 
     * @return Preço do produto
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * Define o preço unitário do produto.
     * 
     * @param preco Novo preço do produto
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * Retorna uma representação textual do produto.
     * 
     * @return String com informações do produto
     */
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
