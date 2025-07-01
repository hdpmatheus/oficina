package Entidades;

import oficina.SistemaCentral;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente da oficina mecânica, que é uma pessoa que pode possuir
 * múltiplos veículos cadastrados.
 * <p>
 * Herda os atributos básicos de Pessoa e adiciona informações específicas como
 * ID do cliente, CPF e lista de veículos associados.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Cliente extends Pessoa {
    private int idCliente;
    private int cpf;
    private List<Veiculo> veiculos; // Lista de veículos do cliente

    /**
     * Construtor para criar um cliente com ID, CPF, nome, endereço e telefone.
     * Inicializa a lista de veículos vazia e incrementa o contador de clientes no sistema.
     * 
     * @param idCliente Identificador único do cliente
     * @param cpf CPF do cliente
     * @param nome Nome do cliente
     * @param endereco Endereço do cliente
     * @param telefone Telefone do cliente
     */
    public Cliente(int idCliente, int cpf, String nome, String endereco, int telefone) {
        super(nome, endereco, telefone);
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.veiculos = new ArrayList<>();
        SistemaCentral.incrementarContadorClientes();
    }

    /**
     * Retorna o ID do cliente.
     * 
     * @return ID do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente.
     * 
     * @param idCliente Novo ID do cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna o CPF do cliente.
     * 
     * @return CPF do cliente
     */
    public int getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     * 
     * @param cpf Novo CPF do cliente
     */
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna a lista de veículos associados ao cliente.
     * 
     * @return Lista de veículos do cliente
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     * 
     * @param veiculo Veículo a ser adicionado
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    /**
     * Remove um veículo da lista do cliente com base na placa fornecida.
     * A remoção é feita ignorando diferenças entre maiúsculas e minúsculas.
     * 
     * @param placa Placa do veículo a ser removido
     */
    public void removerVeiculoPorPlaca(String placa) {
        veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    /**
     * Retorna uma representação em string do cliente, incluindo quantidade de veículos cadastrados.
     * 
     * @return String com informações do cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", cpf=" + cpf +
                ", nome=" + getNome() +
                ", endereco=" + getEndereco() +
                ", telefone=" + getTelefone() +
                ", veiculos=" + veiculos.size() + " veículos cadastrados" +
                '}';
    }
}
