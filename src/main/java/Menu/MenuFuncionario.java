package Menu;

import oficina.SistemaCentral;
import java.util.Scanner;
import Entidades.*;
import java.util.List;

    /**
     * Classe responsável por exibir e controlar o menu de operações disponíveis para funcionários da oficina.
     * 
     * Permite o acesso a funcionalidades como:
     * <ul>
     *     <li>Gerenciar agendamentos e avançar status</li>
     *     <li>Cadastrar clientes e veículos</li>
     *     <li>Agendar serviços</li>
     *     <li>Consultar dados de clientes</li>
     *     <li>Reservar e liberar elevadores</li>
     *     <li>Excluir clientes</li>
     * </ul>
     * 
     * Esta interface de menu é acessada após login como funcionário e interage diretamente com o {@link SistemaCentral}.
     * 
     * @author 
     * Matheus Henrique de Paula <br>
     * Felipe Alcântara Guimarães Veloso
     */
    public class MenuFuncionario {

    /**
     * Exibe o menu interativo de funcionário, permitindo executar as operações vinculadas
     * ao fluxo de trabalho da oficina. A execução permanece em loop até que a opção de saída seja escolhida.
     *
     * @param central Instância do sistema central da oficina
     * @param scanner Objeto Scanner para leitura de entrada do usuário
     */

    public static void menuFuncionario(SistemaCentral central, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== MENU FUNCIONARIO ===");
            System.out.println("1. Ver servicos agendados");
            System.out.println("2. Confirmar agendamento (com veículo e elevador)");
            System.out.println("3. Avancar status de agendamento");
            System.out.println("4. Adicionar cliente");
            System.out.println("5. Adicionar veiculo a cliente");
            System.out.println("6. Agendar servico");
            System.out.println("7. Consultar cliente/veiculo");
            System.out.println("8. Reservar Elevador");
            System.out.println("9. Liberar Elevador");
            System.out.println("10. Listar Elevadores");
            System.out.println("11. Excluir cliente");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                    // Cada case implementa uma funcionalidade conforme descrito no menu
                    // Os blocos do switch seguem organizados por área funcional:
                    // - Agendamentos (1–3)
                    // - Cliente e Veículo (4–7, 11)
                    // - Elevadores (8–10)
                    // - Sair (0)
                case 1 -> central.listarAgendamentos();
                case 2 -> {
                    System.out.print("ID Cliente: ");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();

                    Cliente cliente = central.getGerenciarCliente().buscarCliente(idCliente);
                    if (cliente == null || cliente.getVeiculos().isEmpty()) {
                        System.out.println("Cliente não encontrado ou não possui veículos.");
                        break;
                    }

                    System.out.println("Selecione o veículo para associar ao agendamento:");
                    List<Veiculo> veiculos = cliente.getVeiculos();
                    for (int i = 0; i < veiculos.size(); i++) {
                        System.out.println((i + 1) + " - " + veiculos.get(i));
                    }

                    System.out.print("Escolha (número): ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha < 1 || escolha > veiculos.size()) {
                        System.out.println("Escolha inválida. Tente novamente");
                        break;
                    }

                    Veiculo veiculoSelecionado = veiculos.get(escolha - 1);

                    java.time.LocalDate hoje = java.time.LocalDate.now();
                    Data dataConfirmacao = new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());

                    central.confirmarAgendamentoComVeiculo(idCliente, dataConfirmacao, veiculoSelecionado);
                }

               case 3 -> {
                    System.out.print("ID do cliente do agendamento: ");
                    int idCliente = scanner.nextInt();

                    Agendamento agendamento = central.buscarAgendamentoPorIdCliente(idCliente);
                    if (agendamento == null) {
                        System.out.println("Agendamento não encontrado.");
                        break;
                    }

                    String statusAtual = agendamento.getStatus();
                    if (statusAtual.equals("Agendado")) {
                        System.out.println("⚠️ Este agendamento ainda não foi confirmado. Use a opção 2 para confirmar o agendamento com veículo e elevador.");
                        break;
                    }

                    central.avancarStatusAgendamento(idCliente);
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("CPF: ");
                    int cpf = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    int telefone = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Endereco: ");
                    String endereco = scanner.nextLine();

                    Cliente novoCliente = new Cliente(id, cpf, nome, endereco, telefone);
                    central.getGerenciarCliente().criarCliente(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                case 5 -> {
                    System.out.print("ID Cliente: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = central.getGerenciarCliente().buscarCliente(idCliente);
                    if (cliente != null) {
                        System.out.print("Placa: ");
                        String placa = scanner.next();
                        scanner.nextLine();
                        System.out.print("Modelo: ");
                        String modelo = scanner.nextLine();
                        System.out.print("Ano: ");
                        int ano = scanner.nextInt();
                        System.out.print("Marca: ");
                        String marca = scanner.next();
                        System.out.print("Tipo: ");
                        String tipo = scanner.next();
                        System.out.print("Cor: ");
                        String cor = scanner.next();

                        Veiculo veiculo = new Veiculo(placa, modelo, ano, marca, tipo, cor, cliente);
                        cliente.adicionarVeiculo(veiculo);
                        central.getGerenciarCliente().salvarCliente();
                        System.out.println("Veiculo adicionado com sucesso!");
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                }
                case 6 -> {
                    System.out.print("ID do cliente: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = central.getGerenciarCliente().buscarCliente(idCliente);
                    if (cliente == null || cliente.getVeiculos().isEmpty()) {
                        System.out.println("Cliente não encontrado ou sem veículos.");
                        return;
                    }

                    List<Veiculo> veiculos = cliente.getVeiculos();
                    System.out.println("Selecione o veículo para o agendamento:");
                    for (int i = 0; i < veiculos.size(); i++) {
                        System.out.println((i + 1) + " - " + veiculos.get(i));
                    }
                    System.out.print("Escolha (número): ");
                    int escolha = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (escolha < 0 || escolha >= veiculos.size()) {
                        System.out.println("Veículo inválido.");
                        return;
                    }

                    Veiculo veiculoSelecionado = veiculos.get(escolha);
                    System.out.print("ID do servico: ");
                    int idServico = scanner.nextInt();
                    System.out.print("ID do funcionario: ");
                    int idFuncionario = scanner.nextInt();
                    System.out.print("Dia: ");
                    int dia = scanner.nextInt();
                    System.out.print("Mes: ");
                    int mes = scanner.nextInt();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    Data data = new Data(dia, mes, ano);
                    central.criarPreAgendamento(idCliente, idServico, data, idFuncionario, veiculoSelecionado);
                }
                case 7 -> {
                    System.out.print("ID Cliente: ");
                    int id = scanner.nextInt();
                    Cliente cliente = central.getGerenciarCliente().buscarCliente(id);
                    if (cliente != null) {
                        System.out.println("ID: " + cliente.getIdCliente());
                        System.out.println("Nome: " + cliente.getNome());
                        System.out.println("Endereco: " + cliente.getEndereco());
                        System.out.println("Telefone: " + cliente.getTelefone());
                        System.out.println("CPF: " + cliente.getCpf());
                        if (cliente.getVeiculos().isEmpty()) {
                            System.out.println("Nenhum veiculo cadastrado.");
                        } else {
                            for (Veiculo v : cliente.getVeiculos()) {
                                System.out.println("Placa: " + v.getPlaca() + ", Modelo: " + v.getModelo() + ", Ano: " + v.getAno() + ", Marca: " + v.getMarca() + ", Tipo: " + v.getTipo() + ", Cor: " + v.getCor());
                            }
                        }
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                }
                case 8 -> {
                    System.out.print("ID do cliente: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = central.getGerenciarCliente().buscarCliente(idCliente);

                    if (cliente == null || cliente.getVeiculos().isEmpty()) {
                        System.out.println("Cliente não encontrado ou sem veículos.");
                        break;
                    }

                    List<Veiculo> veiculos = cliente.getVeiculos();
                    System.out.println("Selecione o veículo para usar o elevador:");
                    for (int i = 0; i < veiculos.size(); i++) {
                        System.out.println((i + 1) + " - " + veiculos.get(i));
                    }
                    System.out.print("Escolha (número): ");
                    int escolha = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (escolha < 0 || escolha >= veiculos.size()) {
                        System.out.println("Veículo inválido.");
                        break;
                    }

                    Veiculo veiculoSelecionado = veiculos.get(escolha);

                    System.out.print("Digite o ID do elevador para reservar: ");
                    int idElevador = scanner.nextInt();
                    boolean reservado = central.reservarElevador(idElevador, veiculoSelecionado);
                    if (reservado) {
                        System.out.println("Elevador reservado com sucesso.");
                        // Você pode, opcionalmente, salvar essa associação elevador-veiculo se desejar.
                    } else {
                        System.out.println("Elevador já está ocupado ou não encontrado.");
                    }
                }

                case 9 -> {
                    System.out.print("Digite o ID do elevador para liberar: ");
                    int id = scanner.nextInt();
                    central.liberarElevador(id);
                    System.out.println("Elevador liberado.");
                }
                case 10 -> {
                    central.listarElevadores();
                }
                case 11 -> {
                    List<Cliente> clientes = central.getGerenciarCliente().getClientes();

                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    System.out.println("=== LISTA DE CLIENTES ===");
                    for (Cliente c : clientes) {
                        System.out.println("ID: " + c.getIdCliente());
                        System.out.println("Nome: " + c.getNome());
                        System.out.println("CPF: " + c.getCpf());
                        System.out.println("Telefone: " + c.getTelefone());
                        System.out.println("Endereco: " + c.getEndereco());
                        if (c.getVeiculos().isEmpty()) {
                            System.out.println("Veículos: Nenhum cadastrado.");
                        } else {
                            for (Veiculo v : c.getVeiculos()) {
                                System.out.println("  > Placa: " + v.getPlaca() + " | Modelo: " + v.getModelo() +
                                        " | Marca: " + v.getMarca() + " | Cor: " + v.getCor() + " | Ano: " + v.getAno());
                            }
                        }
                        System.out.println("---------------------------");
                    }

                    System.out.print("Digite o ID do cliente que deseja excluir (ou 0 para voltar): ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();

                    if (idExcluir == 0) {
                        System.out.println("Operação cancelada. Retornando ao menu...");
                        break;
                    }


                    Cliente cliente = central.getGerenciarCliente().buscarCliente(idExcluir);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado. Verifique o ID informado.");
                    } else {
                        System.out.print("Tem certeza que deseja excluir o cliente " + cliente.getNome() + "? (s/n): ");
                        String confirmacao = scanner.next();
                        if (confirmacao.equalsIgnoreCase("s")) {
                            boolean sucesso = central.getGerenciarCliente().removerCliente(idExcluir);
                            if (sucesso) {
                                System.out.println("Cliente excluído com sucesso!");
                            } else {
                                System.out.println("Erro ao excluir cliente.");
                            }
                        } else {
                            System.out.println("Exclusão cancelada.");
                        }
                    }
                }
                case 0 -> System.out.println("Saindo do menu funcionario...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }
}
