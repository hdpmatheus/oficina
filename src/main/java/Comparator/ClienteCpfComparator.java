package Comparator;

import Entidades.Cliente;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Cliente}
 * com base no CPF.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de clientes em ordem crescente
 * de CPF.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class ClienteCpfComparator implements Comparator<Cliente> {

    /**
     * Compara dois objetos {@link Cliente} com base no CPF.
     *
     * @param c1 o primeiro cliente a ser comparado
     * @param c2 o segundo cliente a ser comparado
     * @return um valor negativo se o CPF de {@code c1} for menor que o de {@code c2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return Integer.compare(c1.getCpf(), c2.getCpf());
    }
}
