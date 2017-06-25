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
 * Classe respons�vel por organizar todos os comando que s�o utilizados no Empr�stimo
 *
 */
class EmprestimoComando {
	
	private static DecimalFormat df2 = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(new Locale("en", "US")));
	
	/**
	 * Constante que define o prazo m�ximo (em meses) que o usu�rio pode pagar o empr�stimo
	 */
	private static final int PRAZO_MAXIMO = 36;
	
	/**
	 * Constante que define o multiplicado para calculo do valor m�ximo que o usu�rio pode solicitar de empr�stimo
	 */
	private static final double MULTIPLICADOR_MAXIMO = 40.0d;

	/**
	 * Solicita um empr�stimo ao banco que ser� pago mensalmente pelo usu�rio
	 * 
	 * @param idTelegram Id do Telegram
	 * @param valor Valor que est� sendo solicitado de empr�stimo
	 * @param prazo Prazo para pagamento do empr�stimo
	 * 
	 * @throws ContaInexistenteExcecao Se n�o existir a conta informada
	 * @throws ValorEmprestimoExcedidoExcecao Se exceder o valor m�ximo permitido para empr�stimo
	 * @throws PrazoEmprestimoExcedidoExcecao Se ultrapassar o prazo m�ximo permitido para pagamento do empr�stimo
	 * @throws SaldoInsuficienteExcecao Se n�o houver saldo suficiente para concluir a opera��o
	 * @throws EmprestimoAbertoExcecao Se j� houver algum empr�stimo em aberto nessa conta
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
	 * Verifica e informa o valor m�ximo que o usu�rio pode solicitar de empr�stimo
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Valor m�ximo que o usu�rio pode solicitar de empr�stimo
	 * 
	 * @throws ContaInexistenteExcecao Se n�o existir a conta informada
	 * @throws SaldoInsuficienteExcecao Se n�o houver saldo suficiente para solicitar o empr�stimo
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
	 * Verifica e informa o saldo devedor e o prazo restante para o pagamento de um empr�stimo
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return O saldo devedor e o prazo restante para o pagamento de um empr�stimo
	 * 
	 * @throws ContaInexistenteExcecao Se n�o existir a conta informada
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
	 * Verifica se h� algum empr�stimo em aberto na conta
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return <code>true</code> se houver algum empr�stimo em aberto, se n�o, <code>false</code>
	 */
	public synchronized boolean verificarEmprestimoAberto(long idTelegram) {
		List<Emprestimo> emprestimosAberto = null;
		
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			emprestimosAberto = emprestimoDao.buscarDadosEmprestimoAberto(idTelegram);
		}
		
		return emprestimosAberto != null && !emprestimosAberto.isEmpty();
	}
	
	/**
	 * Verifica as parcelas do empr�stimo que est�o vencidas e tenta realizar o pagamento automaticamente
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @throws ContaInexistenteExcecao  Se n�o existir a conta informada
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
	 * Lista todas as parcelas que est�o vencidas e n�o pagas
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Lista de parcelas vencidas e n�o pagas
	 */
	public synchronized List<Emprestimo> listarEmprestimosVencidos(long idTelegram) {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			return emprestimoDao.buscarEmprestimosVencidos(idTelegram);
		}
	}
	
	/**
	 * Lista todas as parcelas que n�o est�o pagas
	 * 
	 * @param idTelegram Id do Telegram
	 * 
	 * @return Lista de parcelas n�o pagas
	 */
	public synchronized List<Emprestimo> listarEmprestimosAbertos(long idTelegram) {
		try (EmprestimoDao emprestimoDao = new EmprestimoDao();) {
			return emprestimoDao.buscarDadosEmprestimoAberto(idTelegram);
		}
	}
	
	/**
	 * Busca a parcela de um empr�stimo e s� realiza o pagamento se estiver vencido e n�o tiver sido pago
	 * 
	 * @param idTelegram Id do Telegram
	 * @param numeroParcela N�mero da parcela
	 * 
	 * @return <code>true</code> se conseguir realizar o pagamento, se n�o <code>false</code>
	 * 
	 * @throws ContaInexistenteExcecao ContaInexistenteExcecao Se n�o existir a conta informada
	 * @throws PagamentoEmprestimoExcecao Se a parcela n�o existir ou j� estiver paga
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
	 * Realiza o pagamento da parcela de empr�stimo que est� vencida
	 * 
	 * @param idTelegram Id do Telegram
	 * @param emprestimo Parcela que est� vencida
	 * 
	 * @return <code>true</code> se conseguir realizar o pagamento, se n�o <code>false</code>
	 * 
	 * @throws ContaInexistenteExcecao Se n�o existir a conta informada
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
