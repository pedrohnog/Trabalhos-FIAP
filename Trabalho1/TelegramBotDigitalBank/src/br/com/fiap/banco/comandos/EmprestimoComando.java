package br.com.fiap.banco.comandos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javax.persistence.NoResultException;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.banco.dao.impl.EmprestimoDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PagamentoEmprestimoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;
import br.com.fiap.banco.util.CaluladorEmprestimoUtil;

/**
 * Classe responsável por organizar todos os comando que são utilizados no Empréstimo
 *
 */
class EmprestimoComando {
	
	private static DecimalFormat df2 = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(new Locale("en", "US")));
	
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
		
		double saldo = contaComando.verificarSaldo(idTelegram);

		if (valor >= (this.verificarValorMaximoEmprestimo(idTelegram) + .01d)) {
			throw new ValorEmprestimoExcedidoExcecao();
		}
		
		if (prazo > PRAZO_MAXIMO) {
			throw new PrazoEmprestimoExcedidoExcecao();
		}
		
		if (Tarifas.EMPRESTIMO.getCustoServico() > saldo) {
			throw new SaldoInsuficienteExcecao();
		}

		if(this.verificarEmprestimoAberto(idTelegram)) {
			throw new EmprestimoAbertoExcecao();
		}
		
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			Conta conta = contaComando.buscarConta(idTelegram);
			
			List<Emprestimo> listaParcelas = CaluladorEmprestimoUtil.calcularEmprestimo(conta, valor, prazo);
			
			emprestimoDao.adicionarLista(listaParcelas);
					
			contaComando.atualizarSaldo(idTelegram, Tarifas.EMPRESTIMO.getCustoServico(), TipoTransacao.TARIFA);
			contaComando.atualizarSaldo(idTelegram, valor, TipoTransacao.EMPRESTIMO);
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
	 * @throws SaldoInsuficienteExcecao Se não houver saldo suficiente para solicitar o empréstimo
	 */
	public synchronized double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao, SaldoInsuficienteExcecao {
		ContaComando contaComando = new ContaComando();
		
		double saldo = contaComando.verificarSaldo(idTelegram);
		
		if(saldo < Tarifas.EMPRESTIMO.getCustoServico()) {
			throw new SaldoInsuficienteExcecao();
		}
		
		return saldo * MULTIPLICADOR_MAXIMO;
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
	public synchronized EmprestimoDetalhe buscarSaldoDevedorPrazoEmprestimo(long idTelegram) {
		EmprestimoDetalhe emprestimoDetalhe = new EmprestimoDetalhe();
		
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			
			List<Emprestimo> emprestimosAberto = emprestimoDao.buscarDadosEmprestimoAberto(idTelegram);
			
			double saldoDevedor = 0.0d;
			
			if(emprestimosAberto != null && !emprestimosAberto.isEmpty()) {
				emprestimoDetalhe.setPrazoPagamento(emprestimosAberto.size());
				
				for (Emprestimo emprestimo : emprestimosAberto) {
					saldoDevedor += emprestimo.getValorParcela() + emprestimo.getJuros();
				}
				
				emprestimoDetalhe.setSaldoDevedor(Double.valueOf(df2.format(saldoDevedor)));
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
	 * 
	 * @throws ContaInexistenteExcecao  Se não existir a conta informada
	 */
	public synchronized void pagarEmprestimosVencidos(long idTelegram) throws ContaInexistenteExcecao {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			List<Emprestimo> emprestimosVencidos = emprestimoDao.buscarEmprestimosVencidos(idTelegram);
			for (Emprestimo emprestimo : emprestimosVencidos) {
				if(!this.pagarEmprestimo(idTelegram, emprestimo)) {
					break;
				}
			}
		}
	}
	
	/**
	 * Lista todas as parcelas que estão vencidas e não pagas
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Lista de parcelas vencidas e não pagas
	 */
	public synchronized List<Emprestimo> listarEmprestimosVencidos(long idTelegram) {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			return emprestimoDao.buscarEmprestimosVencidos(idTelegram);
		}
	}
	
	/**
	 * Lista todas as parcelas que não estão pagas
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Lista de parcelas não pagas
	 */
	public synchronized List<Emprestimo> listarEmprestimosAbertos(long idTelegram) {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			return emprestimoDao.buscarDadosEmprestimoAberto(idTelegram);
		}
	}
	
	/**
	 * Busca a parcela de um empréstimo e só realiza o pagamento se estiver vencido e não tiver sido pago
	 * 
	 * @param idTelegram Id do Telegram
	 * @param numeroParcela Número da parcela
	 * 
	 * @return <code>true</code> se conseguir realizar o pagamento, se não <code>false</code>
	 * 
	 * @throws ContaInexistenteExcecao ContaInexistenteExcecao Se não existir a conta informada
	 * @throws PagamentoEmprestimoExcecao Se a parcela não existir ou já estiver paga
	 */
	public synchronized boolean pagarEmprestimo(long idTelegram, int numeroParcela) throws ContaInexistenteExcecao, PagamentoEmprestimoExcecao {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			Emprestimo emprestimoVencido = emprestimoDao.buscarEmprestimo(idTelegram, numeroParcela);
			
			if(emprestimoVencido == null || !this.pagarEmprestimo(idTelegram, emprestimoVencido)) {
				throw new PagamentoEmprestimoExcecao();
			}
			
			return true;
		} catch(NoResultException e) {
			throw new PagamentoEmprestimoExcecao();
		}
	}
	
	/**
	 * Realiza o pagamento da parcela de empréstimo que está vencida
	 * 
	 * @param idTelegram Id do Telegram
	 * @param emprestimo Parcela que está vencida
	 * 
	 * @return <code>true</code> se conseguir realizar o pagamento, se não <code>false</code>
	 * 
	 * @throws ContaInexistenteExcecao Se não existir a conta informada
	 */
	private synchronized boolean pagarEmprestimo(long idTelegram, Emprestimo emprestimo) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		
		if (!emprestimo.isParcelaPaga()) {
			try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
				double saldo = contaComando.verificarSaldo(idTelegram);
				
				if(saldo >= (emprestimo.getValorParcela() + emprestimo.getJuros())) {
					contaComando.atualizarSaldo(idTelegram, emprestimo.getValorParcela(), TipoTransacao.PAGAMENTO_EMPRESTIMO);
					contaComando.atualizarSaldo(idTelegram, emprestimo.getJuros(), TipoTransacao.JUROS);
					
					emprestimo.setConta(contaComando.buscarConta(idTelegram));
					
					emprestimoDao.marcarEmprestimoPago(emprestimo);
				} else {
					return false;
				}
			}
		}
		
		return true;
	}

}
