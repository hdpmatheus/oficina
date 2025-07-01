package Entidades;

/**
 * Classe que representa uma receita na gestão financeira da oficina mecânica.
 * 
 * <p>Exemplos de receita: pagamento de serviço, venda de peça, etc.</p>
 * 
 * @author Felipe Alcântara Guimarães Veloso
 * @author Matheus Henrique de Paula
 */
public class Receita {

    /** Descrição da origem da receita. */
    private String descricao;

    /** Valor monetário da receita. */
    private double valor;

    /** Data em que a receita foi registrada. */
    private Data data;

    /**
     * Construtor para criar uma nova receita.
     * 
     * @param descricao Descrição da receita.
     * @param valor     Valor da receita.
     * @param data      Data em que a receita ocorreu.
     */
    public Receita(String descricao, double valor, Data data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    /**
     * Retorna a descrição da receita.
     * 
     * @return Descrição textual.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da receita.
     * 
     * @param descricao Nova descrição.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor da receita.
     * 
     * @return Valor em reais.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da receita.
     * 
     * @param valor Novo valor.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna a data da receita.
     * 
     * @return Objeto {@link Data}.
     */
    public Data getData() {
        return data;
    }

    /**
     * Define a data da receita.
     * 
     * @param data Nova data.
     */
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
