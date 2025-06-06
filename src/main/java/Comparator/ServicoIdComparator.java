package Comparator;

import Entidades.Servico;
import java.util.Comparator;

/**
 * Comparator para comparar objetos Servico com base no ID do serviço.
 */
public class ServicoIdComparator implements Comparator<Servico> {
    @Override
    public int compare(Servico s1, Servico s2) {
        return Integer.compare(s1.getIdServico(), s2.getIdServico());
    }
}
