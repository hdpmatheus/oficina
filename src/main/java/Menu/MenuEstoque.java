package Menu;

import Entidades.Estoque;
import Entidades.Produto;

import java.util.Scanner;

/**
 * Classe responsável por exibir e controlar o menu de gerenciamento do estoque.
 * 
 * Permite ao usuário listar os produtos disponíveis, repor a quantidade de um produto,
 * e alterar nome e preço de produtos no sistema da oficina.
 * 
 * Este menu é acessível normalmente por usuários com permissão de gerente.
 * 
 * As operações realizadas interagem diretamente com o objeto {@link Estoque}.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class MenuEstoque {

    /**
     * Exibe o menu de gerenciamento de estoque e executa as operações conforme a escolha do usuário.
     *
     * @param estoque O estoque que será manipulado pelo menu
     */
    public static void menu(Estoque estoque) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== Menu de Estoque ===");
            System.out.println("1 - Listar produtos");
            System.out.println("2 - Repor produto (adicionar quantidade)");
            System.out.println("3 - Alterar nome e preço de produto");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    estoque.listarProdutos();
                    break;
                case 2:
                    System.out.print("ID do produto: ");
                    int idRepor = scanner.nextInt();
                    System.out.print("Quantidade a adicionar: ");
                    int qtd = scanner.nextInt();
                    Produto pRepor = estoque.buscarProduto(idRepor);
                    if (pRepor != null) {
                        pRepor.setQuantidade(pRepor.getQuantidade() + qtd);
                        System.out.println("✅ Quantidade atualizada.");
                    } else {
                        System.out.println("❌ Produto não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("ID do produto: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo preço: ");
                    double novoPreco = scanner.nextDouble();
                    estoque.alterarProduto(idAlterar, novoNome, novoPreco);
                    break;
                case 0:
                    System.out.println("Retornando ao menu anterior...");
                    break;
                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 0);
    }
}
