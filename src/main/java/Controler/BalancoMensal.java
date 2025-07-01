package Controler;

import Entidades.*;
import Json.Jsonbalancomensal;
import oficina.Login;

import java.util.List;

/**
 * Classe responsável por controlar e registrar o balanço financeiro mensal da oficina.
 * 
 * Gerencia listas de {@link Receita} e {@link Despesa}, associadas a serviços, vendas e agendamentos.
 * Possui integração com estoque, controle de serviços e o sistema de login, além de persistência via JSON.
 * 
 * Funcionalidades principais:
 * <ul>
 *     <li>Adicionar receitas e despesas manuais ou automáticas</li>
 *     <li>Remover receitas vinculadas a agendamentos cancelados</li>
 *     <li>Gerar relatórios de balanço mensal</li>
 *     <li>Persistir os dados financeiros com Gson</li>
 * </ul>
 * 
 * Os dados são carregados automaticamente no construtor através da classe {@link Jsonbalancomensal}.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class BalancoMensal {

    private List<Receita> receitas;
    private List<Despesa> despesas;
    private Estoque estoque;
    private GerenciarServico gerenciarServico;
    private Login login;

    /**
     * Construtor principal que carrega receitas e despesas de arquivos JSON.
     *
     * @param estoque Estoque da oficina
     * @param gerenciarServico Controlador de serviços
     * @param login Sistema de login para controle de acesso
     */
    public BalancoMensal(Estoque estoque, GerenciarServico gerenciarServico, Login login) {
        this.receitas = Jsonbalancomensal.carregarReceitas();
        this.despesas = Jsonbalancomensal.carregarDespesas();
        this.estoque = estoque;
        this.gerenciarServico = gerenciarServico;
        this.login = login;
    }

    /**
     * Construtor alternativo para uso com listas de receitas e despesas prontas.
     *
     * @param receitas Lista de receitas
     * @param despesas Lista de despesas
     * @param login Sistema de login
     */
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

    /**
     * Adiciona uma nova receita manualmente ao balanço.
     *
     * @param descricao Descrição da receita
     * @param valor Valor da receita
     * @param data Data da receita
     */
    public void adicionarReceita(String descricao, double valor, Data data) {
        Receita receita = new Receita(descricao, valor, data);
        receitas.add(receita);
        salvarBalanco();
    }

    /**
     * Adiciona uma despesa ao balanço.
     *
     * @param despesa Objeto despesa a ser adicionado
     */
    public void adicionarDespesa(Despesa despesa) {
        this.despesas.add(despesa);
        System.out.println("Despesa adicionada: " + despesa.getDescricao());
        salvarBalanco();
    }

    /**
     * Gera uma receita automática com base em uma venda de produtos.
     *
     * @param venda Venda realizada
     */
    public void adicionarReceitaVenda(Venda venda) {
        if (venda.getProdutos().isEmpty()) {
            System.out.println("⚠️ Nenhum item registrado na venda para " + venda.getCliente().getNome());
            return;
        }

        double total = 0.0;
        StringBuilder descricao = new StringBuilder("Venda para " + venda.getCliente().getNome() + ": ");

        for (Produto p : venda.getProdutos()) {
            total += p.getPreco();
            descricao.append(p.getNome()).append(" (R$").append(p.getPreco()).append("), ");
        }

        if (descricao.lastIndexOf(",") == descricao.length() - 2) {
            descricao.delete(descricao.length() - 2, descricao.length());
        }

        adicionarReceita(descricao.toString(), total, venda.getDataVenda());
    }

    /**
     * Adiciona uma receita com base em um agendamento concluído.
     *
     * @param agendamento Agendamento finalizado
     */
    public void adicionarReceitaAgendamento(Agendamento agendamento) {
        Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());
        if (servico != null) {
            adicionarReceita("Agendamento de serviço: " + servico.getTipoServico(), servico.getPreco(), agendamento.getDataAgendamento());
        } else {
            System.out.println("Serviço não encontrado para o agendamento com ID: " + agendamento.getIdServico());
        }
    }

    /**
     * Remove a receita associada a um agendamento cancelado.
     *
     * @param agendamento Agendamento a ser desconsiderado
     */
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

    /**
     * Calcula e exibe o balanço mensal de um determinado mês e ano.
     *
     * @param mes Mês desejado (1 a 12)
     * @param ano Ano desejado
     */
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

    /**
     * Exibe um relatório completo com todas as receitas e despesas registradas.
     */
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

    /**
     * Salva as listas de receitas e despesas nos respectivos arquivos JSON.
     * Este método é chamado automaticamente após alterações.
     */
    private void salvarBalanco() {
        Jsonbalancomensal.salvarReceitas(receitas);
        Jsonbalancomensal.salvarDespesas(despesas);
    }
}
