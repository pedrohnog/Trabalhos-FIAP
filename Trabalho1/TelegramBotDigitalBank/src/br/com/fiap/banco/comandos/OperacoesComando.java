package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;

public class OperacoesComando {

	private ContaComando contaComando = new ContaComando();
	private TransacaoComando transacoesComando = new TransacaoComando();

	public void realizarDeposito(long idTelegram, double valor) {
		if (this.contaComando.temConta(idTelegram)) {
			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				
				conta.setSaldo(conta.getSaldo() + valor);
				
				contaDao.alterarConta(conta);
				
				this.transacoesComando.gravarTransacao(usuario, valor, TipoTransacao.DEPOSITO.getCodigo());
			}
		}
	}

	public void realizarSaque(long idTelegram, double valor) throws SaldoInsuficienteExcecao {
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
		}
	}

	private double verificarSaldo(long idTelegram) {
		double saldo = 0.0d;
		
		if (this.contaComando.temConta(idTelegram)) {
			Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				saldo = conta.getSaldo();
			}
		}
		
		return saldo;
	}

	public List<Transacao> verificacaoExtrato(long id) {
		if (Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(id)) {
			// TODO Buscar a lista de transações no BD
			return null;
		} else {
			return null;
		}
	}

}
