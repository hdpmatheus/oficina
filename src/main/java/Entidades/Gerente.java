package Entidades;

/**
 * Representa um gerente na oficina mecânica.
 * <p>
 * A classe Gerente estende a classe Funcionario, definindo o cargo como "Gerente".
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Gerente extends Funcionario {

    /**
     * Construtor para criar um gerente com ID, senha, nome, endereço e telefone.
     * O cargo é automaticamente definido como "Gerente".
     * 
     * @param id Identificador único do gerente
     * @param senha Senha de acesso do gerente
     * @param nome Nome do gerente
     * @param endereco Endereço do gerente
     * @param telefone Telefone do gerente
     */
    public Gerente(int id, int senha, String nome, String endereco, int telefone) {
        super(id, senha, "Gerente", nome, endereco, telefone);
    }
}
