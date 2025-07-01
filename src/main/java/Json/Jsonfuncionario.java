package Json;

import Entidades.Funcionario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe utilitária responsável pela persistência de dados de {@link Funcionario} no formato JSON.
 * 
 * Utiliza a biblioteca Gson para serializar e salvar a lista de funcionários em um arquivo.
 * Essa funcionalidade garante que os dados dos funcionários sejam preservados entre execuções do sistema.
 * 
 * Arquivo utilizado:
 * <ul>
 *     <li>Json/JsonFuncionario.json</li>
 * </ul>
 * 
 * Esta classe não possui método de leitura, apenas de gravação dos dados no momento atual.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsonfuncionario {

    public static final String caminho = "Json/JsonFuncionario.json";

    /**
     * Salva a lista de funcionários no arquivo JSON especificado.
     *
     * @param funcionarios Lista de funcionários a ser salva
     */
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
