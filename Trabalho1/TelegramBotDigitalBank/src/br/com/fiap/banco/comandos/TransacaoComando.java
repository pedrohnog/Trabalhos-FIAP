package br.com.fiap.banco.comandos;

import java.time.LocalDateTime;

import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.TransacaoDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

/**
 * Classe responsável por organizar todos os comando que são utilizados nas Transações
 *
 */
class TransacaoComando {

	/**
	 * Grava um transação sempre que for solicitado
	 * 
	 * @param conta Conta na qual ocorreu a transação
	 * @param valor Valor da transação
	 * @param tipoTransacao Tipo de transação que foi realizada
	 */
	public synchronized void gravarTransacao(Conta conta, double valor, int tipoTransacao) {
		try (TransacaoDao transacaoDao = new TransacaoDao();) {
			Transacao transacao = new Transacao();
			transacao.setConta(conta);
			transacao.setDataHora(LocalDateTime.now());
			transacao.setTipoTransacao(tipoTransacao);
			transacao.setValor(valor);

			transacaoDao.adicionarTransacao(transacao);
		}
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
		return this.listarTransacao(idTelegram, TipoTransacao.DEPOSITO);
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
		return this.listarTransacao(idTelegram, TipoTransacao.SAQUE);
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
		return this.listarTransacao(idTelegram, TipoTransacao.TARIFA);
	}
	
	/**
	 * Lista as transações de acordo com o tipo informado
	 * 
	 * @param idTelegram Id do Telegram
	 * @param tipoTransacao Tipo de transação que será listada
	 * 
	 * @return A lista da transação solicitada e a somatória da mesma
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	private synchronized TransacaoDetalhe listarTransacao(long idTelegram, TipoTransacao tipoTransacao) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		
		TransacaoDetalhe transacaoDetalhe = new TransacaoDetalhe();
		
		if (contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao(); TransacaoDao transacaoDao = new TransacaoDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				transacaoDetalhe.setTransacoes(transacaoDao.buscarTransacoes(conta.getNumero(), tipoTransacao));
				
				double somatorio = 0.0d;
				
				for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
					somatorio += transacao.getValor();
				}
				
				transacaoDetalhe.setSomatorio(somatorio);
			}
		}
		
		return transacaoDetalhe;
	}

}
