package Entidades;

/**
 * Representa um funcionário da oficina mecânica, contendo informações como
 * ID, senha de acesso e cargo, além dos dados básicos herdados da classe Pessoa.
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Funcionario extends Pessoa {
    // Atributos da classe
    private int id;
    private int senha;
    private String cargo;

    /**
     * Construtor para criar um funcionário com ID, senha, cargo, nome, endereço e telefone.
     * 
     * @param id Identificador único do funcionário
     * @param senha Senha de acesso do funcionário
     * @param cargo Cargo ou função do funcionário na oficina
     * @param nome Nome do funcionário
     * @param endereco Endereço do funcionário
     * @param telefone Telefone do funcionário
     */
    public Funcionario(int id, int senha, String cargo, String nome, String endereco, int telefone) {
        super(nome, endereco, telefone); // Chamada ao construtor da superclasse Pessoa
        this.id = id;
        this.senha = senha;
        this.cargo = cargo;
    }

    /**
     * Retorna o ID do funcionário.
     * 
     * @return ID do funcionário
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do funcionário.
     * 
     * @param id Novo ID do funcionário
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a senha do funcionário.
     * 
     * @return Senha de acesso do funcionário
     */
    public int getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     * 
     * @param senha Nova senha de acesso
     */
    public void setSenha(int senha) {
        this.senha = senha;
    }

    /**
     * Retorna o cargo do funcionário.
     * 
     * @return Cargo do funcionário
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     * 
     * @param cargo Novo cargo ou função
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Retorna uma representação em string do funcionário.
     * 
     * @return String com informações do funcionário
     */
    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", senha=" + senha + ", cargo=" + cargo + '}';
    }
}
