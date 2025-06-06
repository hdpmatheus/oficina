package Json;

import Entidades.Veiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsonveiculo {
    public static final String caminho = "Json/JsonVeiculo.json";

    public static void salvarVeiculo(List<Veiculo> veiculos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(veiculos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Veículos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Veículo!");
            e.printStackTrace();
        }
    }
}