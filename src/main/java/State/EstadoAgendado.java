package State;

import Entidades.Agendamento;

public class EstadoAgendado implements EstadoAgendamento {
    @Override
    public void avancar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoConfirmado());
        
    }

    @Override
    public void cancelar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoCancelado());
        
    }

    @Override
    public String getNome() {
        return "Agendado";
    }
}
