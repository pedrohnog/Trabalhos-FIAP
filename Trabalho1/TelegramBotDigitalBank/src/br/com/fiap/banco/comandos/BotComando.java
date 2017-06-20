package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.UsuarioDuplicadoExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;

/**
 * Classe responsável por organizar todos os comandos que podem ser utilizados pelo BOT
 *
 */
public class BotComando {

	/**
	 * Cria uma nova conta para o usuário
	 * 
	 * @param idTelegram ID do Telegram que será usado como número da conta
	 * @param nome Nome do usuário
	 * @param sobrenome Sobrenome do usuário
	 * @param telefone Número de telefone do usuário
	 * @param cpf CPF do usuário
	 * @param email Email do usuário
	 */
	public synchronized void criarConta(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) {
		ContaComando contaComando = new ContaComando();
		contaComando.criarConta(idTelegram, nome, sobrenome, telefone, cpf, email);
	}

	/**
	 * Modifica o CPF e Email do usuário
	 * 
	 * @param idTelegram ID do Telegram
	 * @param cpf CPF do usuário
	 * @param email Email do usuário
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized void modificarConta(long idTelegram, String cpf, String email) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		contaComando.alterarConta(idTelegram, cpf, email);
	}

	/**
	 * Inclui um novo dependente atrelado ao ID do Telegram do usuário principal
	 * 
	 * @param idTelegram ID do Telegram
	 * @param nome Nome do dependente
	 * @param sobrenome Sobrenome do dependente
	 * @param telefone Número de telefone do dependente
	 * @param cpf CPF do dependente
	 * @param email Email do dependente
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 * @throws UsuarioDuplicadoExcecao Se o usuário já existir no BD
	 */
	public synchronized void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) throws ContaInexistenteExcecao, UsuarioDuplicadoExcecao {
		ContaComando contaComando = new ContaComando();
		contaComando.incluirDependente(idTelegram, nome, sobrenome, telefone, cpf, email);
	}

	/**
	 * Busca e devolve todos os usuário e dependentes de uma conta
	 * 
	 * @param idTelegram ID do Telegram
	 * 
	 * @return A lista com os usuários e dependentes
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized List<Usuario> listarUsuariosEDependentes(long idTelegram) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		return contaComando.listarUsuarios(idTelegram);
	}

	/**
	 * Realiza um depósito na conta do usuário
	 * 
	 * @param idTelegram ID do Telegram
	 * @param valor Valor à ser depositado
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized void realizarDeposito(long idTelegram, double valor) throws ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		operacoesComando.realizarDeposito(idTelegram, valor);
	}
	
	/**
	 * Realiza um saque na conta do usuário
	 * 
	 * @param idTelegram ID do Telegram
	 * @param valor Valor à ser sacado
	 * 
	 * @throws SaldoInsuficienteExcecao Se não houver saldo suficiente para concluir a operação
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized void realizarSaque(long idTelegram, double valor) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		operacoesComando.realizarSaque(idTelegram, valor);
	}
	
	/**
	 * Busca e devolve o saldo disponível na conta do usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return O saldo disponível
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized double verificarSaldo(long idTelegram) throws ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		return operacoesComando.verificarSaldo(idTelegram);
	}
	
	/**
	 * Busca e devolve todas as transações realizadas na conta do usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return A lista com todas as transações
	 * 
	 * @throws SaldoInsuficienteExcecao Se não houver saldo suficiente para concluir a operação
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized List<Transacao> verificarExtrato(long idTelegram) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		return operacoesComando.verificacaoExtrato(idTelegram);
	}
	
	/**
	 * Verifica e informa o valor máximo que o usuário pode solicitar de empréstimo
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Valor máximo que o usuário pode solicitar de empréstimo
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		return emprestimoComando.verificarValorMaximoEmprestimo(idTelegram);
	}
	
	/**
	 * Solicita um empréstimo ao banco que será pago mensalmente pelo usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * @param valor Valor que está sendo solicitado de empréstimo
	 * @param prazo Prazo para pagamento do empréstimo
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 * @throws ValorEmprestimoExcedidoExcecao Se exceder o valor máximo permitido para empréstimo
	 * @throws PrazoEmprestimoExcedidoExcecao Se ultrapassar o prazo máximo permitido para pagamento do empréstimo
	 * @throws SaldoInsuficienteExcecao Se não houver saldo suficiente para concluir a operação
	 * @throws EmprestimoAbertoExcecao Se já houver algum empréstimo em aberto nessa conta
	 */
	public synchronized void solicitarEmprestimo(long idTelegram, double valor, int prazo) throws ContaInexistenteExcecao, ValorEmprestimoExcedidoExcecao, PrazoEmprestimoExcedidoExcecao, SaldoInsuficienteExcecao, EmprestimoAbertoExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		emprestimoComando.solicitarEmprestimo(idTelegram, valor, prazo);
	}
	
	/**
	 * Verifica e informa o saldo devedor e o prazo restante para o pagamento de um empréstimo
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return O saldo devedor e o prazo restante para o pagamento de um empréstimo
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized EmprestimoDetalhe verificarSaldoDevedorPrazoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		return emprestimoComando.buscarSaldoDevedorPrazoEmprestimo(idTelegram);
	}
	
	/**
	 * Lista todos os lançamentos que foram realizadas na conta do usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Os lançamentos realizados e a somatória dos mesmos
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized TransacaoDetalhe listarLancamentos(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarLancamentos(idTelegram);
	}
	
	/**
	 * Lista todas as retiradas que foram realizadas na conta do usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return As retiradas realizadas e a somatória das mesmas
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized TransacaoDetalhe listarRetiradas(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarRetiradas(idTelegram);
	}
	
	/**
	 * Lista todas as tarifas já pagas pelo usuário
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return As tarifas pagas e a somatória das mesmas
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized TransacaoDetalhe listarTarifas(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarTarifas(idTelegram);
	}

}
