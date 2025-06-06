package Adapter;

public class SistemaExterno {
    public boolean processarPagamento(double valor, int idCliente) {
        System.out.println("Processando pagamento de R$" + valor + " para o cliente ID: " + idCliente);
        return true; 
    }

    public String obterStatusPagamento(int idCliente) {
        System.out.println("Verificando status do pagamento para o cliente ID: " + idCliente);
        return "Pagamento confirmado";
    }
}
