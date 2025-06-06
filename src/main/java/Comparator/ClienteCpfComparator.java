package Comparator;

import Entidades.Cliente;
import java.util.Comparator;

/**
 * Comparator para comparar clientes com base no CPF.
 */
public class ClienteCpfComparator implements Comparator<Cliente> {
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return Integer.compare(c1.getCpf(), c2.getCpf());
    }
}
