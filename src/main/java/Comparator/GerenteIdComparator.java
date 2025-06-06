package Comparator;

import Entidades.Gerente;
import java.util.Comparator;

/**
 * Comparator para comparar gerentes por ID.
 */
public class GerenteIdComparator implements Comparator<Gerente> {
    @Override
    public int compare(Gerente g1, Gerente g2) {
        return Integer.compare(g1.getId(), g2.getId());
    }
}
