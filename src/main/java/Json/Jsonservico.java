package Json;

import Entidades.Servico;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsonservico {
    public static final String caminho = "Json/JsonServico.json";

    public static void salvarServico(List<Servico> servicos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(servicos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Serviços salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Serviço!");
            e.printStackTrace();
        }
    }
}