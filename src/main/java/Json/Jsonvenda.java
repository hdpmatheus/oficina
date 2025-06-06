package Json;

import Entidades.Venda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsonvenda {
    public static final String caminho = "Json/JsonVenda.json";

    public static void salvarVendas(List<Venda> vendas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(vendas);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Vendas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Vendas!");
            e.printStackTrace();
        }
    }
}