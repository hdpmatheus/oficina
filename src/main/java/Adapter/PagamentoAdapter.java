package Adapter;

public class PagamentoAdapter implements ProcessadorPagamento {
    private SistemaExterno sistemaExterno;

    public PagamentoAdapter(SistemaExterno sistemaExterno) {
        this.sistemaExterno = sistemaExterno;
    }

    @Override
    public boolean realizarPagamento(double valor, int idCliente) {
        return sistemaExterno.processarPagamento(valor, idCliente);
    }

    @Override
    public String verificarStatusPagamento(int idCliente) {
        return sistemaExterno.obterStatusPagamento(idCliente);
    }
}
