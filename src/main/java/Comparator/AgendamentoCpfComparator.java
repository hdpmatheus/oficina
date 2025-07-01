package Comparator;

import Entidades.Agendamento;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Agendamento}
 * com base no ID do cliente associado ao agendamento.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de agendamentos em ordem crescente
 * de acordo com o ID do cliente.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class AgendamentoCpfComparator implements Comparator<Agendamento> {

    /**
     * Compara dois objetos {@link Agendamento} com base no ID do cliente.
     *
     * @param a1 o primeiro agendamento a ser comparado
     * @param a2 o segundo agendamento a ser comparado
     * @return um valor negativo se o ID do cliente de {@code a1} for menor que o de {@code a2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        return Integer.compare(a1.getIdCliente(), a2.getIdCliente());
    }
}
