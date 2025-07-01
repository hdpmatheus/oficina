package Controler;

import Comparator.ClienteCpfComparator;
import Comparator.ClienteIdComparator;
import Entidades.Veiculo;
import Entidades.Cliente;
import Json.Jsoncliente;

import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

/**
 * Classe responsável pelo gerenciamento dos clientes da oficina mecânica.
 * 
 * Permite realizar operações como cadastro, busca, alteração, exclusão, ordenação
 * e listagem de clientes, além de manipular e exibir os veículos vinculados.
 * Também realiza persistência automática dos dados em formato JSON por meio da classe {@link Jsoncliente}.
 * 
 * Esta classe é utilizada amplamente no sistema da oficina para controle de atendimento e vínculo com agendamentos e vendas.
 * 
 * Funcionalidades principais:
 * <ul>
 *     <li>Cadastrar e listar clientes</li>
 *     <li>Buscar por ID ou CPF</li>
 *     <li>Ordenar clientes por ID ou CPF</li>
 *     <li>Remover e editar dados de clientes</li>
 *     <li>Buscar cliente com Iterator e Comparator</li>
 *     <li>Persistência automática ao salvar e alterar</li>
 * </ul>
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class GerenciarCliente {

    private List<Cliente> clientes;

    /**
     * Construtor que carrega automaticamente os clientes salvos no arquivo JSON.
     */
    public GerenciarCliente() {
        this.clientes = Jsoncliente.carregarClientes();
    }

    /**
     * Cria um novo cliente e o adiciona à lista.
     * A lista é salva automaticamente após a inserção.
     *
     * @param c Cliente a ser adicionado
     */
    public void criarCliente(Cliente c) {
        this.clientes.add(c);
        salvarCliente();
    }

    /**
     * Salva a lista de clientes no arquivo JSON.
     */
    public void salvarCliente() {
        Jsoncliente.salvarClientes(clientes);
    }

    /**
     * Busca um cliente pelo ID informado.
     *
     * @param id ID do cliente
     * @return Cliente encontrado ou null se não existir
     */
    public Cliente buscarCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getIdCliente() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * Altera o nome de um cliente a partir do seu ID.
     *
     * @param id ID do cliente
     * @param novoNome Novo nome a ser atribuído
     */
    public void alterarCliente(int id, String novoNome) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            cliente.setNome(novoNome);
            salvarCliente();
            System.out.println(" Cliente alterado com sucesso: " + cliente.getNome());
        } else {
            System.out.println(" Cliente não encontrado.");
        }
    }

    /**
     * Lista todos os clientes cadastrados e seus respectivos veículos.
     *
     * @return Lista de clientes
     */
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

    /**
     * Ordena os clientes da lista pelo ID (usando {@link ClienteIdComparator}).
     */
    public void ordenarPorId() {
        Collections.sort(clientes, new ClienteIdComparator());
        System.out.println("\nLista de clientes ordenada por ID:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() + ", CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
        }
    }

    /**
     * Ordena os clientes da lista pelo CPF (usando {@link ClienteCpfComparator}).
     */
    public void ordenarPorCpf() {
        Collections.sort(clientes, new ClienteCpfComparator());
        System.out.println("\nLista de clientes ordenada por CPF:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() + ", CPF: " + cliente.getCpf() + ", Nome: " + cliente.getNome());
        }
    }

    /**
     * Busca um cliente com base no CPF.
     *
     * @param cpf CPF do cliente
     * @return Cliente correspondente ou null se não encontrado
     */
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

    /**
     * Remove um cliente com base no ID.
     *
     * @param id ID do cliente a ser removido
     * @return true se o cliente foi removido com sucesso, false caso contrário
     */
    public boolean removerCliente(int id) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            clientes.remove(cliente);
            salvarCliente();
            return true;
        }
        return false;
    }

    /**
     * Encontra um cliente usando Iterator e Comparator genérico.
     *
     * @param lista Lista de clientes a ser buscada
     * @param chave Cliente com os dados chave para busca
     * @param comparator Comparador a ser usado
     * @return Cliente correspondente ou null
     */
    public Cliente findCliente(List<Cliente> lista, Cliente chave, Comparator<Cliente> comparator) {
        Iterator<Cliente> it = lista.iterator();
        while (it.hasNext()) {
            Cliente atual = it.next();
            if (comparator.compare(atual, chave) == 0) {
                return atual;
            }
        }
        return null;
    }

    /**
     * Recarrega os dados de cliente a partir do arquivo JSON.
     */
    public void carregarClienteDoArquivo() {
        this.clientes = Jsoncliente.carregarClientes();
    }

    /**
     * Encontra um cliente com determinado ID utilizando {@link Iterator} e {@link ClienteIdComparator}.
     *
     * @param id ID do cliente buscado
     * @return Cliente encontrado ou null
     */
    public Cliente findClienteByIdComIterator(int id) {
        Cliente clienteBusca = new Cliente(id, 0, "", "", 0);
        ClienteIdComparator comparator = new ClienteIdComparator();

        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente atual = iterator.next();
            if (comparator.compare(atual, clienteBusca) == 0) {
                return atual;
            }
        }
        return null;
    }
}
