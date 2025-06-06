package Comparator;

import Entidades.Produto;
import java.util.Comparator;

/**
 * Comparator para comparar produtos (peças) por ID.
 */
public class ProdutoIdComparator implements Comparator<Produto> {
    @Override
    public int compare(Produto p1, Produto p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }
}
