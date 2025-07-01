package Entidades;

/**
 * Representa um horário composto por hora, minuto e segundo.
 * <p>
 * Permite criação, acesso aos seus componentes e formatação para string.
 * </p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class Horario {
    private int hora;
    private int minuto;
    private int segundo;

    /**
     * Construtor que inicializa o horário com hora, minuto e segundo especificados.
     * 
     * @param hora Hora do dia (0-23)
     * @param minuto Minuto (0-59)
     * @param segundo Segundo (0-59)
     */
    public Horario(int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    /**
     * Retorna a hora do horário.
     * 
     * @return Hora (0-23)
     */
    public int getHora() {
        return hora;
    }

    /**
     * Retorna o minuto do horário.
     * 
     * @return Minuto (0-59)
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Retorna o segundo do horário.
     * 
     * @return Segundo (0-59)
     */
    public int getSegundo() {
        return segundo;
    }

    /**
     * Retorna o horário formatado como string no padrão "HH:mm:ss".
     * 
     * @return String formatada representando o horário
     */
    public String formatar() {
        return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }

    /**
     * Retorna a representação em string do horário, equivalente ao método formatar().
     * 
     * @return String formatada do horário
     */
    @Override
    public String toString() {
        return formatar();
    }
}
