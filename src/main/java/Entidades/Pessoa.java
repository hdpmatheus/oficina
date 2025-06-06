package Entidades;

/**
 * Classe base para representar pessoas envolvidas na oficina (clientes, funcionários, gerente).
 */
public class Pessoa {
    protected String nome;
    protected String endereco;
    protected int telefone;

    // Construtor
    public Pessoa(String nome, String endereco, int telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    // Representação textual
    @Override
    public String toString() {
        return "Pessoa{" +
               "nome='" + nome + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone=" + telefone +
               '}';
    }
}
