package Controler;

import Comparator.GerenteIdComparator;
import Entidades.Gerente;
import Json.Jsongerente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por gerenciar os dados de {@link Gerente} no sistema da oficina mecânica.
 * 
 * Permite criar, buscar, alterar e salvar informações de gerentes. Utiliza busca binária
 * para localização eficiente por ID e realiza a persistência dos dados por meio da classe {@link Jsongerente}.
 * 
 * Os dados são armazenados em uma lista interna que pode ser consultada e manipulada.
 * 
 * Funcionalidades principais:
 * <ul>
 *     <li>Adicionar novos gerentes</li>
 *     <li>Buscar gerente por ID (usando {@link Collections#binarySearch})</li>
 *     <li>Alterar nome de gerente</li>
 *     <li>Salvar os dados em arquivo JSON</li>
 *     <li>Exibir a lista de gerentes</li>
 * </ul>
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class GerenciarGerente {

    private List<Gerente> gerentes;

    /**
     * Construtor padrão que inicializa a lista de gerentes.
     */
    public GerenciarGerente() {
        this.gerentes = new ArrayList<>();
    }

    /**
     * Adiciona um novo gerente à lista.
     *
     * @param g Gerente a ser adicionado
     */
    public void criarGerente(Gerente g) {
        this.gerentes.add(g);
    }

    /**
     * Salva a lista atual de gerentes no arquivo JSON.
     */
    public void salvarGerente() {
        Jsongerente.salvarGerente(gerentes);
    }

    /**
     * Busca um gerente pelo ID utilizando busca binária com {@link GerenteIdComparator}.
     *
     * @param id ID do gerente a ser buscado
     * @return Gerente correspondente ou null se não encontrado
     */
    public Gerente buscarGerente(int id) {
        Gerente gerenteBusca = new Gerente(id, 0, null, null, 0);
        int index = Collections.binarySearch(this.gerentes, gerenteBusca, new GerenteIdComparator());

        return (index >= 0) ? this.gerentes.get(index) : null;
    }

    /**
     * Altera o nome de um gerente com base em seu ID.
     *
     * @param id ID do gerente
     * @param novoNome Novo nome a ser atribuído
     */
    public void alterarGerente(int id, String novoNome) {
        Gerente gerente = buscarGerente(id);

        if (gerente != null) {
            gerente.setNome(novoNome);
            System.out.println("Gerente alterado com sucesso: " + gerente);
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }

    /**
     * Retorna a lista completa de gerentes cadastrados.
     *
     * @return Lista de gerentes
     */
    public List<Gerente> exibirGerente() {
        return gerentes;
    }
}
