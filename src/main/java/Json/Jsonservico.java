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

/**
 * Classe responsável pela serialização e desserialização de objetos {@link Servico}
 * para e a partir de arquivos JSON.
 *
 * <p>Utiliza a biblioteca Gson para conversão entre objetos Java e JSON.</p>
 *
 * <p>Os serviços são armazenados no arquivo {@code Json/JsonServico.json}.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Jsonservico {

    /** Caminho padrão do arquivo JSON onde os serviços são armazenados. */
    public static final String caminho = "Json/JsonServico.json";

    /**
     * Salva uma lista de serviços em formato JSON no caminho especificado.
     *
     * @param servicos Lista de serviços a ser salva.
     */
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

    /**
     * Carrega a lista de serviços a partir do arquivo JSON especificado.
     *
     * @return Lista de serviços carregada, ou uma lista vazia se ocorrer erro.
     */
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
