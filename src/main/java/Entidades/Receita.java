package Entidades;

/**
 * Classe que representa uma receita na gestão financeira da oficina mecânica.
 * 
 * Exemplo de receita: pagamento de serviço, venda de peça, etc.
 * 
 */
public class Receita {
    private String descricao; 
    private double valor;
    private Data data;

    public Receita(String descricao, double valor, Data data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }
}
