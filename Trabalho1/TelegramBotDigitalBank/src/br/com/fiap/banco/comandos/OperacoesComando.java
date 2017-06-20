package br.com.fiap.banco.comandos;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;

/**
 * Classe responsável por organizar todos os comando que são utilizados nas Operações
 *
 */
class OperacoesComando {

	/**
	 * Realiza um depósito na conta do usuário
	 * 
	 * @param idTelegram ID do Telegram
	 * @param valor Valor à ser depositado
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	public synchronized void realizarDeposito(long idTelegram, double valor) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		TransacaoComando transacaoComando = new TransacaoComando();
		
		if (contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				conta.setSaldo(conta.getSaldo() + valor);

				contaDao.alterarConta(conta);

				transacaoComando.gravarTransacao(conta, valor, TipoTransacao.DEPOSITO.getCodigo());
			}
		}
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
		ContaComando contaComando = new ContaComando();
		TransacaoComando transacaoComando = new TransacaoComando();
		
		if (contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);

				Double saldo = conta.getSaldo();
				Double valorComTarifa = valor + Tarifas.SAQUE.getCustoServico();

				if (saldo > valorComTarifa) {
					saldo -= valorComTarifa;

					conta.setSaldo(saldo);
					
					contaDao.alterarConta(conta);

					transacaoComando.gravarTransacao(conta, valor, TipoTransacao.SAQUE.getCodigo());
					transacaoComando.gravarTransacao(conta, Tarifas.SAQUE.getCustoServico(), TipoTransacao.TARIFA.getCodigo());
				} else {
					throw new SaldoInsuficienteExcecao();
				}
			}
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
		ContaComando contaComando = new ContaComando();
		
		double saldo = 0.0d;

		if (contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				saldo = conta.getSaldo();
			}
		}

		return saldo;
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
		ContaComando contaComando = new ContaComando();
		TransacaoComando transacaoComando = new TransacaoComando();
		
		if (contaComando.temConta(idTelegram)) {
			if (Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(idTelegram)) {
				try (ContaDao contaDao = new ContaDao();) {
					Conta conta = contaDao.buscarConta(idTelegram);
	
					transacaoComando.gravarTransacao(conta, Tarifas.EXTRATO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());

					return conta.getTransacoes();
				}
			} else {
				throw new SaldoInsuficienteExcecao();
			}
		}
		return new ArrayList<>();
	}

}
