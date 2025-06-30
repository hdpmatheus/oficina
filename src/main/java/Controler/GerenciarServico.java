package Controler;

import Comparator.ServicoIdComparator;
import Entidades.Servico;
import Json.Jsonservico;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsavel por gerenciar os servicos na oficina.
 * Inclui metodos para instanciar servicos, buscar servicos por ID,
 * imprimir a lista de servicos e salvar os servicos em um arquivo JSON.
 */
public class GerenciarServico {

    private Servico[] servicos = new Servico[8];  // Vetor para armazenar os 8 servicos mais comuns

    // Construtor que instancia os servicos oferecidos pela oficina
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

    // Metodo para buscar servico por ID usando busca binaria
    public Servico buscarServicoPorId(int id) {
        int index = Arrays.binarySearch(
            servicos,
            new Servico("", id, 0.0),
            new ServicoIdComparator()
        );
        return (index >= 0) ? servicos[index] : null;
    }
    
    public List<Servico> getServicos() {
        return Arrays.asList(servicos);
    }


    // Metodo para imprimir os servicos armazenados no vetor
    public void imprimirServicos() {
        for (Servico servico : servicos) {
            System.out.println("ID: " + servico.getIdServico() +
                               " | Tipo: " + servico.getTipoServico() +
                               " | Preco: R$" + servico.getPreco());
        }
    }

    // Metodo para salvar os servicos em um arquivo JSON
    public void salvarServicos() {
        List<Servico> listaServicos = Arrays.asList(servicos);
        Jsonservico.salvarServico(listaServicos);
    }
    public void carregarServicosDoArquivo() {
        List<Servico> lista = Jsonservico.carregarServico();
        this.servicos = lista.toArray(new Servico[0]); // conversão de List para array
    }

}
