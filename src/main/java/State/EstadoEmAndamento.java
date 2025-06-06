package State;

import Entidades.Agendamento;

public class EstadoEmAndamento implements EstadoAgendamento {
    @Override
    public void avancar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoFinalizado());
        
    }

    @Override
    public void cancelar(Agendamento agendamento) {
        agendamento.setEstado(new EstadoCancelado());
        
    }

    @Override
    public String getNome() {
        return "Em andamento";
    }
}
