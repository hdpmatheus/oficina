package State;

import Entidades.Agendamento;

/**
 * Representa o estado "Cancelado" no ciclo de vida de um {@link Agendamento}.
 * 
 * Neste estado, o agendamento não pode mais ser avançado nem cancelado novamente.
 * Todas as operações de transição são bloqueadas e apenas mensagens informativas são exibidas.
 * 
 * Esta classe faz parte da implementação do padrão de projeto State, fornecendo
 * o comportamento específico do estado de cancelamento.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class EstadoCancelado implements EstadoAgendamento {

    /**
     * Informa que não é possível avançar um agendamento que já foi cancelado.
     *
     * @param agendamento O agendamento cancelado
     */
    @Override
    public void avancar(Agendamento agendamento) {
        System.out.println("Nao e possivel avançar. O agendamento foi cancelado.");
    }

    /**
     * Informa que o agendamento já se encontra cancelado.
     *
     * @param agendamento O agendamento já cancelado
     */
    @Override
    public void cancelar(Agendamento agendamento) {
        System.out.println("O agendamento ja esta cancelado.");
    }

    /**
     * Retorna o nome do estado atual.
     *
     * @return "Cancelado"
     */
    @Override
    public String getNome() {
        return "Cancelado";
    }
}
