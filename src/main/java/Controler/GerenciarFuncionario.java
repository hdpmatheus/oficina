package Controler;

import Comparator.FuncionarioIdComparator;
import Entidades.Funcionario;
import Json.Jsonfuncionario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por gerenciar as operações relacionadas aos funcionários da oficina.
 * Inclui métodos para adicionar, buscar, editar e salvar dados de funcionários no sistema.
 * Utiliza persistência em JSON e busca binária com {@link FuncionarioIdComparator}.
 * 
 * @author Felipe Alcântara
 * @author Matheus Henrique
 */
public class GerenciarFuncionario {

    /** Lista que armazena todos os funcionários cadastrados */
    private List<Funcionario> funcionarios = new ArrayList<>();

    /** Construtor padrão */
    public GerenciarFuncionario() {}

    /**
     * Adiciona um novo funcionário à lista.
     * 
     * @param f O funcionário a ser adicionado
     */
    public void criarFuncionario(Funcionario f) {
        this.funcionarios.add(f);
    }

    /**
     * Salva todos os funcionários cadastrados no arquivo JSON.
     */
    public void salvarFuncionario() {
        Jsonfuncionario.salvarFuncionario(funcionarios);
    }

    /**
     * Busca um funcionário pelo seu ID usando busca binária.
     * A lista deve estar ordenada previamente para garantir precisão.
     * 
     * @param id O ID do funcionário a ser buscado
     * @return O funcionário encontrado, ou {@code null} se não for encontrado
     */
    public Funcionario buscarFuncionario(int id) {
        Funcionario funcionarioBusca = new Funcionario(id, 0, null, null, null, 0);
        int index = Collections.binarySearch(this.funcionarios, funcionarioBusca, new FuncionarioIdComparator());

        return (index >= 0) ? this.funcionarios.get(index) : null;
    }

    /**
     * Altera o nome de um funcionário com base no ID informado.
     * 
     * @param id O ID do funcionário
     * @param novoNome O novo nome a ser atribuído
     */
    public void alterarFuncionario(int id, String novoNome) {
        Funcionario funcionario = buscarFuncionario(id);

        if (funcionario != null) {
            funcionario.setNome(novoNome);
            System.out.println("Funcionário alterado com sucesso: " + funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    /**
     * Retorna a lista completa de funcionários cadastrados.
     * 
     * @return Lista de funcionários
     */
    public List<Funcionario> exibirFuncionario() {
        return funcionarios;
    }
}
