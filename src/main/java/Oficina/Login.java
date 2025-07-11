package oficina;

import Entidades.Funcionario;
import Entidades.Gerente;
import Controler.GerenciarFuncionario;
import Controler.GerenciarGerente;

/**
 * Classe responsável pela autenticação de login para Funcionários e Gerentes.
 * Realiza a verificação de ID e senha para acesso ao sistema da oficina.
 * 
 * Esta classe é utilizada no processo inicial do sistema, onde o usuário 
 * deve se autenticar para acessar as funcionalidades disponíveis.
 * 
 * Autentica usuários com base no tipo (funcionário ou gerente),
 * validando suas credenciais contra os dados armazenados.
 * 
 * @author 
 * Matheus Henrique de Paula <br>
 * Felipe Alcântara Guimarães Veloso
 */
public class Login {

    private GerenciarFuncionario gerenciarFuncionario;
    private GerenciarGerente gerenciarGerente;

    /**
     * Construtor da classe Login.
     *
     * @param gerenciarFuncionario Instância de gerenciamento de funcionários
     * @param gerenciarGerente Instância de gerenciamento de gerentes
     */
    public Login(GerenciarFuncionario gerenciarFuncionario, GerenciarGerente gerenciarGerente) {
        this.gerenciarFuncionario = gerenciarFuncionario;
        this.gerenciarGerente = gerenciarGerente;
    }

    /**
     * Realiza o login e retorna o tipo do usuário se for bem-sucedido.
     *
     * @param id   ID do usuário
     * @param senha Senha do usuário
     * @param tipo  Tipo de usuário (funcionario/gerente)
     * @return "funcionario", "gerente" ou null se a autenticação falhar
     */
    public String realizarLogin(int id, int senha, String tipo) {
        if (tipo.equalsIgnoreCase("funcionario")) {
            Funcionario funcionario = gerenciarFuncionario.buscarFuncionario(id);
            if (funcionario != null && funcionario.getSenha() == senha) {
                System.out.println("✅ Login realizado com sucesso como Funcionário: " + funcionario.getNome());
                return "funcionario";
            }
        } else if (tipo.equalsIgnoreCase("gerente")) {
            Gerente gerente = gerenciarGerente.buscarGerente(id);
            if (gerente != null && gerente.getSenha() == senha) {
                System.out.println("✅ Login realizado com sucesso como Gerente: " + gerente.getNome());
                return "gerente";
            }
        }

        System.out.println("❌ ID ou senha incorretos. Tente novamente.");
        return null;
    }
}
