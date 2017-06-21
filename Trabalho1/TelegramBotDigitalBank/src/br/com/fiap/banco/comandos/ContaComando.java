package br.com.fiap.banco.comandos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.TransacaoDao;
import br.com.fiap.banco.dao.impl.UsuarioDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.UsuarioDuplicadoExcecao;

/**
 * Classe responsável por organizar todos os comando que são utilizados na Conta
 *
 */
class ContaComando {

	/**
	 * Retorna a conta a partir do Id do Telegram
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return O Objeto Conta referente ao id informado
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized Conta buscarConta(long idTelegram) throws ContaInexistenteExcecao {
		Conta conta = null;
		
		try (ContaDao contaDao = new ContaDao();) {
			conta = contaDao.buscarConta(idTelegram);
			
			if(conta == null) {
				throw new ContaInexistenteExcecao();
			}
		}
		
		return conta;
	}

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
		Conta conta = new Conta();
		conta.setNumero(idTelegram);
		conta.setDataAbertura(LocalDate.now());
		conta.setSaldo(0.0d);

		try (ContaDao contaDao = new ContaDao();) {
			if (contaDao.criarConta(conta)) {
				try (UsuarioDao usuarioDao = new UsuarioDao();) {
					Usuario usuario = this.criarUsuario(nome, sobrenome, telefone, cpf, email, TipoUsuario.PRINCIPAL, conta);
					
					usuarioDao.criarUsuario(usuario);
				}
			}
		}
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
	public synchronized void alterarConta(long idTelegram, String cpf, String email) throws ContaInexistenteExcecao {
		try (UsuarioDao usuarioDao = new UsuarioDao()) {
			Conta conta = this.buscarConta(idTelegram);
			
			List<Usuario> usuarios = conta.getUsuarios();
			
			for (Usuario usuario : usuarios) {
				if(usuario.getTipoUsuario() == TipoUsuario.PRINCIPAL.getCodigo()) {
					usuario.setCpf(cpf);
					usuario.setEmail(email);
					
					usuarioDao.alterarUsuario(usuario);
				}
			}
		}
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
	 * @throws UsuarioDuplicadoExcecao Se o dependente já existir no BD
	 */
	public synchronized void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) throws ContaInexistenteExcecao, UsuarioDuplicadoExcecao {
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			Conta conta = this.buscarConta(idTelegram);
			
			Usuario usuario = this.criarUsuario(nome, sobrenome, telefone, cpf, email, TipoUsuario.DEPENDENTE, conta);
			
			if(!usuarioDao.criarUsuario(usuario)) {
				throw new UsuarioDuplicadoExcecao();
			}
		}
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
	public synchronized List<Usuario> listarUsuarios(long idTelegram) throws ContaInexistenteExcecao {
		Conta conta = this.buscarConta(idTelegram);
		
		return conta.getUsuarios();
	}
	
	/**
	 * Atualiza o saldo de acordo com o tipo de transação
	 * 
	 * @param idTelegram ID do Telegram
	 * @param valor Valor que será utilizado para somar ou subtrair do saldo
	 * @param tipoTransacao Tipo de transação que está sendo realizada
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized void atualizarSaldo(long idTelegram, double valor, TipoTransacao tipoTransacao) throws ContaInexistenteExcecao {
		Conta conta = this.buscarConta(idTelegram);
		
		double saldo = conta.getSaldo();
		
		switch (tipoTransacao) {
			case DEPOSITO:
			case EMPRESTIMO:
				saldo += valor;
				break;
			case SAQUE:
			case TARIFA:
			case JUROS:
			case PAGAMENTO_EMPRESTIMO:
				saldo -= valor;
				break;
		}
		
		conta.setSaldo(saldo);
		
		try (ContaDao contaDao = new ContaDao();) {
			contaDao.alterarConta(conta);
		}

		Transacao transacao = new Transacao();
		
		transacao.setConta(conta);
		transacao.setDataHora(LocalDateTime.now());
		transacao.setTipoTransacao(tipoTransacao.getCodigo());
		transacao.setValor(valor);
		
		try (TransacaoDao transacaoDao = new TransacaoDao();) {
			transacaoDao.adicionarTransacao(transacao);
		}
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
		Conta conta = this.buscarConta(idTelegram);
		
		return conta.getSaldo();
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
	public synchronized List<Transacao> verificacaoExtrato(long idTelegram) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		if (Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(idTelegram)) {
			this.atualizarSaldo(idTelegram, Tarifas.EXTRATO.getCustoServico(), TipoTransacao.TARIFA);

			Conta conta = this.buscarConta(idTelegram);

			return conta.getTransacoes();
		} else {
			throw new SaldoInsuficienteExcecao();
		}
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
		this.atualizarSaldo(idTelegram, valor, TipoTransacao.DEPOSITO);
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
		if (this.verificarSaldo(idTelegram) > (valor + Tarifas.SAQUE.getCustoServico())) {
			this.atualizarSaldo(idTelegram, valor, TipoTransacao.SAQUE);
			this.atualizarSaldo(idTelegram, Tarifas.SAQUE.getCustoServico(), TipoTransacao.TARIFA);
		} else {
			throw new SaldoInsuficienteExcecao();
		}
	}
	
	/**
	 * Cria um novo usuário
	 * 
	 * @param nome Nome do usuário
	 * @param sobrenome Sobrenome do usuário
	 * @param telefone Número de telefone do usuário
	 * @param cpf CPF do usuário
	 * @param email Email do usuário
	 * @param tipoUsuario Tipo de usuário da conta (principal ou dependente)
	 * @param conta A conta à qual esse usuário pertence
	 * 
	 * @return O usuário que foi criado
	 */
	private synchronized Usuario criarUsuario(String nome, String sobrenome, String telefone, String cpf, String email, TipoUsuario tipoUsuario, Conta conta) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setTipoUsuario(tipoUsuario.getCodigo());
		usuario.setConta(conta);
		
		return usuario;
	}

}
