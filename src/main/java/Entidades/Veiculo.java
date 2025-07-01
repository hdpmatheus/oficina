package Entidades;

/**
 * Representa um veículo cadastrado no sistema da oficina mecânica.
 * 
 * Cada veículo possui dados como placa, modelo, marca, tipo, cor e ano, além de uma
 * referência ao cliente proprietário. A classe também implementa duas estratégias de
 * contagem total de veículos:
 * 
 * <ul>
 *     <li><b>Encapsulada:</b> Através de atributo {@code private static} com métodos getter e setter.</li>
 *     <li><b>Protegida (simulada):</b> Acesso direto via atributo {@code private static} e atualização direta.</li>
 * </ul>
 * 
 * A contagem é incrementada automaticamente sempre que um novo veículo é instanciado.
 * A classe também redefine os métodos {@code toString()}, {@code equals()} e {@code hashCode()} com base na placa.
 * 
 * Essa entidade é usada no sistema para associar veículos a clientes e agendamentos.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Veiculo {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String tipo;
    private String cor;
    private Cliente cliente;

    // Estratégia 1: Encapsulada
    private static int contadorVeiculosEncapsulado = 0;

    /**
     * Retorna o valor do contador de veículos usando a estratégia encapsulada.
     *
     * @return número de veículos registrados
     */
    public static int getContadorVeiculosEncapsulado() {
        return contadorVeiculosEncapsulado;
    }

    /**
     * Define o valor do contador de veículos usando a estratégia encapsulada.
     *
     * @param valor novo valor do contador
     */
    public static void setContadorVeiculosEncapsulado(int valor) {
        contadorVeiculosEncapsulado = valor;
    }

    /**
     * Incrementa o contador de veículos (encapsulado).
     */
    public static void incrementarContadorEncapsulado() {
        contadorVeiculosEncapsulado++;
    }

    // Estratégia 2: Protegida (simulada)
    private static int contadorVeiculosProtegido = 0;

    /**
     * Retorna o valor do contador de veículos da estratégia protegida.
     *
     * @return número de veículos registrados
     */
    public static int getContadorVeiculosProtegido() {
        return contadorVeiculosProtegido;
    }

    /**
     * Define o valor do contador da estratégia protegida.
     *
     * @param valor novo valor do contador
     */
    public static void setContadorVeiculosProtegido(int valor) {
        contadorVeiculosProtegido = valor;
    }

    /**
     * Construtor da classe Veiculo.
     * 
     * Ao instanciar um novo veículo, ambos os contadores são automaticamente incrementados.
     *
     * @param placa Placa do veículo
     * @param modelo Modelo do veículo
     * @param ano Ano de fabricação
     * @param marca Marca do veículo
     * @param tipo Tipo (ex: passeio, utilitário)
     * @param cor Cor do veículo
     * @param cliente Cliente proprietário do veículo
     */
    public Veiculo(String placa, String modelo, int ano, String marca, String tipo, String cor, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.tipo = tipo;
        this.cor = cor;
        this.cliente = cliente;

        incrementarContadorEncapsulado();
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

    /**
     * Retorna uma representação textual do veículo, incluindo o nome do cliente, se presente.
     *
     * @return String com os dados do veículo
     */
    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", tipo='" + tipo + '\'' +
                ", cor='" + cor + '\'' +
                ", cliente=" + (cliente != null ? cliente.getNome() : "N/A") +
                '}';
    }

    /**
     * Compara dois veículos com base em sua placa (ignora maiúsculas/minúsculas).
     *
     * @param obj Objeto a ser comparado
     * @return true se as placas forem iguais
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return placa != null && placa.equalsIgnoreCase(veiculo.getPlaca());
    }

    /**
     * Gera o código hash com base na placa.
     *
     * @return Código hash do veículo
     */
    @Override
    public int hashCode() {
        return placa != null ? placa.toLowerCase().hashCode() : 0;
    }
}
