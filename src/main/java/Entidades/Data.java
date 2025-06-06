
package Entidades;

/**
 *
 * @author Carlo
 */
public class Data {
    
    private int dia;
    private int mes;
    private int ano;

    // Construtor
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // Métodos get
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    // Método para formatar a data como string
    public String formatar() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    @Override
    public String toString() {
        return formatar();
    }
}
    
