package Adapter;

/**
 * Implementação de pagamento via Pix usando um sistema externo,
 * adaptado para o contexto de uma oficina mecânica.
 */
public class PagamentoPix implements ProcessadorPagamento {
    private SistemaExterno sx;

    // Construtor que recebe o sistema externo de pagamento
    public PagamentoPix(SistemaExterno sistemaExterno) {
        this.sx = sistemaExterno;
    }

    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        System.out.println("Realizando pagamento via Pix.");
        return sx.processarPagamento(valor, idCliente);
    }

    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sx.obterStatusPagamento(idCliente);
    }
}
