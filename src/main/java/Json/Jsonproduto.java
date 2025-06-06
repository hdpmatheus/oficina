package Json;

import Entidades.Produto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsonproduto {
    public static final String caminho = "Json/JsonProduto.json";

    public static void salvarProduto(List<Produto> produtos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(produtos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Produtos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Produto!");
            e.printStackTrace();
        }
    }
}