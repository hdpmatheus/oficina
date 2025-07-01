package Entidades;

/**
 * Classe base que representa uma pessoa envolvida na oficina mecânica,
 * como clientes, funcionários e gerente.
 * <p>
 * Contém atributos básicos como nome, endereço e telefone, além dos métodos
 * getters e setters correspondentes.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Pessoa {
    protected String nome;
    protected String endereco;
    protected int telefone;

    /**
     * Construtor para criar uma pessoa com nome, endereço e telefone.
     * 
     * @param nome Nome da pessoa
     * @param endereco Endereço da pessoa
     * @param telefone Telefone da pessoa
     */
    public Pessoa(String nome, String endereco, int telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    /**
     * Retorna o nome da pessoa.
     * 
     * @return Nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     * 
     * @param nome Novo nome da pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o endereço da pessoa.
     * 
     * @return Endereço da pessoa
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da pessoa.
     * 
     * @param endereco Novo endereço da pessoa
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna o telefone da pessoa.
     * 
     * @return Telefone da pessoa
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da pessoa.
     * 
     * @param telefone Novo telefone da pessoa
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna uma representação textual da pessoa.
     * 
     * @return String contendo os dados da pessoa
     */
    @Override
    public String toString() {
        return "Pessoa{" +
               "nome='" + nome + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone=" + telefone +
               '}';
    }
}
