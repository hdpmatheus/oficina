package State;

import Entidades.Agendamento;

/**
 * Representa o estado "Finalizado" no ciclo de vida de um {@link Agendamento}.
 * 
 * Neste estado, o agendamento foi concluído com sucesso e não pode mais ser alterado.
 * Nenhuma transição adicional é permitida, e qualquer tentativa de avançar ou cancelar
 * resultará em mensagens informativas.
 * 
 * Esta classe faz parte da implementação do padrão de projeto State, oferecendo
 * o comportamento fixo de um agendamento que chegou ao seu estado terminal.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class EstadoFinalizado implements EstadoAgendamento {

    /**
     * Informa que o agendamento já está finalizado e não pode mais avançar.
     *
     * @param agendamento O agendamento finalizado
     */
    @Override
    public void avancar(Agendamento agendamento) {
        System.out.println("O agendamento ja esta finalizado.");
    }

    /**
     * Informa que não é possível cancelar um agendamento finalizado.
     *
     * @param agendamento O agendamento finalizado
     */
    @Override
    public void cancelar(Agendamento agendamento) {
        System.out.println("Nao e possivel cancelar um agendamento finalizado.");
    }

    /**
     * Retorna o nome do estado atual.
     *
     * @return "Finalizado"
     */
    @Override
    public String getNome() {
        return "Finalizado";
    }
}
