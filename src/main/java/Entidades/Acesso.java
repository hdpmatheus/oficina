package Entidades;

/**
 * Representa um registro de acesso de um funcionário à oficina mecânica.
 */
public class Acesso {
    private int idFuncionario;
    private Data data;
    private Horario horario;
    private String tipo; // "Entrada" ou "Saída"

    // Construtor
    public Acesso(int idFuncionario, Data data, Horario horario, String tipo) {
        this.idFuncionario = idFuncionario;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
    }

    // Métodos get
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public Data getData() {
        return data;
    }

    public Horario getHorario() {
        return horario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDataHora() {
        return data.toString() + " " + horario.toString();
    }

    @Override
    public String toString() {
        return "Acesso{" + "idFuncionario=" + idFuncionario + ", data=" + data + ", horario=" + horario + ", tipo=" + tipo + '}';
    }
}
