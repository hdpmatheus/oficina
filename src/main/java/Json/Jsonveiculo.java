package Json;

import Entidades.Veiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária responsável pela persistência de dados da entidade {@link Veiculo} no formato JSON.
 * 
 * Utiliza a biblioteca Gson para serializar e desserializar listas de veículos,
 * permitindo o armazenamento e recuperação dos dados de forma estruturada.
 * 
 * Além disso, após a leitura dos dados, a classe atualiza os contadores de veículos
 * encapsulados e protegidos na classe {@link Veiculo}.
 * 
 * Arquivo utilizado:
 * <ul>
 *     <li>Json/JsonVeiculo.json</li>
 * </ul>
 * 
 * Esta classe é usada pelo sistema da oficina mecânica para manter o histórico
 * de veículos cadastrados entre sessões do sistema.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsonveiculo {

    public static final String caminho = "Json/JsonVeiculo.json";

    /**
     * Salva a lista de veículos no arquivo JSON.
     *
     * @param veiculos Lista de veículos a ser salva
     */
    public static void salvarVeiculo(List<Veiculo> veiculos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(veiculos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Veículos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Veículo!");
            e.printStackTrace();
        }
    }

    /**
     * Lê a lista de veículos do arquivo JSON e atualiza os contadores da classe {@link Veiculo}.
     * 
     * Se o arquivo estiver ausente ou ocorrer erro de leitura, uma lista vazia será retornada.
     *
     * @return Lista de veículos carregada ou uma lista vazia se houver falha
     */
    public static List<Veiculo> lerVeiculo() {
        List<Veiculo> veiculos = new ArrayList<>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(caminho)) {
            veiculos = gson.fromJson(reader, new TypeToken<List<Veiculo>>(){}.getType());

            // Atualiza os contadores após a leitura
            Veiculo.setContadorVeiculosEncapsulado(veiculos.size());
            Veiculo.setContadorVeiculosProtegido(veiculos.size());

        } catch (IOException e) {
            System.err.println("Erro ao ler Veículos!");
            e.printStackTrace();
        }

        return veiculos;
    }
}
