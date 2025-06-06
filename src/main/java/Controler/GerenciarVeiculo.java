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
 */
public class GerenciarVeiculo {

    private List<Veiculo> veiculos;

    public GerenciarVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    // Criar veículo
    public void criarVeiculo(Veiculo v) {
        this.veiculos.add(v);
        System.out.println("✅ Veículo cadastrado: " + v.getPlaca());
    }

    // Salvar lista de veículos em JSON
    public void salvarVeiculos() {
        Jsonveiculo.salvarVeiculo(veiculos);
    }

    // Buscar veículo por placa (usando busca binária)
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        Collections.sort(veiculos, new VeiculoPlacaComparator());
        Veiculo veiculoBusca = new Veiculo(placa, "", 0, "", "", "", null); // objeto temporário
        int index = Collections.binarySearch(veiculos, veiculoBusca, new VeiculoPlacaComparator());

        if (index >= 0) {
            return veiculos.get(index);
        } else {
            System.out.println("❌ Veículo com placa " + placa + " não encontrado.");
            return null;
        }
    }

    // Alterar modelo, ano e cor do veículo
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

    // Remover veículo
    public void removerVeiculo(String placa) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            veiculos.remove(veiculo);
            System.out.println("✅ Veículo removido: " + placa);
        } else {
            System.out.println("❌ Veículo não encontrado.");
        }
    }

    // Listar veículos
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

    // Getter da lista
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
