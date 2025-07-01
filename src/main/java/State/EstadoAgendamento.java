package State;

import Entidades.Agendamento;

/**
 * Interface que define o contrato para os estados possíveis de um {@link Agendamento}.
 * 
 * Esta interface faz parte da implementação do padrão de projeto State, permitindo
 * que cada estado do agendamento (como Agendado, Confirmado, Cancelado) implemente
 * comportamentos distintos para avançar ou cancelar.
 * 
 * Cada estado deve fornecer uma implementação para os métodos de transição
 * e retornar seu nome representativo.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public interface EstadoAgendamento {

    /**
     * Avança o agendamento para o próximo estado no fluxo.
     *
     * @param agendamento O agendamento a ser atualizado
     */
    void avancar(Agendamento agendamento);

    /**
     * Cancela o agendamento, alterando seu estado atual para cancelado.
     *
     * @param agendamento O agendamento a ser atualizado
     */
    void cancelar(Agendamento agendamento);

    /**
     * Retorna o nome do estado atual.
     *
     * @return Nome do estado
     */
    String getNome();
}
