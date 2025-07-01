package Menu;

import oficina.SistemaCentral;
import Menu.MenuFuncionario;
import java.util.Scanner;
import Entidades.*;

/**
 * Classe responsável por exibir o menu do gerente no sistema da oficina mecânica.
 * Permite acesso às funcionalidades adicionais como controle de estoque, balanço financeiro,
 * gestão de colaboradores e visualização de dados estatísticos.
 * 
 * @author Felipe Alcantara Guimaraes Veloso
 * @author Matheus Henrique de Paula
 */
public class MenuGerente {

    /**
     * Exibe o menu do gerente com opções adicionais.
     * Esse menu é chamado após o menu do funcionário para reutilizar funcionalidades.
     * 
     * @param central Instância do sistema central da oficina.
     * @param scanner Scanner para entrada de dados.
     */
    public static void menuGerente(SistemaCentral central, Scanner scanner) {
        MenuFuncionario.menuFuncionario(central, scanner);
        int opcao;
        do {
            System.out.println("\n=== MENU ADICIONAL DO GERENTE ===");
            System.out.println("12. Gerenciar Estoque");
            System.out.println("13. Ver relatorio financeiro");
            System.out.println("14. Adicionar despesa");
            System.out.println("15. Listar clientes");
            System.out.println("16. Total de veículos cadastrados");
            System.out.println("17. Alterar dados de colaborador");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 12 -> Menu.MenuEstoque.menu(central.getEstoque());
                case 13 -> central.getBalancomensal().exibirBalanco();
                case 14 -> {
                    System.out.print("Descricao: ");
                    String desc = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    java.time.LocalDate hoje = java.time.LocalDate.now();
                    Data data = new Data(hoje.getDayOfMonth(), hoje.getMonthValue(), hoje.getYear());
                    central.getBalancomensal().adicionarDespesa(new Despesa(desc, valor, "Gerente", data));
                    System.out.println("Despesa adicionada.");
                }
                case 15 -> central.getGerenciarCliente().listarClientes();
                case 16 -> central.exibirTotalDeVeiculos();
                case 17 -> {
                    System.out.print("Digite o ID do colaborador: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Funcionario funcionario = central.getGerenciarFuncionario().buscarFuncionario(id);
                    if (funcionario == null) {
                        System.out.println("Colaborador não encontrado.");
                    }

                    System.out.println("Colaborador atual: " + funcionario);

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    System.out.print("Novo telefone: ");
                    int novoTelefone = scanner.nextInt();
                    scanner.nextLine();

                    funcionario.setNome(novoNome);
                    funcionario.setEndereco(novoEndereco);
                    funcionario.setTelefone(novoTelefone);

                    central.getGerenciarFuncionario().salvarFuncionario();
                    System.out.println("Dados do colaborador atualizados com sucesso!");
                }

                case 0 -> System.out.println("Saindo do menu gerente...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }
}
