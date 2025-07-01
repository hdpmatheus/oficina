package Json;

import Entidades.Despesa;
import Entidades.Receita;
import Controler.BalancoMensal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária responsável pela persistência dos dados financeiros da oficina.
 * 
 * Utiliza a biblioteca Gson para salvar e carregar listas de {@link Receita} e {@link Despesa}
 * em arquivos JSON. Também permite salvar o balanço mensal completo encapsulado em {@link BalancoMensal}.
 * 
 * Arquivos utilizados:
 * <ul>
 *   <li>Json/Receitas.json</li>
 *   <li>Json/Despesas.json</li>
 * </ul>
 * 
 * Esta classe é utilizada principalmente no encerramento e inicialização do sistema para
 * garantir que os dados financeiros sejam preservados.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsonbalancomensal {

    private static final String CAMINHO_RECEITAS = "Json/Receitas.json";
    private static final String CAMINHO_DESPESAS = "Json/Despesas.json";

    /**
     * Salva a lista de receitas no arquivo JSON.
     *
     * @param receitas Lista de receitas a ser salva
     */
    public static void salvarReceitas(List<Receita> receitas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO_RECEITAS)) {
            gson.toJson(receitas, writer);
            System.out.println("Receitas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar receitas.");
            e.printStackTrace();
        }
    }

    /**
     * Salva a lista de despesas no arquivo JSON.
     *
     * @param despesas Lista de despesas a ser salva
     */
    public static void salvarDespesas(List<Despesa> despesas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO_DESPESAS)) {
            gson.toJson(despesas, writer);
            System.out.println("Despesas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar despesas.");
            e.printStackTrace();
        }
    }

    /**
     * Salva todas as receitas e despesas presentes no balanço mensal.
     *
     * @param balanco Objeto contendo as listas de receitas e despesas
     */
    public static void salvarBalanco(BalancoMensal balanco) {
        salvarReceitas(balanco.getReceitas());
        salvarDespesas(balanco.getDespesas());
    }

    /**
     * Carrega a lista de receitas a partir do arquivo JSON.
     *
     * @return Lista de receitas carregada ou lista vazia se o arquivo não existir
     */
    public static List<Receita> carregarReceitas() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO_RECEITAS)) {
            Type tipoLista = new TypeToken<List<Receita>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Carrega a lista de despesas a partir do arquivo JSON.
     *
     * @return Lista de despesas carregada ou lista vazia se o arquivo não existir
     */
    public static List<Despesa> carregarDespesas() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO_DESPESAS)) {
            Type tipoLista = new TypeToken<List<Despesa>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
