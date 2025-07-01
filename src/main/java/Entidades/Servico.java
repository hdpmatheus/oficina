package Entidades;

/**
 * Representa um serviço oferecido pela oficina mecânica.
 * 
 * Cada serviço possui um tipo (ex: troca de óleo, alinhamento), um identificador único
 * e um preço associado. Esta entidade é utilizada em agendamentos, controle financeiro e histórico de atendimentos.
 * 
 * A classe fornece métodos de acesso (getters/setters) e sobrescreve {@code toString()} para exibição descritiva.
 * 
 * Exemplo de uso:
 * <pre>
 *     Servico alinhamento = new Servico("Alinhamento", 1, 120.0);
 * </pre>
 * 
 * @author
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Servico {
    private String tipoServico;
    private int idServico;
    private double preco;

    /**
     * Construtor da classe Servico.
     *
     * @param tipoServico Descrição do tipo de serviço
     * @param idServico Identificador único do serviço
     * @param preco Preço cobrado pelo serviço
     */
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

    /**
     * Retorna uma representação textual do serviço, com tipo, ID e preço.
     *
     * @return String com os dados do serviço
     */
    @Override
    public String toString() {
        return "Servico{" +
                "tipoServico='" + tipoServico + '\'' +
                ", idServico=" + idServico +
                ", preco=" + preco +
                '}';
    }
}
