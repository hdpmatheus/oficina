package Entidades;

public class Servico {
    private String tipoServico;
    private int idServico;
    private double preco;

    public Servico(String tipoServico, int idServico, double preco) {
        this.tipoServico = tipoServico;
        this.idServico = idServico;
        this.preco = preco;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "tipoServico='" + tipoServico + '\'' +
                ", idServico=" + idServico +
                ", preco=" + preco +
                '}';
    }
}
