package Json;

import Entidades.Produto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária responsável pela persistência dos dados de {@link Produto} no formato JSON.
 * 
 * Utiliza a biblioteca Gson para serializar e desserializar a lista de produtos da oficina,
 * permitindo a gravação e leitura do estoque entre execuções do sistema.
 * 
 * Arquivo utilizado:
 * <ul>
 *     <li>Json/JsonProduto.json</li>
 * </ul>
 * 
 * É utilizada por funcionalidades de controle de estoque dentro do sistema da oficina mecânica.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsonproduto {

    public static final String caminho = "Json/JsonProduto.json";

    /**
     * Salva a lista de produtos no arquivo JSON.
     *
     * @param produtos Lista de produtos a ser salva
     */
    public static void salvarProdutos(List<Produto> produtos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(produtos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("✅ Produtos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar produtos: " + e.getMessage());
        }
    }

    /**
     * Carrega a lista de produtos a partir do arquivo JSON.
     * 
     * Se o arquivo não for encontrado ou ocorrer erro de leitura, retorna uma lista vazia.
     *
     * @return Lista de produtos carregada ou lista vazia se falhar
     */
    public static List<Produto> carregarProdutos() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<ArrayList<Produto>>() {}.getType();
            List<Produto> produtos = gson.fromJson(reader, listType);
            return (produtos != null) ? produtos : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("⚠️ Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
