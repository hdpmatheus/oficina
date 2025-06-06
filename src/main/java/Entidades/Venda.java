package Entidades;

/**
 * Classe que representa uma venda realizada para um cliente da oficina mecânica.
 */
public class Venda {
    private int idCliente;
    private int idProduto;
    private int quantidade;
    private Data dataVenda; // Data da venda

    // Construtor da classe Venda
    public Venda(int idCliente, int idProduto, int quantidade, Data dataVenda) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
    }

    // Métodos get
    public int getIdCliente() {
        return this.idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Data getDataVenda() {
        return dataVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idCliente=" + idCliente +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", dataVenda=" + dataVenda +
                '}';
    }
}
