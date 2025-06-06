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
    public static final String caminho = "Json/JsonAgendamento.json";

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
