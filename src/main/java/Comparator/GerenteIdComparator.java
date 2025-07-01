package Comparator;

import Entidades.Gerente;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Gerente}
 * com base no ID do gerente.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de gerentes em ordem crescente
 * pelo seu identificador único.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class GerenteIdComparator implements Comparator<Gerente> {

    /**
     * Compara dois objetos {@link Gerente} com base no ID.
     *
     * @param g1 o primeiro gerente a ser comparado
     * @param g2 o segundo gerente a ser comparado
     * @return um valor negativo se o ID de {@code g1} for menor que o de {@code g2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Gerente g1, Gerente g2) {
        return Integer.compare(g1.getId(), g2.getId());
    }
}
