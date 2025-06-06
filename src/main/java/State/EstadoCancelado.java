package State;

import Entidades.Agendamento;

public class EstadoCancelado implements EstadoAgendamento {

    @Override
    public void avancar(Agendamento agendamento) {
        System.out.println("Nao e possivel avançar. O agendamento foi cancelado.");
    }

    @Override
    public void cancelar(Agendamento agendamento) {
        System.out.println("O agendamento ja esta cancelado.");
    }

    @Override
    public String getNome() {
        return "Cancelado";
    }
}
