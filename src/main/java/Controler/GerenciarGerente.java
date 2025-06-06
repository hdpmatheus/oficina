package Controler;

import Comparator.GerenteIdComparator;
import Entidades.Gerente;
import Json.Jsongerente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por gerenciar os gerentes da oficina.
 * Permite criar, buscar, alterar e salvar informações de gerentes.
 */
public class GerenciarGerente {
    
    private List<Gerente> gerentes; // Lista de gerentes

    // Construtor
    public GerenciarGerente() {
        this.gerentes = new ArrayList<>();
    }

    // Adiciona um novo gerente à lista
    public void criarGerente(Gerente g) {
        this.gerentes.add(g);
    }

    // Salva a lista de gerentes em JSON
    public void salvarGerente() {
        Jsongerente.salvarGerente(gerentes);
    }

    // Busca gerente pelo ID utilizando busca binária
    public Gerente buscarGerente(int id) {
        Gerente gerenteBusca = new Gerente(id, 0, null, null, 0);
        int index = Collections.binarySearch(this.gerentes, gerenteBusca, new GerenteIdComparator());

        return (index >= 0) ? this.gerentes.get(index) : null;
    }

    // Altera o nome de um gerente com base no ID
    public void alterarGerente(int id, String novoNome) {
        Gerente gerente = buscarGerente(id);

        if (gerente != null) {
            gerente.setNome(novoNome);
            System.out.println("Gerente alterado com sucesso: " + gerente);
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }

    // Retorna a lista de gerentes
    public List<Gerente> exibirGerente() {
        return gerentes;
    }
}
