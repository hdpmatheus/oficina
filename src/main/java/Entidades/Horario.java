/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class Horario {
    private int hora;
    private int minuto;
    private int segundo;

    // Construtor
    public Horario(int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    // Métodos get
    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    // Método para formatar a hora como string
    public String formatar() {
        return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }

    @Override
    public String toString() {
        return formatar();
    }
}
