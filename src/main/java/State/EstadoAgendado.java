package State;

import Entidades.Agendamento;

/**
 * Representa o estado "Agendado" no ciclo de vida de um agendamento.
 * 
 * A partir deste estado, o agendamento pode ser:
 * <ul>
 *   <li>Avançado para o estado {@link EstadoConfirmado}</li>
 *   <li>Cancelado, indo para o estado {@link EstadoCancelado}</li>
 * </ul>
 * 
 * Esta classe implementa o padrão de projeto State, permitindo
 * a transição de estados de forma orientada a objetos.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class EstadoAgendado implements EstadoAgendamento {

    /**
     * Avança o agendamento para o estado "Confirmado".
     *
     * @param agendamento O agendamento a ser atualizado
     */
    @Override
    public void avancar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoConfirmado());
    }

    /**
     * Cancela o agendamento, mudando seu estado para "Cancelado".
     *
     * @param agendamento O agendamento a ser atualizado
     */
    @Override
    public void cancelar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoCancelado());
    }

    /**
     * Retorna o nome do estado atual.
     *
     * @return String representando o nome do estado
     */
    @Override
    public String getNome() {
        return "Agendado";
    }
}
