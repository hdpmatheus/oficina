package Entidades;

import java.time.LocalDate;

public class Data {

    private int dia;
    private int mes;
    private int ano;

    // Construtor manual
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // ✅ Novo construtor com data atual
    public Data() {
        LocalDate hoje = LocalDate.now();
        this.dia = hoje.getDayOfMonth();
        this.mes = hoje.getMonthValue();
        this.ano = hoje.getYear();
    }

    // Getters
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    // Formatar como string
    public String formatar() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    @Override
    public String toString() {
        return formatar();
    }
}
