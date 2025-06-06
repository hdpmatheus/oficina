package Entidades;

import State.EstadoAgendamento;
import State.EstadoAgendado;
import State.EstadoConfirmado;
import State.EstadoCancelado;
import State.EstadoEmAndamento;
import State.EstadoFinalizado;

public class Agendamento {
    private Data dataAgendamento;
    private Data dataConfirmacao;
    private int idCliente;
    private int idServico;
    private Funcionario funcionario;
    private Elevador elevador;
    private int idElevador;
    private Veiculo veiculo;

    private transient EstadoAgendamento estado; // Gson ignora esse campo
    private String nomeEstadoSalvo; // usado para salvar/restaurar

    public Agendamento(Data dataAgendamento, Data dataConfirmacao, int idCliente, int idServico, Funcionario funcionario) {
        this.dataAgendamento = dataAgendamento;
        this.dataConfirmacao = dataConfirmacao;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.funcionario = funcionario;
        this.estado = new EstadoAgendado(); // estado inicial padrão
        this.nomeEstadoSalvo = estado.getNome();
    }

    public Data getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Data dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Data getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(Data dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return estado.getNome();
    }

    public void avancarEstado() {
        estado.avancar(this);
        nomeEstadoSalvo = estado.getNome();
    }

    public void cancelar() {
        estado.cancelar(this);
        nomeEstadoSalvo = estado.getNome();
    }

    public void setEstado(EstadoAgendamento novoEstado) {
        this.estado = novoEstado;
    }
    
    public EstadoAgendamento getEstadoInterno() {
        return this.estado;
}

    public void prepararParaSalvar() {
        this.nomeEstadoSalvo = getStatus();
    }
    public Elevador getElevador() {
        return elevador;
    }

    public void setElevador(Elevador elevador) {
        this.elevador = elevador;
        this.idElevador = (elevador != null) ? elevador.getId() : -1;
    }
    
    public int getIdElevador() {
        return (elevador != null) ? elevador.getId() : idElevador;
    }

    public void setIdElevador(int idElevador) {
        this.idElevador = idElevador;
    }
    

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    

    public void restaurarEstado() {
        switch (nomeEstadoSalvo) {
            case "Confirmado" -> this.estado = new EstadoConfirmado();
            case "Em andamento" -> this.estado = new EstadoEmAndamento();
            case "Finalizado" -> this.estado = new EstadoFinalizado();
            case "Cancelado" -> this.estado = new EstadoCancelado();
            default -> this.estado = new EstadoAgendado();
        }
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "dataAgendamento=" + dataAgendamento +
                ", dataConfirmacao=" + dataConfirmacao +
                ", idCliente=" + idCliente +
                ", idServico=" + idServico +
                ", funcionario=" + (funcionario != null ? funcionario.getNome() : "N/A") +
                ", status='" + getStatus() + '\'' +
                '}';
    }
} 
