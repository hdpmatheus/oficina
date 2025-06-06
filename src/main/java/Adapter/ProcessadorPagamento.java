package Adapter;

/**
 * Interface para o processamento de pagamento,
 * adaptada para clientes de uma oficina mecânica.
 */
public interface ProcessadorPagamento {
    boolean realizarPagamento(double valor, int idCliente);
    String verificarStatusPagamento(int idCliente);
}
