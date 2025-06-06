package Entidades;

/**
 * Classe que representa um funcionário da oficina mecânica.
 */

public class Funcionario extends Pessoa {
    // Atributos da classe
    private int id;
    private int senha;
    private String cargo;

    // Construtor da classe
    public Funcionario(int id, int senha, String cargo, String nome, String endereco, int telefone) {
        super(nome, endereco, telefone); // Chamada ao construtor da superclasse Pessoa
        this.id = id;
        this.senha = senha;
        this.cargo = cargo;
    }

    // Métodos de acesso (getters e setters)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", senha=" + senha + ", cargo=" + cargo + '}';
    }
}
