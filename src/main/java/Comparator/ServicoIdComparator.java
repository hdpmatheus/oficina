package Comparator;

import Entidades.Servico;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Servico}
 * com base no ID do serviço.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de serviços em ordem crescente
 * de acordo com seu identificador único.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class ServicoIdComparator implements Comparator<Servico> {

    /**
     * Compara dois objetos {@link Servico} com base no ID do serviço.
     *
     * @param s1 o primeiro serviço a ser comparado
     * @param s2 o segundo serviço a ser comparado
     * @return um valor negativo se o ID de {@code s1} for menor que o de {@code s2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Servico s1, Servico s2) {
        return Integer.compare(s1.getIdServico(), s2.getIdServico());
    }
}
