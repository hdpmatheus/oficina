package Entidades;

/**
 * Representa um elevador da oficina mecânica, que pode ser exclusivo para alinhamento
 * ou para uso geral, e pode estar ocupado ou livre.
 * <p>
 * Também mantém referência ao veículo que está atualmente no elevador.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Elevador {
    private int id;
    private boolean exclusivoAlinhamento;
    private boolean ocupado;
    private Veiculo veiculoAtual; // Veículo atualmente no elevador

    /**
     * Construtor para criar um elevador com ID e indicação se é exclusivo para alinhamento.
     * Inicialmente o elevador está livre e sem veículo associado.
     * 
     * @param id Identificador único do elevador
     * @param exclusivoAlinhamento Indica se o elevador é exclusivo para alinhamento
     */
    public Elevador(int id, boolean exclusivoAlinhamento) {
        this.id = id;
        this.exclusivoAlinhamento = exclusivoAlinhamento;
        this.ocupado = false;
        this.veiculoAtual = null;
    }

    /**
     * Retorna o ID do elevador.
     * 
     * @return ID do elevador
     */
    public int getId() {
        return id;
    }

    /**
     * Indica se o elevador está ocupado.
     * 
     * @return true se ocupado, false caso contrário
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Indica se o elevador é exclusivo para alinhamento.
     * 
     * @return true se exclusivo para alinhamento, false caso contrário
     */
    public boolean isExclusivoAlinhamento() {
        return exclusivoAlinhamento;
    }

    /**
     * Retorna o veículo que está atualmente no elevador.
     * 
     * @return Veículo atualmente no elevador, ou null se estiver vazio
     */
    public Veiculo getVeiculoAtual() {
        return this.veiculoAtual;
    }

    /**
     * Define o veículo que está atualmente no elevador.
     * 
     * @param veiculo Veículo a ser associado ao elevador
     */
    public void setVeiculoAtual(Veiculo veiculo){
        this.veiculoAtual = veiculo;
    } 

    /**
     * Reserva o elevador se ele não estiver ocupado.
     * 
     * @return true se a reserva foi feita com sucesso, false se já estava ocupado
     */
    public boolean reservar() {
        if (!ocupado) {
            ocupado = true;
            return true;
        }
        return false;
    }

    /**
     * Libera o elevador, marcando-o como livre e removendo o veículo associado.
     */
    public void liberar() {
        ocupado = false;
        veiculoAtual = null; // Libera o veículo também
    }

    /**
     * Retorna uma representação em string do elevador, mostrando ID, tipo, status e veículo atual.
     * 
     * @return String com informações do elevador
     */
    @Override
    public String toString() {
        String status = ocupado ? "Ocupado" : "Livre";
        String tipo = exclusivoAlinhamento ? "Alinhamento" : "Normal";
        String veiculoInfo = (veiculoAtual != null)
            ? " | Veículo: " + veiculoAtual.getPlaca()
            : "";
        return "Elevador " + id + " | " + tipo + " | " + status + veiculoInfo;
    }
}
