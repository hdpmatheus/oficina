package Entidades;


public class Despesa {
    
    private String tipo;
    private double valor;
    private String descricao;
    private Data dataDespesa;

    public Despesa(String tipo, double valor, String descricao, Data dataDespesa) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataDespesa = dataDespesa;
    }



    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Data getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Data dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    @Override
    public String toString() {
        return "Despesa{" + "tipo=" + tipo + ", valor=" + valor + ", descricao=" + descricao + ", dataDespesa=" + dataDespesa + '}';
    }
    
    
}
    

