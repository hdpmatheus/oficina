package Json;

import Entidades.Venda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe utilitária responsável pela persistência dos dados da entidade {@link Venda} no formato JSON.
 * 
 * Utiliza a biblioteca Gson para serializar a lista de vendas e armazená-la em um arquivo.
 * Atualmente, a classe possui apenas a funcionalidade de salvar os dados.
 * 
 * Arquivo utilizado:
 * <ul>
 *     <li>Json/JsonVenda.json</li>
 * </ul>
 * 
 * Esta funcionalidade é usada para preservar o histórico de vendas realizadas na oficina.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Jsonvenda {

    public static final String caminho = "Json/JsonVenda.json";

    /**
     * Salva a lista de vendas no arquivo JSON.
     *
     * @param vendas Lista de vendas a ser salva
     */
    public static void salvarVendas(List<Venda> vendas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(vendas);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Vendas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Vendas!");
            e.printStackTrace();
        }
    }
}
