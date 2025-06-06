package Comparator;

import Entidades.Agendamento;
import java.util.Comparator;

/**
 * Comparator para comparar agendamentos com base no ID do cliente.
 */
public class AgendamentoCpfComparator implements Comparator<Agendamento> {
    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        return Integer.compare(a1.getIdCliente(), a2.getIdCliente());
    }
}
