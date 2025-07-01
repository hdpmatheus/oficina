package Json;

import Entidades.Elevador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsável por salvar e carregar os dados dos elevadores do sistema
 * utilizando arquivos JSON. Utiliza a biblioteca Gson para serialização e desserialização.
 *
 * <p>Os dados são persistidos no arquivo {@code Json/Elevadores.json}.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Jsonelevador {

    /** Caminho para o arquivo JSON que armazena os elevadores. */
    private static final String CAMINHO = "Json/Elevadores.json";

    /**
     * Salva o array de elevadores em um arquivo JSON.
     *
     * @param elevadores Array de objetos {@link Elevador} a serem salvos.
     */
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

    /**
     * Carrega os dados dos elevadores a partir do arquivo JSON.
     *
     * @return Um array de {@link Elevador} carregado do arquivo, ou {@code null} em caso de erro.
     */
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
