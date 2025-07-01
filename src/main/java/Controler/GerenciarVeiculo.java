package Controler;

import Comparator.VeiculoPlacaComparator;
import Entidades.Veiculo;
import Json.Jsonveiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por gerenciar a lista de veículos da oficina.
 * Inclui métodos para criar, buscar, alterar, remover e salvar veículos.
 *
 * <p>Os veículos são identificados principalmente pela placa. As operações utilizam
 * busca binária com um {@link VeiculoPlacaComparator} para maior eficiência.</p>
 *
 * <p>A persistência dos dados é realizada em JSON, por meio da classe {@link Jsonveiculo}.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class GerenciarVeiculo {

    /** Lista que armazena todos os veículos cadastrados. */
    private List<Veiculo> veiculos;

    /** Construtor padrão que inicializa a lista de veículos. */
    public GerenciarVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    /**
     * Adiciona um novo veículo à lista.
     *
     * @param v Veículo a ser cadastrado.
     */
    public void criarVeiculo(Veiculo v) {
        this.veiculos.add(v);
        System.out.println("✅ Veículo cadastrado: " + v.getPlaca());
    }

    /**
     * Salva a lista atual de veículos no arquivo JSON.
     */
    public void salvarVeiculos() {
        Jsonveiculo.salvarVeiculo(veiculos);
    }

    /**
     * Busca um veículo pela placa, utilizando busca binária.
     *
     * @param placa Placa do veículo.
     * @return Veículo encontrado ou {@code null} se não for encontrado.
     */
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        Collections.sort(veiculos, new VeiculoPlacaComparator());
        Veiculo veiculoBusca = new Veiculo(placa, "", 0, "", "", "", null);
        int index = Collections.binarySearch(veiculos, veiculoBusca, new VeiculoPlacaComparator());

        if (index >= 0) {
            return veiculos.get(index);
        } else {
            System.out.println("❌ Veículo com placa " + placa + " não encontrado.");
            return null;
        }
    }

    /**
     * Altera os dados de um veículo.
     *
     * @param placa Placa do veículo a ser alterado.
     * @param novoModelo Novo modelo.
     * @param novoAno Novo ano.
     * @param novaCor Nova cor.
     */
    public void alterarVeiculo(String placa, String novoModelo, int novoAno, String novaCor) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculo.setModelo(novoModelo);
            veiculo.setAno(novoAno);
            veiculo.setCor(novaCor);
            System.out.println("✅ Veículo alterado: " + veiculo);
        } else {
            System.out.println("❌ Veículo não encontrado.");
        }
    }

    /**
     * Remove um veículo da lista com base na placa.
     *
     * @param placa Placa do veículo a ser removido.
     */
    public void removerVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculos.remove(veiculo);
            System.out.println("✅ Veículo removido: " + placa);
        } else {
            System.out.println("❌ Veículo não encontrado.");
        }
    }

    /**
     * Exibe no console todos os veículos cadastrados.
     */
    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("⚠️ Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("🚗 Lista de Veículos:");
        for (Veiculo v : veiculos) {
            System.out.println("- Placa: " + v.getPlaca() +
                    ", Modelo: " + v.getModelo() +
                    ", Marca: " + v.getMarca() +
                    ", Ano: " + v.getAno() +
                    ", Tipo: " + v.getTipo() +
                    ", Cor: " + v.getCor());
        }
    }

    /**
     * Retorna a lista de veículos cadastrados.
     *
     * @return Lista de veículos.
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
