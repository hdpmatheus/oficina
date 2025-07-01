package Adapter;

/**
 * Implementação de pagamento via Pix usando um sistema externo,
 * adaptado para o contexto de uma oficina mecânica.
 * 
 * <p>Integra um {@link SistemaExterno} com o sistema interno da oficina por meio
 * da interface {@link ProcessadorPagamento}, aplicando o padrão Adapter.</p>
 * 
 * <p>Este adaptador permite que o sistema processe pagamentos via Pix e
 * verifique seus status de forma desacoplada do sistema externo.</p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class PagamentoPix implements ProcessadorPagamento {
    
    private SistemaExterno sx;

    /**
     * Construtor que recebe o sistema externo de pagamento.
     *
     * @param sistemaExterno o sistema externo que será utilizado para processar o pagamento
     */
    public PagamentoPix(SistemaExterno sistemaExterno) {
        this.sx = sistemaExterno;
    }

    /**
     * Realiza um pagamento via Pix utilizando o sistema externo.
     *
     * @param valor o valor a ser pago
     * @param idCliente o identificador do cliente
     * @return {@code true} se o pagamento foi realizado com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        System.out.println("Realizando pagamento via Pix.");
        return sx.processarPagamento(valor, idCliente);
    }

    /**
     * Verifica o status do pagamento via Pix de um cliente no sistema externo.
     *
     * @param idCliente o identificador do cliente
     * @return uma {@code String} com o status do pagamento
     */
    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sx.obterStatusPagamento(idCliente);
    }
}
