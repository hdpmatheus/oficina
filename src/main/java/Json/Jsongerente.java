package Json;

import Entidades.Gerente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsongerente {
    public static final String caminho = "Json/JsonGerente.json";

    public static void salvarGerente(List<Gerente> gerentes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(gerentes);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Gerentes salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Gerente!");
            e.printStackTrace();
        }
    }
}