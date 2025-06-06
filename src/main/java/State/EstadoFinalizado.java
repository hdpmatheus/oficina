package State;

import Entidades.Agendamento;

public class EstadoFinalizado implements EstadoAgendamento {
    @Override
    public void avancar(Agendamento agendamento) {
        System.out.println("O agendamento ja esta finalizado.");
    }

    @Override
    public void cancelar(Agendamento agendamento) {
        System.out.println("Nao e possivel cancelar um agendamento finalizado.");
    }

    @Override
    public String getNome() {
        return "Finalizado";
    }
}
