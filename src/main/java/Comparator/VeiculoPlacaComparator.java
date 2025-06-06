package Comparator;

import Entidades.Veiculo;
import java.util.Comparator;

/**
 * Comparator para comparar veículos com base na placa.
 */
public class VeiculoPlacaComparator implements Comparator<Veiculo> {
    @Override
    public int compare(Veiculo v1, Veiculo v2) {
        return v1.getPlaca().compareToIgnoreCase(v2.getPlaca());
    }
}
