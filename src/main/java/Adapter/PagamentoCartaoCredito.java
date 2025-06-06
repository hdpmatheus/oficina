package Adapter;

/**
 * Implementação de pagamento por cartão de crédito usando um sistema externo,
 * adaptado para o contexto de uma oficina mecânica.
 */
public class PagamentoCartaoCredito implements ProcessadorPagamento {
    private SistemaExterno sx;

    // Construtor que recebe o sistema externo de pagamento
    public PagamentoCartaoCredito(SistemaExterno sistemaExterno) {
        this.sx = sistemaExterno;
    }

    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        System.out.println("Realizando pagamento com cartão de crédito.");
        return sx.processarPagamento(valor, idCliente);
    }

    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sx.obterStatusPagamento(idCliente);
    }
}
