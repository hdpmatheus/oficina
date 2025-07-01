package Adapter;

/**
 * Interface para o processamento de pagamento,
 * adaptada para clientes de uma oficina mecânica.
 * 
 * <p>Define os métodos necessários para integração de diferentes
 * formas de pagamento ao sistema da oficina, como Pix ou cartão de crédito.</p>
 * 
 * <p>Utilizada em conjunto com o padrão Adapter para desacoplar a lógica 
 * de pagamento do sistema externo utilizado.</p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public interface ProcessadorPagamento {

    /**
     * Realiza o pagamento de um determinado valor para o cliente informado.
     *
     * @param valor o valor a ser pago
     * @param idCliente o identificador do cliente que está realizando o pagamento
     * @return {@code true} se o pagamento foi concluído com sucesso, {@code false} caso contrário
     */
    boolean realizarPagamento(double valor, int idCliente);

    /**
     * Verifica o status do pagamento do cliente.
     *
     * @param idCliente o identificador do cliente
     * @return uma {@code String} representando o status atual do pagamento
     */
    String verificarStatusPagamento(int idCliente);
}
