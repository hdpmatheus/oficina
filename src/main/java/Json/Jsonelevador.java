package Json;

import Entidades.Elevador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Jsonelevador {

    private static final String CAMINHO = "Json/Elevadores.json";

    public static void salvarElevadores(Elevador[] elevadores) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO)) {
            gson.toJson(elevadores, writer);
            System.out.println("✅ Elevadores salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar elevadores.");
            e.printStackTrace();
        }
    }

    public static Elevador[] carregarElevadores() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO)) {
            return gson.fromJson(reader, Elevador[].class);
        } catch (IOException e) {
            System.err.println("⚠️ Nenhum elevador carregado (arquivo não encontrado ou vazio).");
            return null;
        }
    }
}
