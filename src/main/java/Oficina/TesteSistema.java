package oficina;

import Entidades.*;
import oficina.SistemaCentral;
import Comparator.ClienteIdComparator;
import java.util.*;

public class TesteSistema {
    public static void main(String[] args) {
        SistemaCentral central = new SistemaCentral();

        // Questão 1 - Exemplo de criação de cliente
        {
            System.out.println("=== Questão 1 ===");
            Cliente c1 = new Cliente(1, 123456789, "João", "Rua A", 99999999);
            central.getGerenciarCliente().criarCliente(c1);
        }

        // Questão 2 - Edição de cliente
        {
            System.out.println("=== Questão 2 ===");
            central.getGerenciarCliente().alterarCliente(1, "João Silva");
        }

        // Questão 3 - Exclusão de cliente
        {
            System.out.println("=== Questão 3 ===");
            boolean removido = central.getGerenciarCliente().removerCliente(1);
            System.out.println(removido ? "Cliente removido." : "Cliente não encontrado.");
        }

        // Questão 4 - Cadastro de colaborador
        {
            System.out.println("=== Questão 4 ===");
            Funcionario f1 = new Funcionario(1, 1111, "Mecânico", "Carlos", "Rua B", 88888888);
            central.getGerenciarFuncionario().criarFuncionario(f1);
        }

        // Questão 5 - Edição de colaborador
        {
            System.out.println("=== Questão 5 ===");
            central.getGerenciarFuncionario().alterarFuncionario(1, "Carlos Silva");
        }

        // Questão 6 - Visualizar ordens de serviço de um cliente
        {
            System.out.println("=== Questão 6 ===");
            central.listarAgendamentos();
        }

        // Questão 7 - Salvamento dinâmico dos dados de clientes
        {
            System.out.println("=== Questão 7 ===");
            central.getGerenciarCliente().salvarCliente();
        }

        // Questão 8 - Salvamento dinâmico das ordens de serviço
        {
            System.out.println("=== Questão 8 ===");
            central.salvarAgendamento();
        }

        // Questão 9 - Geração de extrato de serviço
        {
            System.out.println("=== Questão 9 ===");
            // Suponha que um agendamento tenha sido feito e confirmado
            central.listarAgendamentos(); // Simulação da verificação
        }

        // Questão 10 - Incremento de contador de veículo (encapsulado e protegido)
        {
            System.out.println("=== Questão 10 ===");
            central.exibirTotalDeVeiculos();
        }

        // Questão 11 - Método de classe que retorna total de veículos
        {
            System.out.println("=== Questão 11 ===");
            System.out.println("Encapsulado: " + Veiculo.getContadorVeiculosEncapsulado());
            System.out.println("Protegido: " + Veiculo.getContadorVeiculosProtegido());
        }

        // Questão 12 - Comparator em Cliente
        {
            System.out.println("=== Questão 12 ===");
            central.getGerenciarCliente().ordenarPorCpf();
        }

        // Questão 13 - Salvamento com JSON
        {
            System.out.println("=== Questão 13 ===");
            central.getGerenciarCliente().salvarCliente();
        }

        // Questão 14 - JavaDoc (não pode ser mostrado aqui, mas deve ser gerado com `javadoc`)
        {
            System.out.println("=== Questão 14 ===");
            System.out.println("JavaDoc deve ser gerado com a ferramenta javadoc.");
        }

        // Questão 15 - Iterator
        {
            System.out.println("=== Questão 15 ===");
            Iterator<Cliente> iterator = central.getGerenciarCliente().getClientes().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            System.out.println("=== Foreach ===");
            for (Cliente cliente : central.getGerenciarCliente().getClientes()) {
                System.out.println(cliente);
            }

            System.out.println("O Iterator permite controle explícito da iteração; o foreach abstrai isso.");
        }

        // Questão 16 - Comparator com sort
        {
            System.out.println("=== Questão 16 ===");
            central.getGerenciarCliente().ordenarPorCpf();
            central.getGerenciarCliente().ordenarPorId();
        }

        // Questão 17 - Método find com Iterator + binarySearch
        {
            System.out.println("=== Questão 17 ===");
            Cliente encontrado = central.getGerenciarCliente().buscarClientePorCPF(123456789);
            if (encontrado != null) {
                System.out.println("Cliente encontrado com find: " + encontrado.getNome());
            }

            Cliente busca = new Cliente(1, 0, "null", "null", 0);
            List<Cliente> lista = central.getGerenciarCliente().getClientes();
            Collections.sort(lista, new ClienteIdComparator());
            int idx = Collections.binarySearch(lista, busca, new ClienteIdComparator());

            if (idx >= 0) {
                System.out.println("Cliente encontrado com binarySearch: " + lista.get(idx).getNome());
            } else {
                System.out.println("Cliente não encontrado com binarySearch.");
            }
        }

        // Questão 18 - Atendimento de 10 clientes reais do JSON
        {
            System.out.println("=== Questão 18 ===");
            List<Cliente> clientes = central.getGerenciarCliente().getClientes();
            List<Servico> servicos = central.getGerenciarServico().getServicos(); // use seus serviços padrão

            int atendidos = 0;
            for (Cliente cliente : clientes) {
                if (atendidos >= 10) break;

                if (cliente.getVeiculos().isEmpty()) {
                    System.out.println("Cliente " + cliente.getNome() + " não possui veículo cadastrado. Pulando.");
                    continue;
                }

                Veiculo veiculo = cliente.getVeiculos().get(0); // usa o primeiro veículo
                Servico servico = servicos.get(atendidos % servicos.size()); // alterna entre os serviços
                Funcionario funcionario = central.getGerenciarFuncionario().buscarFuncionario(1); // assume um funcionário existente
                Data data = new Data(1, 1, 2025);

                central.criarPreAgendamento(cliente.getIdCliente(), servico.getIdServico(), data, funcionario.getId(), veiculo);
                central.confirmarAgendamentoComVeiculo(cliente.getIdCliente(), data, veiculo);

                // Simula geração de nota fiscal (exibe no console)
                System.out.println("Nota Fiscal - Cliente: " + cliente.getNome());
                System.out.println("Veículo: " + veiculo.getModelo() + ", Placa: " + veiculo.getPlaca());
                System.out.println("Serviço: " + servico.getTipoServico() + ", Preço: R$" + servico.getPreco());
                System.out.println("---------------------------------------");

                atendidos++;
            }

            if (atendidos == 0) {
                System.out.println("⚠️ Nenhum cliente com veículo foi encontrado no sistema.");
            } else {
                System.out.println("✅ Atendimento realizado para " + atendidos + " cliente(s).");
            }
        }
    }
}