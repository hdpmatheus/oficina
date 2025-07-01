package Entidades;

/**
 * Representa uma despesa realizada na oficina mecânica,
 * contendo informações sobre tipo, valor, descrição e data da despesa.
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Despesa {
    
    private String tipo;
    private double valor;
    private String descricao;
    private Data dataDespesa;

    /**
     * Construtor para criar uma despesa com tipo, valor, descrição e data.
     * 
     * @param tipo Tipo da despesa (ex: "Material", "Serviço", etc.)
     * @param valor Valor monetário da despesa
     * @param descricao Descrição detalhada da despesa
     * @param dataDespesa Data em que a despesa foi realizada
     */
    public Despesa(String tipo, double valor, String descricao, Data dataDespesa) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataDespesa = dataDespesa;
    }

    /**
     * Retorna o tipo da despesa.
     * 
     * @return Tipo da despesa
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna o valor da despesa.
     * 
     * @return Valor da despesa
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna a descrição da despesa.
     * 
     * @return Descrição da despesa
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a data da despesa.
     * 
     * @return Data da despesa
     */
    public Data getDataDespesa() {
        return dataDespesa;
    }

    /**
     * Define a data da despesa.
     * 
     * @param dataDespesa Nova data da despesa
     */
    public void setDataDespesa(Data dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    /**
     * Retorna uma representação em string da despesa.
     * 
     * @return String com informações da despesa
     */
    @Override
    public String toString() {
        return "Despesa{" + "tipo=" + tipo + ", valor=" + valor + ", descricao=" + descricao + ", dataDespesa=" + dataDespesa + '}';
    }
}
