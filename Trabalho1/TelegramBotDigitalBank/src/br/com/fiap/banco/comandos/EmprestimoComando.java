package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.EmprestimoDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;
import br.com.fiap.banco.util.CaluladorEmprestimoUtil;

/**
 * Classe responsável por organizar todos os comando que são utilizados no Empréstimo
 *
 */
class EmprestimoComando {
	
	/**
	 * Constante que define o prazo máximo (em meses) que o usuário pode pagar o empréstimo
	 */
	private static final int PRAZO_MAXIMO = 36;
	
	/**
	 * Constante que define o multiplicado para calculo do valor máximo que o usuário pode solicitar de empréstimo
	 */
	private static final double MULTIPLICADOR_MAXIMO = 40.0d;

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
		ContaComando contaComando = new ContaComando();
		OperacoesComando operacoesComando = new OperacoesComando();
		TransacaoComando transacaoComando = new TransacaoComando();
		
		if (contaComando.temConta(idTelegram)) {
			double saldo = operacoesComando.verificarSaldo(idTelegram);

			if (valor > this.verificarValorMaximoEmprestimo(idTelegram)) {
				throw new ValorEmprestimoExcedidoExcecao();
			}
			if (prazo > PRAZO_MAXIMO) {
				throw new PrazoEmprestimoExcedidoExcecao();
			}
			if (valor + Tarifas.EMPRESTIMO.getCustoServico() > saldo) {
				throw new SaldoInsuficienteExcecao();
			}

			try (ContaDao contaDao = new ContaDao(); EmprestimoDao emprestimoDao = new EmprestimoDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				if(verificarEmprestimoAberto(idTelegram)) {
					throw new EmprestimoAbertoExcecao();
				}

				List<Emprestimo> listaParcelas = CaluladorEmprestimoUtil.calcularEmprestimo(conta, valor, prazo);
				
				emprestimoDao.adicionarLista(listaParcelas);
						
				conta.setSaldo((conta.getSaldo() - Tarifas.EMPRESTIMO.getCustoServico()) + valor);

				contaDao.alterarConta(conta);

				transacaoComando.gravarTransacao(conta, Tarifas.EMPRESTIMO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());
				transacaoComando.gravarTransacao(conta, valor, TipoTransacao.EMPRESTIMO.getCodigo());
			}
		}
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
		ContaComando contaComando = new ContaComando();
		OperacoesComando operacoesComando = new OperacoesComando();
		
		if (contaComando.temConta(idTelegram)) {
			return operacoesComando.verificarSaldo(idTelegram) * MULTIPLICADOR_MAXIMO;
		}
		return 0.0d;
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
	public synchronized EmprestimoDetalhe buscarSaldoDevedorPrazoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		
		EmprestimoDetalhe emprestimoDetalhe = new EmprestimoDetalhe();
		
		if (contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao(); EmprestimoDao emprestimoDao = new EmprestimoDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				List<Emprestimo> emprestimosAberto = emprestimoDao.buscarDadosEmprestimoAberto(conta.getNumero());
				
				double saldoDevedor = 0.0d;
				
				if(emprestimosAberto != null && !emprestimosAberto.isEmpty()) {
					emprestimoDetalhe.setPrazoPagamento(emprestimosAberto.size());
					
					for (Emprestimo emprestimo : emprestimosAberto) {
						saldoDevedor += emprestimo.getValorParcela() + emprestimo.getJuros();
					}
					
					emprestimoDetalhe.setSaldoDevedor(saldoDevedor);
				}
			}
		}
		
		return emprestimoDetalhe;
	}
	
	/**
	 * Verifica se há algum empréstimo em aberto na conta
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return <code>true</code> se houver algum empréstimo em aberto, se não, <code>false</code>
	 */
	public synchronized boolean verificarEmprestimoAberto(long idTelegram) {
		List<Emprestimo> emprestimosAberto = null;
		
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			emprestimosAberto = emprestimoDao.buscarDadosEmprestimoAberto(idTelegram);
		}
		
		return emprestimosAberto != null && !emprestimosAberto.isEmpty();
	}
	
	/**
	 * Verifica as parcelas do empréstimo que estão vencidas e tenta realizar o pagamento automaticamente
	 * 
	 * @param idTelegram Id do Telegram
	 */
	public synchronized void pagarEmprestimosVencidos(long idTelegram) {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			List<Emprestimo> emprestimosVencidos = emprestimoDao.buscarEmprestimosVencidos(idTelegram);
			for (Emprestimo emprestimo : emprestimosVencidos) {
				if(!this.pagarEmprestimoVencido(idTelegram, emprestimo)) {
					break;
				}
			}
		}
	}
	
	/**
	 * Realiza o pagamento da parcela de empréstimo que está vencida
	 * 
	 * @param idTelegram Id do Telegram
	 * @param emprestimo Parcela que está vencida
	 * 
	 * @return <code>true</code> se conseguir realizar o pagamento, se não <code>false</code>
	 */
	private synchronized boolean pagarEmprestimoVencido(long idTelegram, Emprestimo emprestimo) {
		TransacaoComando transacaoComando = new TransacaoComando();
		
		try (ContaDao contaDao = new ContaDao(); EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			Conta conta = contaDao.buscarConta(idTelegram);

			Double saldo = conta.getSaldo();
			
			if(saldo >= (emprestimo.getValorParcela() + emprestimo.getJuros())) {
				saldo -= (emprestimo.getValorParcela() + emprestimo.getJuros());
				
				conta.setSaldo(saldo);

				transacaoComando.gravarTransacao(conta, emprestimo.getValorParcela(), TipoTransacao.PAGAMENTO_EMPRESTIMO.getCodigo());
				transacaoComando.gravarTransacao(conta, emprestimo.getJuros(), TipoTransacao.JUROS.getCodigo());
				
				//FIXME Por algum motivo que não consegui entender, não está gravando o saldo corretamente
				contaDao.alterarConta(conta);
				emprestimoDao.marcarEmprestimoPago(emprestimo);
			} else {
				return false;
			}
		}
		
		return true;
	}

}
