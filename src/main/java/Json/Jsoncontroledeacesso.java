package Json;

import Entidades.Acesso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar registros de acesso do sistema
 * em formato JSON no arquivo especificado.
 *
 * <p>Utiliza a biblioteca Gson para serialização.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Jsoncontroledeacesso {
    /** Caminho do arquivo JSON onde os registros serão salvos. */
    public static final String caminho = "Json/JsonControleDeAcesso.json";

    /**
     * Salva uma lista de objetos {@link Acesso} em formato JSON no arquivo definido.
     *
     * @param acessos Lista de registros de acesso a serem salvos.
     */
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
