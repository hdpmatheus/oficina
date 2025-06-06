package Entidades;

/**
 * Classe que representa um gerente na oficina mecânica.
 */
public class Gerente extends Funcionario {

    // Construtor da classe Gerente
    public Gerente(int id, int senha, String nome, String endereco, int telefone) {
        super(id, senha, "Gerente", nome, endereco, telefone);
    }
}
