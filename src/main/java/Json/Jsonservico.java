package Json;

import Entidades.Servico;
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

public class Jsonservico {
    public static final String caminho = "Json/JsonServico.json";

    // ✅ Salvar serviços
    public static void salvarServico(List<Servico> servicos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(servicos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("✅ Serviços salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar serviços: " + e.getMessage());
        }
    }

    // ✅ Carregar serviços
    public static List<Servico> carregarServico() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<ArrayList<Servico>>(){}.getType();
            List<Servico> servicos = gson.fromJson(reader, listType);
            return (servicos != null) ? servicos : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("⚠️ Erro ao carregar serviços: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
