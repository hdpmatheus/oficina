package Comparator;

import Entidades.Funcionario;
import java.util.Comparator;

/**
 * Comparator para comparar funcionários por ID.
 */
public class FuncionarioIdComparator implements Comparator<Funcionario> {
    @Override
    public int compare(Funcionario f1, Funcionario f2) {
        return Integer.compare(f1.getId(), f2.getId());
    }
}
