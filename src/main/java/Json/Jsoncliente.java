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

public class Jsoncliente {
    public static final String caminho = "Json/JsonCliente.json";

    /**
     * Salva a lista de clientes no arquivo JSON.
     * Remove temporariamente a referência ao cliente nos veículos para evitar ciclo de serialização.
     */
    public static void salvarCliente(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            for (Veiculo veiculo : cliente.getVeiculos()) {
                veiculo.setCliente(null); // quebra referência circular
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientes);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("✅ Clientes salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar Cliente: " + e.getMessage());
        }
    }

    /**
     * Carrega a lista de clientes do arquivo JSON.
     * Se o arquivo não existir ou estiver vazio, retorna uma lista vazia.
     */
    public static List<Cliente> carregarCliente() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            List<Cliente> clientes = gson.fromJson(reader, listType);

            if (clientes == null) {
                System.out.println("⚠️ Nenhum cliente encontrado no arquivo JSON.");
                return new ArrayList<>();
            }

            //System.out.println("✅ Clientes carregados com sucesso!");
            return clientes;

        } catch (IOException e) {
            System.err.println("⚠️ Erro ao carregar Cliente: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
