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

	private ContaComando contaComando = new ContaComando();
	private TransacaoComando transacoesComando = new TransacaoComando();
	private OperacoesComando operacoesComando = new OperacoesComando();

	public void solicitarEmprestimo(long idTelegram, double valor, int prazo) throws ContaInexistenteExcecao, ValorEmprestimoExcedidoExcecao, PrazoEmprestimoExcedidoExcecao, SaldoInsuficienteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			double saldo = this.operacoesComando.verificarSaldo(idTelegram);

			if (valor > this.verificarValorMaximoEmprestimo(idTelegram)) {
				throw new ValorEmprestimoExcedidoExcecao();
			}
			if (prazo > 36) {
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

				this.transacoesComando.gravarTransacao(conta, Tarifas.EMPRESTIMO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());
				this.transacoesComando.gravarTransacao(conta, valor, TipoTransacao.EMPRESTIMO.getCodigo());
			}
		}
	}

	public double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			return this.operacoesComando.verificarSaldo(idTelegram) * 40.0d;
		}
		return 0.0d;
	}

}
