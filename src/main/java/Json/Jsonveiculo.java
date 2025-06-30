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

public class Jsonveiculo {

    public static final String caminho = "Json/JsonVeiculo.json";

    // Método para salvar veículos no arquivo JSON
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

    // Método para ler veículos do arquivo JSON e atualizar contadores
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
