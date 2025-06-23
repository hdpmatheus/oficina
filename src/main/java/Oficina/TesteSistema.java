package Oficina;

import Comparator.ClienteCpfComparator;
import Comparator.ClienteIdComparator;
import Entidades.Cliente;
import Entidades.Servico;
import oficina.SistemaCentral;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TesteSistema {
    public static void main(String[] args) {
        SistemaCentral central = new SistemaCentral();

        // Carrega dados padrão
        central.carregarEstoquePadrao();
        central.cadastrarServicosPadrao();
        central.getGerenciarCliente().carregarClienteDoArquivo();


        List<Cliente> clientes = central.getGerenciarCliente().getClientes();

        System.out.println("=== Questão 15: Iterator e Foreach com Clientes ===");

        // Usando Iterator
        System.out.println("\n>> Usando Iterator:");
        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente c = iterator.next();
            System.out.println("Cliente ID: " + c.getIdCliente() + ", Nome: " + c.getNome());
        }

        // Usando Foreach
        System.out.println("\n>> Usando Foreach:");
        for (Cliente c : clientes) {
            System.out.println("Cliente ID: " + c.getIdCliente() + ", Nome: " + c.getNome());
        }

        // Explicação
        System.out.println("\n>> Explicação:");
        System.out.println("O Iterator percorre a lista manualmente com hasNext() e next().");
        System.out.println("O Foreach é uma forma mais simples que também usa Iterator internamente.");

        System.out.println("\n=== Questão 16: Comparator com sort ===");

        // Ordenar por CPF
        Collections.sort(clientes, new ClienteCpfComparator());
        System.out.println("\n>> Ordenado por CPF:");
        for (Cliente c : clientes) {
            System.out.println("Cliente ID: " + c.getIdCliente() + ", CPF: " + c.getCpf() + ", Nome: " + c.getNome());
        }

        // Ordenar por ID
        Collections.sort(clientes, new ClienteIdComparator());
        System.out.println("\n>> Ordenado por ID:");
        for (Cliente c : clientes) {
            System.out.println("Cliente ID: " + c.getIdCliente() + ", CPF: " + c.getCpf() + ", Nome: " + c.getNome());
        }

        System.out.println("\n=== Questão 17: find com Iterator + Comparator e binarySearch ===");

        // Preparar comparator
        ClienteCpfComparator comparator = new ClienteCpfComparator();

        // Ordenar a lista antes do binarySearch
        Collections.sort(clientes, comparator);

        // Cliente chave a buscar (ajuste o CPF conforme necessário)
        Cliente chave = new Cliente(0, 100022, "", "", 0);

        // Buscar com método find
        Cliente encontradoViaFind = central.getGerenciarCliente().findCliente(clientes, chave, comparator);
        if (encontradoViaFind != null) {
            System.out.println("\n>> Encontrado via find(): " + encontradoViaFind.getNome());
        } else {
            System.out.println("\n>> Cliente não encontrado via find()");
        }

        // Buscar com binarySearch
        int index = Collections.binarySearch(clientes, chave, comparator);
        if (index >= 0) {
            Cliente encontradoViaBinary = clientes.get(index);
            System.out.println(">> Encontrado via binarySearch(): " + encontradoViaBinary.getNome());
        } else {
            System.out.println(">> Cliente não encontrado via binarySearch()");
        }

        // Explicação
        System.out.println("\n>> Explicação:");
        System.out.println("find percorre a lista com Iterator e compara com o Comparator.");
        System.out.println("binarySearch faz uma busca binária, exigindo lista ordenada com o mesmo Comparator.");

        System.out.println("\n=== Questão 18: Simular Atendimento com Nota Fiscal ===");

        if (clientes.isEmpty()) {
            System.out.println("⚠️ Nenhum cliente encontrado no sistema para o teste.");
        } else {
            Cliente cliente = clientes.get(0); // usa o primeiro cliente real
            Servico servico = central.buscarServicoPorIdManual(1); // Troca de óleo e filtro

            central.realizarAtendimentoComNota(cliente, servico);
        }
    }
}
