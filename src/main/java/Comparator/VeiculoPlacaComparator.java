package Comparator;

import Entidades.Veiculo;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Veiculo}
 * com base na placa do veículo.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de veículos em ordem alfabética
 * ignorando diferenças entre maiúsculas e minúsculas nas placas.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class VeiculoPlacaComparator implements Comparator<Veiculo> {

    /**
     * Compara dois objetos {@link Veiculo} com base na placa, ignorando maiúsculas e minúsculas.
     *
     * @param v1 o primeiro veículo a ser comparado
     * @param v2 o segundo veículo a ser comparado
     * @return um valor negativo se a placa de {@code v1} for lexicograficamente menor que a de {@code v2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Veiculo v1, Veiculo v2) {
        return v1.getPlaca().compareToIgnoreCase(v2.getPlaca());
    }
}
