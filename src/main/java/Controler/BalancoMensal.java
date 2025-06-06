package Controler;

import Entidades.Despesa;
import Entidades.Receita;
import Entidades.Venda;
import Entidades.Agendamento;
import Entidades.Servico;
import Entidades.Data;
import Entidades.Estoque;
import Entidades.Produto;
import Json.Jsonbalancomensal;
import oficina.Login;
import java.util.ArrayList;
import java.util.List;

public class BalancoMensal {

    private List<Receita> receitas;
    private List<Despesa> despesas;
    private Estoque estoque;
    private GerenciarServico gerenciarServico;
    private Login login;

    public BalancoMensal(Estoque estoque, GerenciarServico gerenciarServico, Login login) {
        this.receitas = Jsonbalancomensal.carregarReceitas();
        this.despesas = Jsonbalancomensal.carregarDespesas();
        this.estoque = estoque;
        this.gerenciarServico = gerenciarServico;
        this.login = login;
    }

    public BalancoMensal(List<Receita> receitas, List<Despesa> despesas, Login login) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }
    public List<Receita> getReceitas() {
        return receitas;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public void adicionarReceita(String descricao, double valor, Data data) {
        Receita receita = new Receita(descricao, valor, data);
        receitas.add(receita);
        salvarBalanco();
    }

    public void adicionarDespesa(Despesa despesa) {
        this.despesas.add(despesa);
        System.out.println("Despesa adicionada: " + despesa.getDescricao());
        salvarBalanco();
    }

    public void adicionarReceitaVenda(Venda venda) {
        Produto produto = estoque.buscarProduto(venda.getIdProduto());
        if (produto != null) {
            double totalVenda = venda.getQuantidade() * produto.getPreco();
            adicionarReceita("Venda de produto: " + produto.getNome(), totalVenda, venda.getDataVenda());
        } else {
            System.out.println("Produto não encontrado para a venda com ID: " + venda.getIdProduto());
        }
    }

    public void adicionarReceitaAgendamento(Agendamento agendamento) {
        Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());
        if (servico != null) {
            adicionarReceita("Agendamento de serviço: " + servico.getTipoServico(), servico.getPreco(), agendamento.getDataAgendamento());
        } else {
            System.out.println("Serviço não encontrado para o agendamento com ID: " + agendamento.getIdServico());
        }
    }

    public void removerReceitaAgendamento(Agendamento agendamento) {
        Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());
        if (servico != null) {
            Receita receitaARemover = new Receita("Agendamento de serviço: " + servico.getTipoServico(), servico.getPreco(), agendamento.getDataAgendamento());
            boolean removed = receitas.removeIf(receita -> receita.equals(receitaARemover));

            if (removed) {
                System.out.println("Receita de agendamento removida: " + receitaARemover.getDescricao());
            } else {
                System.out.println("Receita de agendamento não encontrada para remoção.");
            }
            salvarBalanco();
        } else {
            System.out.println("Serviço não encontrado para o agendamento com ID: " + agendamento.getIdServico());
        }
    }

    public void realizarBalancoMensal(int mes, int ano) {
        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Receita receita : receitas) {
            if (receita.getData().getMes() == mes && receita.getData().getAno() == ano) {
                totalReceitas += receita.getValor();
            }
        }

        for (Despesa despesa : despesas) {
            if (despesa.getDataDespesa().getMes() == mes && despesa.getDataDespesa().getAno() == ano) {
                totalDespesas += despesa.getValor();
            }
        }

        System.out.println("Balanço Mensal para " + mes + "/" + ano + ":");
        System.out.println("Total Receitas: R$ " + totalReceitas);
        System.out.println("Total Despesas: R$ " + totalDespesas);
        System.out.println("Lucro Líquido: R$ " + (totalReceitas - totalDespesas));
    }

    public void exibirBalanco() {
        System.out.println("=== RELATÓRIO DE BALANÇO ===");
        System.out.println("Receitas:");
        for (Receita receita : receitas) {
            System.out.println("- " + receita.getDescricao() + " | Valor: R$ " + receita.getValor() + " | Data: " + receita.getData());
        }

        System.out.println("Despesas:");
        for (Despesa despesa : despesas) {
            System.out.println("- " + despesa.getDescricao() + " | Valor: R$ " + despesa.getValor() + " | Data: " + despesa.getDataDespesa());
        }

        double totalReceitas = receitas.stream().mapToDouble(Receita::getValor).sum();
        double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();

        System.out.println("Total Receitas: R$ " + totalReceitas);
        System.out.println("Total Despesas: R$ " + totalDespesas);
        System.out.println("Lucro Líquido: R$ " + (totalReceitas - totalDespesas));
    }

    private void salvarBalanco() {
        Jsonbalancomensal.salvarReceitas(receitas);
        Jsonbalancomensal.salvarDespesas(despesas);
    }
}
