package Menu;

import Entidades.Estoque;
import Entidades.Produto;

import java.util.Scanner;

public class MenuEstoque {

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
