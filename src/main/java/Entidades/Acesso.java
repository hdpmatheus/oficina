package Entidades;

/**
 * Representa um registro de acesso de um funcionário à oficina mecânica,
 * contendo informações sobre a data, horário e tipo do acesso (entrada ou saída).
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Acesso {
    private int idFuncionario;
    private Data data;
    private Horario horario;
    private String tipo; // "Entrada" ou "Saída"

    /**
     * Construtor que inicializa um registro de acesso com os dados do funcionário,
     * data, horário e tipo do acesso.
     * 
     * @param idFuncionario ID do funcionário que realizou o acesso
     * @param data Data do acesso
     * @param horario Horário do acesso
     * @param tipo Tipo do acesso, podendo ser "Entrada" ou "Saída"
     */
    public Acesso(int idFuncionario, Data data, Horario horario, String tipo) {
        this.idFuncionario = idFuncionario;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
    }

    /**
     * Retorna o ID do funcionário.
     * 
     * @return ID do funcionário
     */
    public int getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Retorna a data do acesso.
     * 
     * @return Data do acesso
     */
    public Data getData() {
        return data;
    }

    /**
     * Retorna o horário do acesso.
     * 
     * @return Horário do acesso
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Retorna o tipo do acesso, que pode ser "Entrada" ou "Saída".
     * 
     * @return Tipo do acesso
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna a data e horário concatenados em uma única string.
     * 
     * @return Data e horário do acesso no formato string
     */
    public String getDataHora() {
        return data.toString() + " " + horario.toString();
    }

    /**
     * Retorna uma representação em string do objeto Acesso.
     * 
     * @return String com os dados do acesso
     */
    @Override
    public String toString() {
        return "Acesso{" + "idFuncionario=" + idFuncionario + ", data=" + data + ", horario=" + horario + ", tipo=" + tipo + '}';
    }
}
