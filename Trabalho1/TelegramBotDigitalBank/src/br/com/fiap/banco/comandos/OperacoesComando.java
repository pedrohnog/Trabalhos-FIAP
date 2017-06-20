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

class OperacoesComando {

	private ContaComando contaComando = new ContaComando();
	private TransacaoComando transacoesComando = new TransacaoComando();

	public void realizarDeposito(long idTelegram, double valor) throws ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				conta.setSaldo(conta.getSaldo() + valor);

				contaDao.alterarConta(conta);

				this.transacoesComando.gravarTransacao(conta, valor, TipoTransacao.DEPOSITO.getCodigo());
			}
		}
	}

	public void realizarSaque(long idTelegram, double valor) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);

				Double saldo = conta.getSaldo();
				Double valorComTarifa = valor + Tarifas.SAQUE.getCustoServico();

				if (saldo > valorComTarifa) {
					saldo -= valorComTarifa;

					conta.setSaldo(saldo);
					contaDao.alterarConta(conta);

					this.transacoesComando.gravarTransacao(conta, valor, TipoTransacao.SAQUE.getCodigo());
					this.transacoesComando.gravarTransacao(conta, Tarifas.SAQUE.getCustoServico(), TipoTransacao.TARIFA.getCodigo());

				} else {
					throw new SaldoInsuficienteExcecao();
				}
			}
		}
	}

	public double verificarSaldo(long idTelegram) throws ContaInexistenteExcecao {
		double saldo = 0.0d;

		if (this.contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				saldo = conta.getSaldo();
			}
		}

		return saldo;
	}

	public List<Transacao> verificacaoExtrato(long idTelegram) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		if (this.contaComando.temConta(idTelegram)) {
			if (Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(idTelegram)) {
				try (ContaDao contaDao = new ContaDao();) {
					Conta conta = contaDao.buscarConta(idTelegram);
	
					this.transacoesComando.gravarTransacao(conta, Tarifas.EXTRATO.getCustoServico(), TipoTransacao.TARIFA.getCodigo());

					return conta.getTransacoes();
				}
			} else {
				throw new SaldoInsuficienteExcecao();
			}
		}
		return new ArrayList<>();
	}

}
