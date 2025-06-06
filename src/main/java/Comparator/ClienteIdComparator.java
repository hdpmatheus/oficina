package Comparator;

import Entidades.Cliente;
import java.util.Comparator;

/**
 * Comparator para comparar clientes com base no ID.
 */
public class ClienteIdComparator implements Comparator<Cliente> {
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return Integer.compare(c1.getIdCliente(), c2.getIdCliente());
    }
}
