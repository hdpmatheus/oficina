package Entidades;

public class Elevador {
    private int id;
    private boolean exclusivoAlinhamento;
    private boolean ocupado;
    private Veiculo veiculoAtual; // Novo atributo

    public Elevador(int id, boolean exclusivoAlinhamento) {
        this.id = id;
        this.exclusivoAlinhamento = exclusivoAlinhamento;
        this.ocupado = false;
        this.veiculoAtual = null;
    }

    public int getId() {
        return id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public boolean isExclusivoAlinhamento() {
        return exclusivoAlinhamento;
    }

    public Veiculo getVeiculoAtual() {
        return this.veiculoAtual;
    }

    public void setVeiculoAtual(Veiculo veiculo){
        this.veiculoAtual = veiculo;
    } 

    public boolean reservar() {
        if (!ocupado) {
            ocupado = true;
            return true;
        }
        return false;
    }

    public void liberar() {
        ocupado = false;
        veiculoAtual = null; // Libera o veículo também
    }

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
