package Entidades;

import java.time.LocalDate;

/**
 * Representa uma data simples composta por dia, mês e ano.
 * <p>
 * Possui construtores para criar datas manualmente ou para obter a data atual do sistema.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Data {

    private int dia;
    private int mes;
    private int ano;

    /**
     * Construtor que cria uma data com dia, mês e ano especificados manualmente.
     * 
     * @param dia Dia do mês (1-31)
     * @param mes Mês do ano (1-12)
     * @param ano Ano (exemplo: 2025)
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Construtor que cria uma data com o dia, mês e ano atuais do sistema.
     */
    public Data() {
        LocalDate hoje = LocalDate.now();
        this.dia = hoje.getDayOfMonth();
        this.mes = hoje.getMonthValue();
        this.ano = hoje.getYear();
    }

    /**
     * Retorna o dia da data.
     * 
     * @return Dia do mês
     */
    public int getDia() {
        return dia;
    }

    /**
     * Retorna o mês da data.
     * 
     * @return Mês do ano
     */
    public int getMes() {
        return mes;
    }

    /**
     * Retorna o ano da data.
     * 
     * @return Ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * Retorna a data formatada no padrão "dd/MM/yyyy".
     * 
     * @return String representando a data formatada
     */
    public String formatar() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    /**
     * Retorna a representação em string da data, equivalente ao método formatar().
     * 
     * @return String com a data formatada
     */
    @Override
    public String toString() {
        return formatar();
    }
}
