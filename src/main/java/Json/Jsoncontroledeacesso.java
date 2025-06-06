package Json;

import Entidades.Acesso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsoncontroledeacesso {
    public static final String caminho = "Json/JsonControleDeAcesso.json";

    public static void salvarAcessos(List<Acesso> acessos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(acessos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Registros de acesso salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar registros de acesso!");
            e.printStackTrace();
        }
    }
}