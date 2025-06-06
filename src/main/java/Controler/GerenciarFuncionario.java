package Controler;

import Comparator.FuncionarioIdComparator;
import Entidades.Funcionario;
import Json.Jsonfuncionario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por gerenciar as operações relacionadas aos funcionários da oficina.
 * Inclui métodos para criar, buscar, alterar e salvar informações de funcionários.
 */
public class GerenciarFuncionario {

    private List<Funcionario> funcionarios = new ArrayList<>(); // Lista para armazenar os funcionários

    // Construtor
    public GerenciarFuncionario() {
    }

    // Método para adicionar um novo funcionário à lista
    public void criarFuncionario(Funcionario f) {
        this.funcionarios.add(f);
    }

    // Método para salvar todos os funcionários no arquivo JSON
    public void salvarFuncionario() {
        Jsonfuncionario.salvarFuncionario(funcionarios);
    }

    // Busca funcionário por ID com busca binária
    public Funcionario buscarFuncionario(int id) {
        Funcionario funcionarioBusca = new Funcionario(id, 0, null, null, null, 0);
        int index = Collections.binarySearch(this.funcionarios, funcionarioBusca, new FuncionarioIdComparator());

        return (index >= 0) ? this.funcionarios.get(index) : null;
    }

    // Altera o nome de um funcionário pelo ID
    public void alterarFuncionario(int id, String novoNome) {
        Funcionario funcionario = buscarFuncionario(id);

        if (funcionario != null) {
            funcionario.setNome(novoNome);
            System.out.println("Funcionário alterado com sucesso: " + funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Retorna a lista de funcionários cadastrados
    public List<Funcionario> exibirFuncionario() {
        return funcionarios;
    }
}
