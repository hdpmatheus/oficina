package Controler;

import Comparator.ClienteCpfComparator;
import Comparator.ClienteIdComparator;
import Entidades.Veiculo;
import Entidades.Cliente;
import Json.Jsoncliente;
import java.util.Collections;
import java.util.List;

public class GerenciarCliente {

    private List<Cliente> clientes;

    public GerenciarCliente() {
        this.clientes = Jsoncliente.carregarCliente();  // ✅ Carrega automaticamente ao iniciar
    }

    public void criarCliente(Cliente c) {
        this.clientes.add(c);
        salvarCliente();  // ✅ Salva automaticamente ao criar
    }

    public void salvarCliente() {
        Jsoncliente.salvarCliente(clientes);
    }

    public Cliente buscarCliente(int id) {
        // ✅ Construtor atualizado: removeu sobrenome e apelido
        Cliente clienteBusca = new Cliente(id, 0, "null", "null", 0);
        int index = Collections.binarySearch(clientes, clienteBusca, new ClienteIdComparator());
        return (index >= 0) ? clientes.get(index) : null;
    }

    public void alterarCliente(int id, String novoNome) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            cliente.setNome(novoNome);
            salvarCliente();  // ✅ Salva após alteração
            System.out.println("✅ Cliente alterado com sucesso: " + cliente.getNome());
        } else {
            System.out.println("❌ Cliente não encontrado.");
        }
    }

    public List<Cliente> listarClientes() {
    if (clientes.isEmpty()) {
        System.out.println("⚠️ Nenhum cliente cadastrado.");
    } else {
        System.out.println("=== Lista de Clientes ===");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() +
                               ", Nome: " + cliente.getNome() +
                               ", Endereço: " + cliente.getEndereco() +
                               ", Telefone: " + cliente.getTelefone() +
                               ", CPF: " + cliente.getCpf());

            // ✅ Agora imprime veículos do cliente
            if (cliente.getVeiculos().isEmpty()) {
                System.out.println("    Nenhum veículo cadastrado.");
            } else {
                System.out.println("    Veiculos:");
                for (Veiculo veiculo : cliente.getVeiculos()) {
                    System.out.println("      Placa: " + veiculo.getPlaca() +
                                       ", Modelo: " + veiculo.getModelo() +
                                       ", Marca: " + veiculo.getMarca() +
                                       ", Ano: " + veiculo.getAno() +
                                       ", Tipo: " + veiculo.getTipo() +
                                       ", Cor: " + veiculo.getCor());
                }
            }
        }
    }
    return clientes;
}


    public void ordenarPorId() {
        Collections.sort(clientes, new ClienteIdComparator());
        System.out.println("\nLista de clientes ordenada por ID:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() + ", CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
        }
    }

    public void ordenarPorCpf() {
        Collections.sort(clientes, new ClienteCpfComparator());
        System.out.println("\nLista de clientes ordenada por CPF:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() + ", CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
        }
    }

    public Cliente buscarClientePorCPF(int cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        System.out.println("❌ Cliente com CPF " + cpf + " não encontrado.");
        return null;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
