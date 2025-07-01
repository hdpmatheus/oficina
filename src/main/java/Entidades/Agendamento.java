package Entidades;

import State.EstadoAgendamento;
import State.EstadoAgendado;
import State.EstadoConfirmado;
import State.EstadoCancelado;
import State.EstadoEmAndamento;
import State.EstadoFinalizado;

/**
 * Representa um agendamento de serviço na oficina mecânica, com informações sobre datas,
 * cliente, serviço, funcionário, veículo, elevador e o estado atual do agendamento.
 * <p>
 * O estado do agendamento segue o padrão State, permitindo avançar ou cancelar o agendamento
 * de acordo com as regras de negócio.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
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

    /**
     * Construtor para criar um agendamento inicializado com datas, IDs do cliente e serviço,
     * e o funcionário responsável. O estado inicial é "Agendado".
     * 
     * @param dataAgendamento Data do agendamento
     * @param dataConfirmacao Data de confirmação do agendamento
     * @param idCliente ID do cliente que solicitou o serviço
     * @param idServico ID do serviço agendado
     * @param funcionario Funcionário responsável pelo agendamento
     */
    public Agendamento(Data dataAgendamento, Data dataConfirmacao, int idCliente, int idServico, Funcionario funcionario) {
        this.dataAgendamento = dataAgendamento;
        this.dataConfirmacao = dataConfirmacao;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.funcionario = funcionario;
        this.estado = new EstadoAgendado(); // estado inicial padrão
        this.nomeEstadoSalvo = estado.getNome();
    }

    /**
     * Retorna a data do agendamento.
     * 
     * @return Data do agendamento
     */
    public Data getDataAgendamento() {
        return dataAgendamento;
    }

    /**
     * Define a data do agendamento.
     * 
     * @param dataAgendamento Nova data do agendamento
     */
    public void setDataAgendamento(Data dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    /**
     * Retorna a data de confirmação do agendamento.
     * 
     * @return Data de confirmação
     */
    public Data getDataConfirmacao() {
        return dataConfirmacao;
    }

    /**
     * Define a data de confirmação do agendamento.
     * 
     * @param dataConfirmacao Nova data de confirmação
     */
    public void setDataConfirmacao(Data dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    /**
     * Retorna o ID do cliente.
     * 
     * @return ID do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente.
     * 
     * @param idCliente Novo ID do cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna o ID do serviço agendado.
     * 
     * @return ID do serviço
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço agendado.
     * 
     * @param idServico Novo ID do serviço
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Retorna o funcionário responsável pelo agendamento.
     * 
     * @return Funcionário responsável
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável pelo agendamento.
     * 
     * @param funcionario Novo funcionário responsável
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna o nome do estado atual do agendamento.
     * 
     * @return Nome do estado atual
     */
    public String getStatus() {
        return estado.getNome();
    }

    /**
     * Avança o estado do agendamento conforme as regras do padrão State.
     */
    public void avancarEstado() {
        estado.avancar(this);
        nomeEstadoSalvo = estado.getNome();
    }

    /**
     * Cancela o agendamento, alterando seu estado para cancelado se permitido.
     */
    public void cancelar() {
        estado.cancelar(this);
        nomeEstadoSalvo = estado.getNome();
    }

    /**
     * Define o estado interno do agendamento.
     * 
     * @param novoEstado Novo estado a ser atribuído
     */
    public void setEstado(EstadoAgendamento novoEstado) {
        this.estado = novoEstado;
    }

    /**
     * Retorna o estado interno do agendamento.
     * 
     * @return Estado interno
     */
    public EstadoAgendamento getEstadoInterno() {
        return this.estado;
    }

    /**
     * Prepara o agendamento para salvar seu estado atual (normalmente para persistência).
     */
    public void prepararParaSalvar() {
        this.nomeEstadoSalvo = getStatus();
    }

    /**
     * Retorna o elevador associado ao agendamento.
     * 
     * @return Elevador utilizado
     */
    public Elevador getElevador() {
        return elevador;
    }

    /**
     * Define o elevador associado ao agendamento.
     * Atualiza também o ID do elevador.
     * 
     * @param elevador Elevador a ser associado
     */
    public void setElevador(Elevador elevador) {
        this.elevador = elevador;
        this.idElevador = (elevador != null) ? elevador.getId() : -1;
    }

    /**
     * Retorna o ID do elevador associado ao agendamento.
     * 
     * @return ID do elevador
     */
    public int getIdElevador() {
        return (elevador != null) ? elevador.getId() : idElevador;
    }

    /**
     * Define o ID do elevador associado ao agendamento.
     * 
     * @param idElevador Novo ID do elevador
     */
    public void setIdElevador(int idElevador) {
        this.idElevador = idElevador;
    }

    /**
     * Define o veículo associado ao agendamento.
     * 
     * @param veiculo Veículo a ser associado
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Retorna o veículo associado ao agendamento.
     * 
     * @return Veículo associado
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Restaura o estado do agendamento a partir do nome do estado salvo,
     * normalmente usado após desserialização.
     */
    public void restaurarEstado() {
        switch (nomeEstadoSalvo) {
            case "Confirmado" -> this.estado = new EstadoConfirmado();
            case "Em andamento" -> this.estado = new EstadoEmAndamento();
            case "Finalizado" -> this.estado = new EstadoFinalizado();
            case "Cancelado" -> this.estado = new EstadoCancelado();
            default -> this.estado = new EstadoAgendado();
        }
    }

    /**
     * Retorna uma representação em string do agendamento, com dados básicos e status.
     * 
     * @return String com informações do agendamento
     */
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
