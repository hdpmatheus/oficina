package Entidades;

import oficina.SistemaCentral;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private int idCliente;
    private int cpf;
    private List<Veiculo> veiculos; // ✅ Lista de veículos

    // ✅ Construtor atualizado: removeu sobrenome e apelido
    public Cliente(int idCliente, int cpf, String nome, String endereco, int telefone) {
        super(nome, endereco, telefone);
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.veiculos = new ArrayList<>(); // ✅ Inicializando a lista de veículos
        SistemaCentral.incrementarContadorClientes();
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    // ✅ Métodos de gestão de veículos
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void removerVeiculoPorPlaca(String placa) {
        veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }
    

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
