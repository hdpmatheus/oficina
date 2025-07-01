package Adapter;

/**
 * Adapter que permite que o sistema interno utilize os serviços de pagamento 
 * de um {@link SistemaExterno}, adaptando sua interface para a interface 
 * {@link ProcessadorPagamento}.
 * 
 * <p>Esse padrão permite desacoplar o sistema principal do sistema externo, 
 * facilitando a manutenção e possíveis substituições do provedor de pagamento.</p>
 * 
 * @author Matheus Henrique de Paula
 * @author Felipe Alcântara Guimarães Veloso
 */
public class PagamentoAdapter implements ProcessadorPagamento {
    
    private SistemaExterno sistemaExterno;

    /**
     * Construtor que inicializa o adapter com o sistema externo de pagamento.
     *
     * @param sistemaExterno o sistema externo que será adaptado
     */
    public PagamentoAdapter(SistemaExterno sistemaExterno) {
        this.sistemaExterno = sistemaExterno;
    }

    /**
     * Realiza um pagamento utilizando o sistema externo.
     *
     * @param valor o valor a ser pago
     * @param idCliente o identificador do cliente que está realizando o pagamento
     * @return {@code true} se o pagamento foi realizado com sucesso, {@code false} caso contrário
     */
    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        return sistemaExterno.processarPagamento(valor, idCliente);
    }

    /**
     * Verifica o status de pagamento de um cliente no sistema externo.
     *
     * @param idCliente o identificador do cliente
     * @return uma {@code String} com o status atual do pagamento
     */
    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sistemaExterno.obterStatusPagamento(idCliente);
    }
}
