package Entidades;

public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String tipo;
    private String cor;
    private Cliente cliente;

    // Estratégia 1: Encapsulada (private static + getter/setter)
    private static int contadorVeiculosEncapsulado = 0;

    public static int getContadorVeiculosEncapsulado() {
        return contadorVeiculosEncapsulado;
    }

    public static void incrementarContadorEncapsulado() {
        contadorVeiculosEncapsulado++;
    }

    // Estratégia 2: Protegida (protected static direto)
    protected static int contadorVeiculosProtegido = 0;

    public Veiculo(String placa, String modelo, int ano, String marca, String tipo, String cor, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.tipo = tipo;
        this.cor = cor;
        this.cliente = cliente;

        // Incrementa ambos os contadores
        contadorVeiculosEncapsulado++;
        contadorVeiculosProtegido++;
    }

    // Getters e setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", tipo='" + tipo + '\'' +
                ", cor='" + cor + '\'' +
                ", cliente=" + cliente.getNome() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return placa != null && placa.equalsIgnoreCase(veiculo.getPlaca());
    }

    @Override
    public int hashCode() {
        return placa != null ? placa.toLowerCase().hashCode() : 0;
    }
    
    public static int getContadorVeiculosProtegido() {
    return contadorVeiculosProtegido;
}
}
