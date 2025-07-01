package Adapter;

/**
 * Simulação de um sistema externo de pagamentos, utilizado por adaptadores
 * para integrar diferentes formas de pagamento ao sistema da oficina mecânica.
 * 
 * <p>Esta classe representa um serviço de terceiros que processa pagamentos
 * e retorna o status de transações financeiras.</p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class SistemaExterno {

    /**
     * Processa o pagamento de um cliente com base no valor fornecido.
     *
     * @param valor o valor do pagamento
     * @param idCliente o identificador do cliente
     * @return {@code true} indicando que o pagamento foi processado com sucesso
     */
    public boolean processarPagamento(double valor, int idCliente) {
        System.out.println("Processando pagamento de R$" + valor + " para o cliente ID: " + idCliente);
        return true; 
    }

    /**
     * Obtém o status do pagamento de um cliente.
     *
     * @param idCliente o identificador do cliente
     * @return uma {@code String} indicando o status atual do pagamento
     */
    public String obterStatusPagamento(int idCliente) {
        System.out.println("Verificando status do pagamento para o cliente ID: " + idCliente);
        return "Pagamento confirmado";
    }
}
