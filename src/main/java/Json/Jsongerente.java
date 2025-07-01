package Json;

import Entidades.Gerente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar os dados de gerentes em formato JSON.
 * Utiliza a biblioteca Gson para conversão entre objetos Java e JSON.
 *
 * <p>Os dados são armazenados no caminho {@code Json/JsonGerente.json}.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Jsongerente {

    /** Caminho para o arquivo JSON onde os gerentes são salvos. */
    public static final String caminho = "Json/JsonGerente.json";

    /**
     * Salva uma lista de objetos {@link Gerente} no arquivo JSON definido.
     *
     * @param gerentes Lista de gerentes a serem salvos.
     */
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
