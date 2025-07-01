package Comparator;

import Entidades.Funcionario;
import java.util.Comparator;

/**
 * Classe que implementa um {@link Comparator} para comparar objetos do tipo {@link Funcionario}
 * com base no ID do funcionário.
 * <p>
 * Essa classe pode ser utilizada para ordenar listas de funcionários em ordem crescente
 * pelo seu identificador único.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class FuncionarioIdComparator implements Comparator<Funcionario> {

    /**
     * Compara dois objetos {@link Funcionario} com base no ID.
     *
     * @param f1 o primeiro funcionário a ser comparado
     * @param f2 o segundo funcionário a ser comparado
     * @return um valor negativo se o ID de {@code f1} for menor que o de {@code f2},
     *         zero se forem iguais, ou um valor positivo se for maior
     */
    @Override
    public int compare(Funcionario f1, Funcionario f2) {
        return Integer.compare(f1.getId(), f2.getId());
    }
}
