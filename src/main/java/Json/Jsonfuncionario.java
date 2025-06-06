package Json;

import Entidades.Funcionario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Jsonfuncionario {
    public static final String caminho = "Json/JsonFuncionario.json";

    public static void salvarFuncionario(List<Funcionario> funcionarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(funcionarios);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Funcionários salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Funcionários!");
            e.printStackTrace();
        }
    }
}