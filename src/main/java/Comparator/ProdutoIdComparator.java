package Comparator;

import Entidades.Produto;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Produto}
 * com base no ID do produto (peça).
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de produtos em ordem crescente
 * pelo seu identificador único.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class ProdutoIdComparator implements Comparator<Produto> {

    /**
     * Compara dois objetos {@link Produto} com base no ID.
     *
     * @param p1 o primeiro produto a ser comparado
     * @param p2 o segundo produto a ser comparado
     * @return um valor negativo se o ID de {@code p1} for menor que o de {@code p2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Produto p1, Produto p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }
}
