package State;

import Entidades.Agendamento;

public interface EstadoAgendamento {
    void avancar(Agendamento agendamento);
    void cancelar(Agendamento agendamento);
    String getNome();
}
