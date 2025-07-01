package State;

import Entidades.Agendamento;

/**
 * Representa o estado \"Em Andamento\" de um {@link Agendamento}.
 * <p>
 * Neste estado, o agendamento pode ser:
 * <ul>
 *   <li>Avançado para o estado "Finalizado"</li>
 *   <li>Cancelado, indo para o estado "Cancelado"</li>
 * </ul>
 * </p>
 *
 * Implementa a interface {@link EstadoAgendamento} como parte do padrão State.
 * 
 * @author Felipe Alcantara Guimaraes Veloso
 * @author Matheus Henrique de Paula
 */
public class EstadoEmAndamento implements EstadoAgendamento {

    /**
     * Avança o agendamento para o estado "Finalizado".
     *
     * @param agendamento o agendamento a ser alterado
     */
    @Override
    public void avancar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoFinalizado());
    }

    /**
     * Cancela o agendamento, alterando seu estado para "Cancelado".
     *
     * @param agendamento o agendamento a ser alterado
     */
    @Override
    public void cancelar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoCancelado());
    }

    /**
     * Retorna o nome do estado atual.
     *
     * @return uma {@link String} representando o estado
     */
    @Override
    public String getNome() {
        return "Em andamento";
    }
}
