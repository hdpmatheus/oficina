package Controler;

import Entidades.Acesso;
import Entidades.Funcionario;
import Entidades.Data;
import Entidades.Horario;
import Json.Jsoncontroledeacesso;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por registrar e gerenciar os acessos (entrada e saída)
 * dos funcionários na oficina mecânica.
 */
public class ControleDeAcesso {

    private List<Acesso> acessos;
    private GerenciarFuncionario gerenciarFuncionario;

    /**
     * Construtor que recebe o gerenciador de funcionários e inicializa a lista de acessos.
     */
    public ControleDeAcesso(GerenciarFuncionario gerenciarFuncionario) {
        this.acessos = new ArrayList<>();
        this.gerenciarFuncionario = gerenciarFuncionario;
    }

    /**
     * Registra a entrada de um funcionário.
     * 
     * @param idFuncionario ID do funcionário
     * @param dataEntrada   Data da entrada
     * @param horarioEntrada Horário da entrada
     */
    public void registrarEntrada(int idFuncionario, Data dataEntrada, Horario horarioEntrada) {
        Funcionario funcionario = gerenciarFuncionario.buscarFuncionario(idFuncionario);

        if (funcionario != null) {
            Acesso novoAcesso = new Acesso(idFuncionario, dataEntrada, horarioEntrada, "Entrada");
            acessos.add(novoAcesso);
            System.out.println("✅ Entrada registrada: Funcionário " + idFuncionario + " em " + novoAcesso.getDataHora());
            Jsoncontroledeacesso.salvarAcessos(acessos);
        } else {
            System.out.println("❌ Funcionário com ID " + idFuncionario + " não encontrado. Entrada não registrada.");
        }
    }

    /**
     * Registra a saída de um funcionário.
     * 
     * @param idFuncionario ID do funcionário
     * @param dataSaida     Data da saída
     * @param horarioSaida  Horário da saída
     */
    public void registrarSaida(int idFuncionario, Data dataSaida, Horario horarioSaida) {
        Funcionario funcionario = gerenciarFuncionario.buscarFuncionario(idFuncionario);

        if (funcionario != null) {
            Acesso novoAcesso = new Acesso(idFuncionario, dataSaida, horarioSaida, "Saída");
            acessos.add(novoAcesso);
            System.out.println("✅ Saída registrada: Funcionário " + idFuncionario + " em " + novoAcesso.getDataHora());
            Jsoncontroledeacesso.salvarAcessos(acessos);
        } else {
            System.out.println("❌ Funcionário com ID " + idFuncionario + " não encontrado. Saída não registrada.");
        }
    }

    /**
     * Retorna a lista de acessos registrados.
     * 
     * @return Lista de acessos
     */
    public List<Acesso> getAcessos() {
        return acessos;
    }
}
