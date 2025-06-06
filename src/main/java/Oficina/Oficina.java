package Oficina;

import Adapter.PagamentoCartaoCredito;
import Adapter.PagamentoPix;
import Adapter.ProcessadorPagamento;
import Adapter.SistemaExterno;
import Entidades.*;
import Json.Jsonbalancomensal;
import Json.Jsonelevador;
import oficina.SistemaCentral;
import oficina.Login;

import java.util.Scanner;
import java.util.List;


public class Oficina {

    public static void main(String[] args) {
        SistemaCentral central = new SistemaCentral();
        SistemaExterno sistemaExterno = new SistemaExterno();
        ProcessadorPagamento cartaoCredito = new PagamentoCartaoCredito(sistemaExterno);
        ProcessadorPagamento pix = new PagamentoPix(sistemaExterno);

        Funcionario funcionario1 = new Funcionario(1, 12345, "Mecanico", "Boquita", "Campo Tijuco, 45", 3890101);
        Gerente gerente1 = new Gerente(1, 5513, "Felps", "Rua Antonio Olinto, 60", 3190101);
        central.getGerenciarFuncionario().criarFuncionario(funcionario1);
        central.getGerenciarGerente().criarGerente(gerente1);

        Login loginSystem = new Login(central.getGerenciarFuncionario(), central.getGerenciarGerente());
        Scanner scanner = new Scanner(System.in);
        String tipoUsuarioLogado = null;

        System.out.println("Bem-vindo ao sistema da oficina! Por favor, faca o login.");
        while (tipoUsuarioLogado == null) {
            System.out.print("Digite o tipo de usuario (funcionario/gerente): ");
            String tipoUsuario = scanner.next().toLowerCase();

            if (!tipoUsuario.equals("funcionario") && !tipoUsuario.equals("gerente")) {
                System.out.println("Tipo de usuario invalido. Tente novamente.");
                continue;
            }

            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();

            System.out.print("Digite a senha: ");
            int senha = scanner.nextInt();

            tipoUsuarioLogado = loginSystem.realizarLogin(id, senha, tipoUsuario);
        }

        System.out.println("Acesso concedido!");

        if (tipoUsuarioLogado.equals("gerente")) {
            menuGerente(central, scanner);
        } else if (tipoUsuarioLogado.equals("funcionario")) {
            menuFuncionario(central, scanner);
        }

        central.getGerenciarCliente().salvarCliente();
        central.getGerenciarGerente().salvarGerente();
        central.getGerenciarFuncionario().salvarFuncionario();
        central.salvarVenda();
        central.salvarAgendamento();
        Jsonbalancomensal.salvarBalanco(central.getBalancomensal());
        Jsonelevador.salvarElevadores(SistemaCentral.getElevadores());


        System.out.println("Sistema encerrado com sucesso.");
    }

    private static void menuGerente(SistemaCentral central, Scanner scanner) {
    // Primeiro usa o menu de funcionário
    menuFuncionario(central, scanner);

    // Depois vê o menu exclusivo de gerente
    int opcao;
    do {
        System.out.println("\n=== MENU ADICIONAL DO GERENTE ===");
        System.out.println("11. Ver relatorio financeiro");
        System.out.println("12. Adicionar despesa");
        System.out.println("13. Listar clientes");
        System.out.println("14. Total de veículos cadastrados");
        System.out.println("0. Sair");

        System.out.print("Escolha uma opcao: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 11 -> central.getBalancomensal().exibirBalanco();
            case 12 -> {
                System.out.print("Descricao: ");
                String desc = scanner.nextLine();
                System.out.print("Valor: ");
                double valor = scanner.nextDouble();
                java.time.LocalDate hoje = java.time.LocalDate.now();
                Data data = new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
                central.getBalancomensal().adicionarDespesa(new Despesa(desc, valor, "Gerente", data));
                System.out.println("Despesa adicionada.");
            }
            case 13 -> central.getGerenciarCliente().listarClientes();
            case 14 -> central.exibirTotalDeVeiculos();
            case 0 -> System.out.println("Saindo do menu gerente...");
            default -> System.out.println("Opcao invalida.");
        }
    } while (opcao != 0);
}



    private static void menuFuncionario(SistemaCentral central, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== MENU FUNCIONARIO ===");
            System.out.println("1. Ver servicos agendados");
            System.out.println("2. Confirmar servico realizado");
            System.out.println("3. Avancar status de agendamento");
            System.out.println("4. Adicionar cliente");
            System.out.println("5. Adicionar veiculo a cliente");
            System.out.println("6. Agendar servico");
            System.out.println("7. Consultar cliente/veiculo");
            System.out.println("8. Reservar Elevador");
            System.out.println("9. Liberar Elevador");
            System.out.println("10. Listar Elevadores");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
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
                        System.out.println("Escolha inválida.");
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
                    boolean reservado = central.reservarElevador(idElevador);

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
                case 0 -> System.out.println("Saindo do menu funcionario...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }
}
