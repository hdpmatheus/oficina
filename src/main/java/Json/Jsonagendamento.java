/**
 * Classe responsável pela serialização e desserialização dos agendamentos em formato JSON.
 * Utiliza a biblioteca Gson para salvar e carregar a lista de agendamentos no sistema da oficina.
 * Após carregar, corrige o estado interno e associa elevadores aos agendamentos conforme necessário.
 * 
 * @author Felipe Alcantara Guimaraes Veloso
 * @author Matheus Henrique de Paula
 */
package Json;

import oficina.SistemaCentral;
import Entidades.Agendamento;
import Entidades.Elevador;
import State.EstadoAgendado; 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Jsonagendamento {
    /** Caminho do arquivo JSON onde os agendamentos são armazenados */
    public static final String caminho = "Json/JsonAgendamento.json";

    /**
     * Salva a lista de agendamentos no arquivo JSON.
     * 
     * @param agendamentos Lista de objetos Agendamento a serem salvos.
     */
    public static void salvarAgendamento(List<Agendamento> agendamentos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(agendamentos);

        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(json);
            System.out.println("Agendamentos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar Agendamento!");
            e.printStackTrace();
        }
    }

    /**
     * Carrega a lista de agendamentos do arquivo JSON. Caso algum agendamento
     * não tenha estado definido, ele será atribuído como "Agendado". Também
     * reatribui os elevadores com base no ID salvo.
     * 
     * @return Lista de agendamentos carregados ou lista vazia em caso de erro.
     */
    public static List<Agendamento> carregarAgendamentos() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(caminho)) {
            Type tipoLista = new TypeToken<List<Agendamento>>() {}.getType();
            List<Agendamento> agendamentos = gson.fromJson(reader, tipoLista);

            // Corrige estados e elevações após desserialização
            for (Agendamento ag : agendamentos) {
                if (ag.getEstadoInterno() == null) {
                    ag.setEstado(new EstadoAgendado());
                }

                // ⚙️ Atribuir referência ao elevador (usando SistemaCentral)
                Elevador elevador = SistemaCentral.getElevadorById(ag.getIdElevador());
                ag.setElevador(elevador);
            }

            return agendamentos;
        } catch (IOException e) {
            System.err.println("Nenhum agendamento carregado (arquivo nao encontrado ou vazio).");
            return new ArrayList<>();
        }
    }
}
