package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.EmprestimoDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;
import br.com.fiap.banco.util.CaluladorEmprestimoUtil;

class EmprestimoComando {
	
	private static final int PRAZO_MAXIMO = 36;
	private static final double MULTIPLICADOR_MAXIMO = 40.0d;

	public synchronized void solicitarEmprestimo(long idTelegram, double valor, int prazo) throws ContaInexistenteExcecao, ValorEmprestimoExcedidoExcecao, PrazoEmprestimoExcedidoExcecao, SaldoInsuficienteExcecao {
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

				List<Emprestimo> listaParcelas = CaluladorEmprestimoUtil.calcularEmprestimo(conta, valor, prazo);
				
				emprestimoDao.adicionarLista(listaParcelas);
						
				conta.setSaldo((conta.getSaldo() - Tarifas.EMPRESTIMO.getCustoServico()) + valor);

				contaDao.alterarConta(conta);

				transacaoComando.gravarTransacao(conta, Tarifas.EMPRESTIMO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());
				transacaoComando.gravarTransacao(conta, valor, TipoTransacao.EMPRESTIMO.getCodigo());
			}
		}
	}

	public synchronized double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		OperacoesComando operacoesComando = new OperacoesComando();
		
		if (contaComando.temConta(idTelegram)) {
			return operacoesComando.verificarSaldo(idTelegram) * MULTIPLICADOR_MAXIMO;
		}
		return 0.0d;
	}

}
