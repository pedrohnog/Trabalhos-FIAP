package br.com.fiap.banco.comandos;

import java.time.LocalDateTime;

import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.TransacaoDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class TransacaoComando {

	private ContaComando contaComando = new ContaComando();

	public void gravarTransacao(Usuario usuario, double valor, int tipoTransacao) {
		try (TransacaoDao transacaoDao = new TransacaoDao();) {
			Transacao transacao = new Transacao();
			transacao.setConta(usuario.getConta());
			transacao.setDataHora(LocalDateTime.now());
			transacao.setTipoTransacao(tipoTransacao);
			transacao.setUsuario(usuario);
			transacao.setValor(valor);

			transacaoDao.adicionarTransacao(transacao);
		}
	}

	public TransacaoDetalhe listarLancamentos(long idTelegram) throws ContaInexistenteExcecao {
		return this.listarTransacao(idTelegram, TipoTransacao.DEPOSITO);
	}

	public TransacaoDetalhe listarRetiradas(long idTelegram) throws ContaInexistenteExcecao {
		return this.listarTransacao(idTelegram, TipoTransacao.SAQUE);
	}

	public TransacaoDetalhe listarTarifas(long idTelegram) throws ContaInexistenteExcecao {
		return this.listarTransacao(idTelegram, TipoTransacao.TARIFA);
	}
	
	private TransacaoDetalhe listarTransacao(long idTelegram, TipoTransacao tipoTransacao) throws ContaInexistenteExcecao {
		TransacaoDetalhe transacaoDetalhe = new TransacaoDetalhe();
		
		if (this.contaComando.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao(); TransacaoDao transacaoDao = new TransacaoDao();) {
				Usuario usuario = this.contaComando.buscarUsuarioConta(idTelegram);
				
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				
				transacaoDetalhe.setTransacoes(transacaoDao.buscarTransacoes(conta.getNumero(), tipoTransacao));
				
				double somatorio = 0.0d;
				
				for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
					somatorio += transacao.getValor();
				}
				
				transacaoDetalhe.setSomatorio(somatorio);
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
		return transacaoDetalhe;
	}

}
