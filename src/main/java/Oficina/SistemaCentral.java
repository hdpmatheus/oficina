package oficina;

import Adapter.PagamentoAdapter;
import Adapter.ProcessadorPagamento;
import Adapter.SistemaExterno;
import Comparator.AgendamentoCpfComparator;
import Controler.BalancoMensal;
import Controler.ControleDeAcesso;
import Controler.GerenciarCliente;
import Controler.GerenciarServico;
import Controler.GerenciarFuncionario;
import Controler.GerenciarGerente;
import Entidades.*;
import Json.Jsonagendamento;
import Json.Jsonvenda;
import Json.Jsonelevador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
    /**
     * Classe principal do sistema da oficina mecânica.
     * 
     * Responsável por gerenciar clientes, serviços, funcionários, gerentes, agendamentos, vendas,
     * controle de elevadores, estoque, login, balanço mensal e integração com sistema externo de pagamentos.
     * 
     * Também realiza controle de agendamentos, vendas com nota fiscal e manipulação de contadores e serviços padrão.
     * 
     * @author 
     * Matheus Henrique de Paula <br>
     * Felipe Alcântara Guimarães Veloso
     */
public class SistemaCentral {
      // Atributos de controle e gerência
    private GerenciarCliente gerenciarCliente;
    private GerenciarServico gerenciarServico;
    private Estoque estoque = new Estoque();
    private GerenciarFuncionario gerenciarFuncionario;
    private GerenciarGerente gerenciarGerente;
    private ProcessadorPagamento processadorPagamento;
    private ControleDeAcesso controledeacesso;
    private BalancoMensal balancomensal;
    private Login login;
    private static Elevador[] elevadores = new Elevador[3];

    protected static int contadorClientes = 0;
    private static int contadorProdutos = 0;

    private List<Agendamento> listaAgendamentos;
    private List<Venda> listaVendas;
    private List<Servico> servicosPadrao = new ArrayList<>();

    SistemaExterno sistemaexterno = new SistemaExterno();
    /**
     * Construtor da classe. Inicializa todos os gerenciadores, carregando dados de JSON.
     * Também instancia elevadores, lista de agendamentos e configura os adaptadores.
     */
    public SistemaCentral() {
        this.gerenciarCliente = new GerenciarCliente();
        this.gerenciarServico = new GerenciarServico();
        this.gerenciarCliente.carregarClienteDoArquivo();
        this.estoque.carregarProdutosDoArquivo();
        this.gerenciarFuncionario = new GerenciarFuncionario();
        this.gerenciarGerente = new GerenciarGerente();
        this.estoque = new Estoque();
        this.listaAgendamentos = Jsonagendamento.carregarAgendamentos();
        this.listaVendas = new ArrayList<>();
        this.processadorPagamento = new PagamentoAdapter(sistemaexterno);
        this.controledeacesso = new ControleDeAcesso(gerenciarFuncionario);
        this.login = new Login(gerenciarFuncionario, gerenciarGerente);
        this.balancomensal = new BalancoMensal(estoque, gerenciarServico, login);
        
        elevadores = Jsonelevador.carregarElevadores();
        if(elevadores == null || elevadores.length == 0){
            inicializarElevadores();
        }
        
    }
        /**
         * Inicializa os 3 elevadores padrão da oficina.
         * O terceiro é exclusivo para serviços de alinhamento e balanceamento.
         */
        private void inicializarElevadores(){
            elevadores = new Elevador [3];
            elevadores[0] = new Elevador(1, false); // Uso geral
            elevadores[1] = new Elevador(2, false); // Uso geral
            elevadores[2] = new Elevador(3, true);  // Exclusivo para Alinhamento e Balanceamento
        }
     /**
     * Tenta reservar um elevador com o ID informado para um determinado veículo.
     * 
     * @param id ID do elevador (1 a 3)
     * @param veiculo Veículo a ser colocado no elevador
     * @return true se reservado com sucesso, false caso contrário
     */
        public boolean reservarElevador(int id, Veiculo veiculo) {
            if (id < 1 || id > elevadores.length) {
                System.out.println("❌ ID de elevador inválido.");
                return false;
            }
            Elevador elevador = elevadores[id - 1];
            if (elevador == null) {
                System.out.println("❌ Elevador não existe.");
                return false;
            }
            if (elevador.reservar()) {
                elevador.setVeiculoAtual(veiculo);
                System.out.println("✅ Elevador " + id + " reservado com sucesso.");
                Jsonelevador.salvarElevadores(elevadores);
                return true;
            } else {
                System.out.println("⚠️ Elevador " + id + " já está ocupado.");
                return false;
            }
        }
         /**
         * Libera o elevador correspondente ao ID fornecido.
         * 
         * @param id ID do elevador a ser liberado
         */

        public void liberarElevador(int id) {
            if (id < 1 || id > elevadores.length) {
                System.out.println("❌ ID de elevador inválido.");
                return;
            }
            Elevador elevador = elevadores[id - 1];
            if (elevador != null) {
                elevador.liberar();
                System.out.println("✅ Elevador " + id + " liberado.");
            } else {
                System.out.println("❌ Elevador não encontrado.");
            }
        }
        /**
     * Exibe no console os contadores de veículos (encapsulado e protegido).
     */
        public void exibirTotalDeVeiculos(){
            System.out.println("Total de veiculos (encapsulado): " + Veiculo.getContadorVeiculosEncapsulado());
            System.out.println("Total de veículos (protegido): " + Veiculo.getContadorVeiculosProtegido());
        }

    public GerenciarCliente getGerenciarCliente() { return gerenciarCliente; }
    public GerenciarServico getGerenciarServico() { return gerenciarServico; }
    public Estoque getEstoque() { return estoque; }
    public GerenciarFuncionario getGerenciarFuncionario() { return gerenciarFuncionario; }
    public GerenciarGerente getGerenciarGerente() { return gerenciarGerente; }
    public static int getContadorClientes() { return contadorClientes; }
    public static int getContadorProdutos() { return contadorProdutos; }
    public static void incrementarContadorClientes() { contadorClientes++; }
    public static void incrementarContadorProdutos() { contadorProdutos++; }
    public BalancoMensal getBalancomensal() { return balancomensal; }
    public ControleDeAcesso getControleDeAcesso() { return controledeacesso; }

    /**
     * Realiza uma venda simples com pagamento, baixa de estoque e registro.
     * 
     * @param idCliente ID do cliente comprador
     * @param idProduto ID do produto vendido
     * @param pagamento Adaptador de pagamento usado
     */
    
    public void realizarVenda(int idCliente, int idProduto, ProcessadorPagamento pagamento) {
        Cliente cliente = gerenciarCliente.buscarCliente(idCliente);
        Produto produto = estoque.buscarProduto(idProduto);

        if (cliente == null || produto == null) {
            System.out.println("❌ Cliente ou produto não encontrado.");
            return;
        }

        if (produto.getQuantidade() <= 0) {
            System.out.println("❌ Estoque insuficiente para o produto: " + produto.getNome());
            return;
        }

        double total = produto.getPreco();

        if (pagamento.realizarPagamento(total, idCliente)) {
            estoque.removerQuantidade(produto.getIdProduto(), 1);

            Venda venda = new Venda(cliente);
            venda.adicionarItem(produto);

            registrarVenda(venda);
            balancomensal.adicionarReceitaVenda(venda);

            System.out.println("✅ Venda realizada com sucesso!");
        } else {
            System.out.println("❌ Pagamento recusado.");
        }
    }
     /**
     * Cria um pré-agendamento no sistema com cliente, serviço, data, funcionário e veículo.
     */

    public void salvarVenda() {
        Jsonvenda.salvarVendas(listaVendas);
    }

    public void criarPreAgendamento(int idCliente, int servicoId, Data dataAgendamento, int funcionarioId, Veiculo veiculo) {
        Cliente cliente = gerenciarCliente.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        Servico servico = gerenciarServico.buscarServicoPorId(servicoId);
        if (servico == null) {
            System.out.println("Servico nao encontrado.");
            return;
        }

        Funcionario funcionario = gerenciarFuncionario.buscarFuncionario(funcionarioId);
        if (funcionario == null) {
            System.out.println("Funcionario nao encontrado.");
            return;
        }

        Agendamento agendamentoExistente = buscarAgendamentoPorIdCliente(idCliente);
        if (agendamentoExistente != null && agendamentoExistente.getDataAgendamento().equals(dataAgendamento)) {
            System.out.println("Ja existe um agendamento para este cliente na mesma data.");
            return;
        }

        Agendamento agendamento = new Agendamento(dataAgendamento, null, idCliente, servicoId, funcionario);
        listaAgendamentos.add(agendamento);
        agendamento.setVeiculo(veiculo);
        System.out.println("Pre-agendamento realizado com sucesso para " + cliente.getNome() + " no servico " + servico.getTipoServico() + " no dia: " + dataAgendamento); 
    }
        /**
         * Confirma um agendamento associando data, veículo e elevador.
         * 
         * @param idCliente ID do cliente
         * @param dataConfirmacao Data da confirmação
         * @param veiculo Veículo usado
         */

    public Agendamento buscarAgendamentoPorIdCliente(int idCliente) {
        Agendamento agendamentoBuscado = new Agendamento(null, null, idCliente, 0, null);
        Collections.sort(listaAgendamentos, new AgendamentoCpfComparator());
        int index = Collections.binarySearch(listaAgendamentos, agendamentoBuscado, new AgendamentoCpfComparator());
        return (index >= 0) ? listaAgendamentos.get(index) : null;
    }

    public void confirmarAgendamento(int idCliente, Data dataConfirmacao) {
        Agendamento agendamento = buscarAgendamentoPorIdCliente(idCliente);
        if (agendamento == null) {
            System.out.println("Agendamento nao encontrado para o cliente: " + idCliente);
            return;
        }
        agendamento.setDataConfirmacao(dataConfirmacao);

        Cliente cliente = gerenciarCliente.buscarCliente(idCliente);
        Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());

        // ✅ Seleciona o primeiro veículo do cliente (ou personalize a lógica se desejar)
        Veiculo veiculoUsado = (!cliente.getVeiculos().isEmpty()) ? cliente.getVeiculos().get(0) : null;

        for (Elevador elevador : elevadores) {
            if (!elevador.isOcupado()) {
                if (elevador.isExclusivoAlinhamento()) {
                    if (servico != null && servico.getTipoServico().toLowerCase().contains("alinhamento")) {
                        elevador.reservar();
                        elevador.setVeiculoAtual(veiculoUsado); // ✅ Associa veículo
                        agendamento.setElevador(elevador);
                        break;
                    }
                } else {
                    elevador.reservar();
                    elevador.setVeiculoAtual(veiculoUsado); // ✅ Associa veículo
                    agendamento.setElevador(elevador);
                    break;
                }
            }
        }

        if (agendamento.getElevador() == null) {
            System.out.println("⚠️ Nenhum elevador disponível no momento.");
        } else {
            System.out.println("✅ Elevador reservado: " + agendamento.getElevador().getId());
        }

        agendamento.avancarEstado();
        balancomensal.adicionarReceitaAgendamento(agendamento);

        System.out.println("Agendamento confirmado com sucesso! " +
                "Cliente: " + cliente.getNome() +
                ", Data Agendamento: " + agendamento.getDataAgendamento() +
                ", Data Confirmacao: " + dataConfirmacao +
                ", Status: " + agendamento.getStatus());
    }
        /**
         * Avança o estado de um agendamento para o próximo passo (ex: de "Confirmado" para "Finalizado").
         * 
         * @param idCliente ID do cliente
         */

    public void atualizarStatusAgendamento(int idCliente) {
        Agendamento agendamento = buscarAgendamentoPorIdCliente(idCliente);
        if (agendamento == null) {
            System.out.println("Agendamento nao encontrado.");
            return;
        }

        agendamento.avancarEstado();
        System.out.println("Status atualizado para: " + agendamento.getStatus() + " para o cliente " + idCliente);
    }

    public void salvarAgendamento() {
        Jsonagendamento.salvarAgendamento(listaAgendamentos);
    }

    public boolean removerAgendamento(int idAgendamento) {
        for (Agendamento agendamento : listaAgendamentos) {
            if (agendamento.getIdCliente() == idAgendamento) {
                balancomensal.removerReceitaAgendamento(agendamento);
                listaAgendamentos.remove(agendamento);
                System.out.println("Agendamento do cliente com ID: " + idAgendamento + " removido com sucesso.");
                return true;
            }
        }
        System.out.println("Agendamento com ID " + idAgendamento + " nao encontrado.");
        return false;
    }

    public void imprimirVendas() {
        if (listaVendas.isEmpty()) {
            System.out.println("Nao ha vendas registradas.");
            return;
        }

        System.out.println("=== Lista de Vendas ===");
        for (Venda venda : listaVendas) {
            System.out.println("Cliente: " + venda.getCliente().getNome() +
                               ", Data: " + venda.getDataVenda() +
                               ", Valor Total: R$" + venda.getValorTotal());
            for (Produto p : venda.getProdutos()) {
                System.out.println("  - Item: " + p.getNome() + " | R$" + p.getPreco());
            }
        }
    }


    public void listarAgendamentos() {
        if (listaAgendamentos.isEmpty()) {
            System.out.println("Nao ha agendamentos cadastrados.");
            return;
        }

        System.out.println("=== Lista de Agendamentos ===");
        for (Agendamento agendamento : listaAgendamentos) {
            Cliente cliente = gerenciarCliente.buscarCliente(agendamento.getIdCliente());
            Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());

            String nomeCliente = (cliente != null) ? cliente.getNome() : "Desconhecido";
            String nomeServico = (servico != null) ? servico.getTipoServico() : "Desconhecido";
            String nomeFuncionario = (agendamento.getFuncionario() != null) ? agendamento.getFuncionario().getNome() : "N/A ";
            String dataConfirmacao = (agendamento.getDataConfirmacao() != null) ? agendamento.getDataConfirmacao().toString() : "Nao confirmada";

            System.out.println("Cliente: " + nomeCliente +
                    ", Servico: " + nomeServico +
                    ", Funcionario: " + nomeFuncionario +
                    ", Data Agendamento: " + agendamento.getDataAgendamento() +
                    ", Data Confirmacao: " + dataConfirmacao +
                    ", Status: " + agendamento.getStatus());
        }
    }
    public void recalcularContadoresVeiculos() {
        int encapsulado = 0;
        int protegido = 0;

         for (Cliente cliente : gerenciarCliente.getClientes()) {
            for (Veiculo veiculo : cliente.getVeiculos()) {
                encapsulado++;
                 protegido++;
            }
        }

            // Atualiza os contadores manualmente
        Veiculo.setContadorVeiculosEncapsulado(encapsulado);
        Veiculo.setContadorVeiculosProtegido(protegido);
        }
   

        public void carregarEstoquePadrao() {
        estoque.criarProduto(new Produto(1, "Óleo 10W40", 20, 50.0));
        estoque.criarProduto(new Produto(2, "Filtro de óleo", 20, 30.0));
        estoque.criarProduto(new Produto(3, "Pastilhas de freio", 15, 80.0));
        estoque.criarProduto(new Produto(4, "Amortecedor", 10, 150.0));
        estoque.criarProduto(new Produto(5, "Pneu Aro escolhido", 16, 200.0));
        estoque.criarProduto(new Produto(6, "Bateria 60Ah", 8, 250.0));
    }


    public void cadastrarServicosPadrao() {
        servicosPadrao.add(new Servico("Troca de oleo e filtro", 1, 120.00));
        servicosPadrao.add(new Servico("Alinhamento e balanceamento", 2, 100.00));
        servicosPadrao.add(new Servico("Troca de pastilhas de freio", 3, 150.00));
        servicosPadrao.add(new Servico("Troca de amortecedores", 4, 300.00));
        servicosPadrao.add(new Servico("Troca de pneus", 5, 200.00));
        servicosPadrao.add(new Servico("Revisao completa", 6, 400.00));
        servicosPadrao.add(new Servico("Diagnostico eletronico", 7, 80.00));
        servicosPadrao.add(new Servico("Troca de bateria", 8, 250.00));
    }
    public void realizarAtendimentoComNota(Cliente cliente, Servico servico) {
        List<Produto> itensUsados = new ArrayList<>();
        List<Integer> idsProdutos = MapaServicoProduto.getIdsProdutosParaServico(servico.getTipoServico());

        for (int idProduto : idsProdutos) {
            Produto produto = estoque.buscarProduto(idProduto);
            if (produto != null && produto.getQuantidade() > 0) {
                estoque.removerQuantidade(idProduto, 1);
                itensUsados.add(produto);
            } else {
                System.out.println("⚠️ Produto insuficiente ou não encontrado para ID: " + idProduto);
            }
        }

        // Criar venda
        Venda venda = new Venda(cliente);
        venda.adicionarItem(servico);
        for (Produto p : itensUsados) {
            venda.adicionarItem(p);
        }

        // Registrar a venda (usando método dedicado)
        registrarVenda(venda);

        // Emitir nota fiscal
        System.out.println("\n=== Nota Fiscal ===");
        System.out.println("Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
        System.out.println("Serviço realizado: " + servico.getTipoServico() + " - R$" + servico.getPreco());

        if (!itensUsados.isEmpty()) {
            System.out.println("Produtos utilizados:");
            for (Produto p : itensUsados) {
                System.out.println("- " + p.getNome() + " (R$" + p.getPreco() + ")");
            }
        } else {
            System.out.println("Nenhum produto foi consumido neste serviço.");
        }

        System.out.println("Valor Total da Venda: R$" + venda.getValorTotal());
    }
    public void registrarVenda(Venda venda) {
        listaVendas.add(venda);
        System.out.println("✅ Venda registrada com sucesso.");
}



   public void listarServicos() {
        if (servicosPadrao.isEmpty()) {
            System.out.println("Nenhum servico cadastrado.");
            return;
        }

        System.out.println("=== Lista de Servicos Disponiveis ===");
        for (Servico s : servicosPadrao) {
            System.out.println("ID: " + s.getIdServico() +
                    " | Tipo: " + s.getTipoServico() +
                    " | Preco: R$" + s.getPreco());
        }
    }

    public void avancarStatusAgendamento(int idCliente) {
    Agendamento agendamento = buscarAgendamentoPorIdCliente(idCliente);
    if (agendamento == null) {
        System.out.println("Agendamento não encontrado para o cliente com ID: " + idCliente);
        return;
    }

    agendamento.avancarEstado();

    // ✅ Se finalizado, libera o elevador
    if (agendamento.getStatus().equals("Finalizado") && agendamento.getElevador() != null) {
        agendamento.getElevador().liberar();
        System.out.println("Elevador liberado para novo agendamento.");
    }

    System.out.println("Novo status do agendamento: " + agendamento.getStatus());
}

    public Servico buscarServicoPorIdManual(int id) {
        for (Servico s : servicosPadrao) {
            if (s.getIdServico() == id) return s;
        }
        return null;
    }
    public void listarElevadores() {
        System.out.println("=== ELEVADORES ===");
        for (Elevador e : elevadores) {
            System.out.println(e);
        }
    }
    public static Elevador getElevadorById(int id) {
        for (Elevador e : elevadores) {
            if (e != null && e.getId() == id){ 
                return e;
            }
        }
        return null;
    }
    public void salvarElevadores() {
        Json.Jsonelevador.salvarElevadores(elevadores);
    }
    public static Elevador[] getElevadores() {
        return elevadores;
}
    public void confirmarAgendamentoComVeiculo(int idCliente, Data dataConfirmacao, Veiculo veiculo) {
    Agendamento agendamento = buscarAgendamentoPorIdCliente(idCliente);
    if (agendamento == null) {
        System.out.println("Agendamento não encontrado.");
        return;
    }

    agendamento.setDataConfirmacao(dataConfirmacao);
    agendamento.setVeiculo(veiculo);

    // Alocação de elevador
    for (Elevador elevador : elevadores) {
        if (!elevador.isOcupado()) {
            Servico servico = gerenciarServico.buscarServicoPorId(agendamento.getIdServico());
            if (elevador.isExclusivoAlinhamento()) {
                if (servico != null && servico.getTipoServico().toLowerCase().contains("alinhamento")) {
                    elevador.reservar();
                    agendamento.setElevador(elevador);
                    break;
                }
            } else {
                elevador.reservar();
                agendamento.setElevador(elevador);
                break;
            }
        }
    }

    if (agendamento.getElevador() == null) {
        System.out.println("⚠️ Nenhum elevador disponível.");
    } else {
        System.out.println("✅ Elevador reservado: " + agendamento.getElevador().getId());
    }

    agendamento.avancarEstado();
    balancomensal.adicionarReceitaAgendamento(agendamento);
    System.out.println("Agendamento confirmado com sucesso com o veículo: " + veiculo);
}


}
