package State;

import Entidades.Agendamento;

public class EstadoConfirmado implements EstadoAgendamento {
    @Override
    public void avancar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoEmAndamento());
    }

    @Override
    public void cancelar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() {
        return "Confirmado";
    }
}
