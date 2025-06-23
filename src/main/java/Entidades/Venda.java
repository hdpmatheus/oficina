package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private List<Produto> produtos;
    private double valorTotal;
    private Data dataVenda;

    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.valorTotal = 0.0;
        this.dataVenda = new Data(); 
    }

    // Adicionar serviço como produto
    public void adicionarItem(Servico servico) {
        Produto item = new Produto(0, servico.getTipoServico(), 1, servico.getPreco());
        this.produtos.add(item);
        this.valorTotal += item.getPreco();
    }

    // Adicionar produto diretamente
    public void adicionarItem(Produto produto) {
        this.produtos.add(produto);
        this.valorTotal += produto.getPreco();
    }

    // Getters
    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Data getDataVenda() {
        return dataVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "cliente=" + cliente.getNome() +
                ", valorTotal=R$" + valorTotal +
                ", data=" + dataVenda +
                ", itens=" + produtos.size() +
                '}';
    }
}
