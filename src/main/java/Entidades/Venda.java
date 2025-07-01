package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma venda realizada na oficina.
 * Pode conter serviços e/ou produtos comprados por um cliente.
 *
 * <p>A classe permite adicionar serviços como produtos e realiza
 * automaticamente o cálculo do valor total da venda.</p>
 *
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Venda {

    /** Cliente que realizou a compra. */
    private Cliente cliente;

    /** Lista de produtos/serviços adquiridos. */
    private List<Produto> produtos;

    /** Valor total da venda. */
    private double valorTotal;

    /** Data da venda. */
    private Data dataVenda;

    /**
     * Construtor da venda.
     *
     * @param cliente Cliente que está realizando a venda.
     */
    public Venda(Cliente cliente) {
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
        this.valorTotal = 0.0;
        this.dataVenda = new Data(); // Data atual
    }

    /**
     * Adiciona um serviço à venda como um item de produto.
     *
     * @param servico Serviço a ser adicionado.
     */
    public void adicionarItem(Servico servico) {
        Produto item = new Produto(0, servico.getTipoServico(), 1, servico.getPreco());
        this.produtos.add(item);
        this.valorTotal += item.getPreco();
    }

    /**
     * Adiciona um produto diretamente à venda.
     *
     * @param produto Produto a ser adicionado.
     */
    public void adicionarItem(Produto produto) {
        this.produtos.add(produto);
        this.valorTotal += produto.getPreco();
    }

    /**
     * Retorna o cliente da venda.
     *
     * @return Cliente associado.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Retorna a lista de produtos/serviços da venda.
     *
     * @return Lista de itens.
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Retorna o valor total da venda.
     *
     * @return Valor em reais.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Retorna a data da venda.
     *
     * @return Data no formato {@link Data}.
     */
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
