package Controler;

import Comparator.ServicoIdComparator;
import Entidades.Servico;
import Json.Jsonservico;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por gerenciar os serviços da oficina mecânica.
 * 
 * <p>Esta classe inclui métodos para instanciar serviços fixos, buscar serviços por ID
 * com busca binária, exibir todos os serviços, e salvar/carregar os dados usando JSON.</p>
 * 
 * @author Felipe Alcântara
 * @author Matheus Henrique
 */
public class GerenciarServico {

    /** Vetor que armazena os 8 serviços mais comuns da oficina */
    private Servico[] servicos = new Servico[8];

    /**
     * Construtor que instancia os serviços padrão da oficina.
     */
    public GerenciarServico() {
        servicos[0] = new Servico("Troca de oleo e filtro", 1, 120.00);
        servicos[1] = new Servico("Alinhamento e balanceamento", 2, 100.00);
        servicos[2] = new Servico("Troca de pastilhas de freio", 3, 150.00);
        servicos[3] = new Servico("Troca de amortecedores", 4, 300.00);
        servicos[4] = new Servico("Troca de pneus", 5, 200.00);
        servicos[5] = new Servico("Revisao completa", 6, 400.00);
        servicos[6] = new Servico("Diagnostico eletronico", 7, 80.00);
        servicos[7] = new Servico("Troca de bateria", 8, 250.00);
    }

    /**
     * Busca um serviço pelo seu ID utilizando busca binária.
     * 
     * @param id ID do serviço a ser buscado
     * @return O serviço encontrado, ou {@code null} se não existir
     */
    public Servico buscarServicoPorId(int id) {
        int index = Arrays.binarySearch(
            servicos,
            new Servico("", id, 0.0),
            new ServicoIdComparator()
        );
        return (index >= 0) ? servicos[index] : null;
    }

    /**
     * Retorna todos os serviços como uma lista.
     * 
     * @return Lista contendo os serviços da oficina
     */
    public List<Servico> getServicos() {
        return Arrays.asList(servicos);
    }

    /**
     * Imprime todos os serviços da oficina no console.
     */
    public void imprimirServicos() {
        for (Servico servico : servicos) {
            System.out.println("ID: " + servico.getIdServico() +
                               " | Tipo: " + servico.getTipoServico() +
                               " | Preco: R$" + servico.getPreco());
        }
    }

    /**
     * Salva os serviços da oficina no arquivo JSON correspondente.
     */
    public void salvarServicos() {
        List<Servico> listaServicos = Arrays.asList(servicos);
        Jsonservico.salvarServico(listaServicos);
    }

    /**
     * Carrega os serviços a partir do arquivo JSON, substituindo o vetor atual.
     */
    public void carregarServicosDoArquivo() {
        List<Servico> lista = Jsonservico.carregarServico();
        this.servicos = lista.toArray(new Servico[0]);
    }
}
