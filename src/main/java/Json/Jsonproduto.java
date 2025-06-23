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

public class Jsonproduto {
    public static final String caminho = "Json/JsonProduto.json";

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
