package Adapter;

/**
 * Implementação de pagamento por cartão de crédito usando um sistema externo,
 * adaptado para o contexto de uma oficina mecânica.
 * 
 * <p>Utiliza um {@link SistemaExterno} para processar pagamentos e verificar 
 * o status das transações, mantendo o padrão de projeto Adapter para 
 * integração com o sistema interno.</p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class PagamentoCartaoCredito implements ProcessadorPagamento {
    
    private SistemaExterno sx;

    /**
     * Construtor que recebe o sistema externo de pagamento.
     *
     * @param sistemaExterno o sistema externo que será utilizado para processar o pagamento
     */
    public PagamentoCartaoCredito(SistemaExterno sistemaExterno) {
        this.sx = sistemaExterno;
    }

    /**
     * Realiza um pagamento via cartão de crédito utilizando o sistema externo.
     *
     * @param valor o valor a ser pago
     * @param idCliente o identificador do cliente
     * @return {@code true} se o pagamento foi realizado com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        System.out.println("Realizando pagamento com cartão de crédito.");
        return sx.processarPagamento(valor, idCliente);
    }

    /**
     * Verifica o status do pagamento de um cliente no sistema externo.
     *
     * @param idCliente o identificador do cliente
     * @return uma {@code String} com o status do pagamento
     */
    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sx.obterStatusPagamento(idCliente);
    }
}
