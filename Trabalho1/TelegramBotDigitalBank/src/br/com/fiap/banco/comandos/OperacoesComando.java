package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;

public class OperacoesComando {

	private ContaComando contaComando = new ContaComando();
	private TransacaoComando transacoesComando = new TransacaoComando();

	public void realizarDeposito(long idTelegram, double valor) throws ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());

				conta.setSaldo(conta.getSaldo() + valor);

				contaDao.alterarConta(conta);

				this.transacoesComando.gravarTransacao(usuario, valor, TipoTransacao.DEPOSITO.getCodigo());
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
	}

	public void realizarSaque(long idTelegram, double valor) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());

				Double saldo = conta.getSaldo();
				Double valorComTarifa = valor + Tarifas.SAQUE.getCustoServico();

				if (saldo > valorComTarifa) {
					saldo -= valorComTarifa;

					conta.setSaldo(saldo);
					contaDao.alterarConta(conta);

					this.transacoesComando.gravarTransacao(usuario, valor, TipoTransacao.SAQUE.getCodigo());
					this.transacoesComando.gravarTransacao(usuario, Tarifas.SAQUE.getCustoServico(), TipoTransacao.TARIFA.getCodigo());

				} else {
					throw new SaldoInsuficienteExcecao();
				}
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
	}

	public double verificarSaldo(long idTelegram) throws ContaInexistenteExcecao {
		double saldo = 0.0d;

		if (this.contaComando.temConta(idTelegram)) {
			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				saldo = conta.getSaldo();
			}
		} else {
			throw new ContaInexistenteExcecao();
		}

		return saldo;
	}

	public List<Transacao> verificacaoExtrato(long idTelegram) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			if (Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(idTelegram)) {
				Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);

				this.transacoesComando.gravarTransacao(usuario, Tarifas.EXTRATO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());

				try (ContaDao contaDao = new ContaDao();) {
					return contaDao.buscarConta(usuario.getConta().getNumero()).getTransacoes();
				}
			} else {
				throw new SaldoInsuficienteExcecao();
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
	}

	public double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			return this.verificarSaldo(idTelegram) * 40.0d;
		}
		return 0.0d;
	}

	public void solicitarEmprestimo(long idTelegram, double valor, int prazo) throws ContaInexistenteExcecao, ValorEmprestimoExcedidoExcecao, PrazoEmprestimoExcedidoExcecao, SaldoInsuficienteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			double saldo = this.verificarSaldo(idTelegram);

			if (valor > this.verificarValorMaximoEmprestimo(idTelegram)) {
				throw new ValorEmprestimoExcedidoExcecao();
			}
			if (prazo > 36) {
				throw new PrazoEmprestimoExcedidoExcecao();
			}
			if (valor + Tarifas.EMPRESTIMO.getCustoServico() > saldo) {
				throw new SaldoInsuficienteExcecao();
			}

			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);

			// TODO Criar operações de empréstimo

			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());

				conta.setSaldo((conta.getSaldo() - Tarifas.EMPRESTIMO.getCustoServico()) + valor);

				contaDao.alterarConta(conta);

				this.transacoesComando.gravarTransacao(usuario, Tarifas.EMPRESTIMO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());
				this.transacoesComando.gravarTransacao(usuario, valor, TipoTransacao.EMPRESTIMO.getCodigo());
			}
		}
	}

}
