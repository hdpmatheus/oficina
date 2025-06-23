package Oficina;

import Adapter.PagamentoCartaoCredito;
import Adapter.PagamentoPix;
import Adapter.ProcessadorPagamento;
import Adapter.SistemaExterno;
import Entidades.*;
import Json.Jsonbalancomensal;
import oficina.SistemaCentral;
import oficina.Login;
import Menu.MenuFuncionario;
import Menu.MenuGerente;  

import java.util.Scanner;

public class Oficina {

    public static void main(String[] args) {
        SistemaCentral central = new SistemaCentral();
        SistemaExterno sistemaExterno = new SistemaExterno();
        central.carregarEstoquePadrao();
        ProcessadorPagamento cartaoCredito = new PagamentoCartaoCredito(sistemaExterno);
        ProcessadorPagamento pix = new PagamentoPix(sistemaExterno);

        Funcionario funcionario1 = new Funcionario(1, 12345, "Mecanico", "Boquita", "Campo Tijuco, 45", 3890101);
        Gerente gerente1 = new Gerente(1, 5513, "Felps", "Rua Antonio Olinto, 60", 3190101);
        central.getGerenciarFuncionario().criarFuncionario(funcionario1);
        central.getGerenciarGerente().criarGerente(gerente1);

        Login loginSystem = new Login(central.getGerenciarFuncionario(), central.getGerenciarGerente());
        Scanner scanner = new Scanner(System.in);
        String tipoUsuarioLogado = null;

        System.out.println("Bem-vindo ao sistema da oficina! Por favor, faça o login.");
        while (tipoUsuarioLogado == null) {
            System.out.print("Digite o tipo de usuario (funcionario/gerente): ");
            String tipoUsuario = scanner.next().toLowerCase();

            if (!tipoUsuario.equals("funcionario") && !tipoUsuario.equals("gerente")) {
                System.out.println("❌ Tipo de usuário inválido. Tente novamente.");
                continue;
            }

            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();

            System.out.print("Digite a senha: ");
            int senha = scanner.nextInt();

            tipoUsuarioLogado = loginSystem.realizarLogin(id, senha, tipoUsuario);
        }

        System.out.println("✅ Acesso concedido!");

        if (tipoUsuarioLogado.equals("gerente")) {
            MenuGerente.menuGerente(central, scanner); 
        } else if (tipoUsuarioLogado.equals("funcionario")) {
            MenuFuncionario.menuFuncionario(central, scanner);
        }

        // Salvar todos os dados ao encerrar
        central.getGerenciarCliente().salvarCliente();
        central.getGerenciarGerente().salvarGerente();
        central.getGerenciarFuncionario().salvarFuncionario();
        central.salvarVenda();
        central.salvarAgendamento();
        central.salvarElevadores();
        central.carregarEstoquePadrao();
        Jsonbalancomensal.salvarBalanco(central.getBalancomensal());

        System.out.println("Sistema encerrado com sucesso.");
        scanner.close();
    }
}
