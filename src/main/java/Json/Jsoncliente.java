package Json;

import Entidades.Cliente;
import Entidades.Veiculo;
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
 * Classe utilitária responsável pela persistência de dados de {@link Cliente} no formato JSON.
 * 
 * Esta classe utiliza a biblioteca Gson para salvar e carregar a lista de clientes e seus veículos.
 * Ao salvar, evita-se a referência circular entre cliente e veículo, removendo temporariamente
 * a associação do veículo ao cliente.
 * 
 * Arquivo utilizado:
 * <ul>
 *     <li>Json/JsonCliente.json</li>
 * </ul>
 * 
 * É usada pelo sistema da oficina para manter os dados dos clientes entre execuções.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsoncliente {

    public static final String caminho = "Json/JsonCliente.json";

    /**
     * Salva a lista de clientes no arquivo JSON.
     * 
     * Antes da serialização, a referência do veículo ao cliente é removida
     * para evitar ciclos de serialização que causariam erro na conversão para JSON.
     *
     * @param clientes Lista de clientes a ser salva
     */
    public static void salvarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            for (Veiculo veiculo : cliente.getVeiculos()) {
                veiculo.setCliente(null); // Evita referência circular
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientes);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("✅ Clientes salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar clientes: " + e.getMessage());
        }
    }

    /**
     * Carrega a lista de clientes do arquivo JSON.
     * 
     * Se o arquivo não existir, estiver vazio ou ocorrer erro de leitura,
     * uma lista vazia será retornada.
     *
     * @return Lista de clientes carregada ou uma lista vazia se falhar
     */
    public static List<Cliente> carregarClientes() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            List<Cliente> clientes = gson.fromJson(reader, listType);

            return (clientes != null) ? clientes : new ArrayList<>();

        } catch (IOException e) {
            System.err.println("⚠️ Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
