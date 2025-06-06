package Json;

import Entidades.Despesa;
import Entidades.Receita;
import Controler.BalancoMensal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Jsonbalancomensal {
    private static final String CAMINHO_RECEITAS = "Json/Receitas.json";
    private static final String CAMINHO_DESPESAS = "Json/Despesas.json";

    public static void salvarReceitas(List<Receita> receitas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO_RECEITAS)) {
            gson.toJson(receitas, writer);
            System.out.println("Receitas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar receitas.");
            e.printStackTrace();
        }
    }

    public static void salvarDespesas(List<Despesa> despesas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO_DESPESAS)) {
            gson.toJson(despesas, writer);
            System.out.println("Despesas salvas com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar despesas.");
            e.printStackTrace();
        }
    }
    public static void salvarBalanco(BalancoMensal balanco) {
        salvarReceitas(balanco.getReceitas());
        salvarDespesas(balanco.getDespesas());
}


    public static List<Receita> carregarReceitas() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO_RECEITAS)) {
            Type tipoLista = new TypeToken<List<Receita>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            //System.err.println("Nenhuma receita carregada (arquivo não encontrado ou vazio).");
            return new ArrayList<>();
        }
    }

    public static List<Despesa> carregarDespesas() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CAMINHO_DESPESAS)) {
            Type tipoLista = new TypeToken<List<Despesa>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            //System.err.println("Nenhuma despesa carregada (arquivo não encontrado ou vazio).");
            return new ArrayList<>();
        }
    }
}
