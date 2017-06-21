package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class ExibicaoLancamentosTeste {

	private BotComando comando = new BotComando();

	@Test
	public void listarLancamentos() {
		try {
			TransacaoDetalhe transacaoDetalhe = comando.listarLancamentos(idTelegram);
			
			Assert.assertEquals(deposito, transacaoDetalhe.getSomatorio().doubleValue(), 0d);
			
			List<Transacao> transacoes = transacaoDetalhe.getTransacoes();
			
			Assert.assertEquals(1, transacoes.size());
			
			Transacao transacao = transacoes.get(0);
			
			Assert.assertEquals(TipoTransacao.DEPOSITO.getCodigo(), transacao.getTipoTransacao());
			Assert.assertEquals(deposito, transacao.getValor(), 0d);
		} catch (ContaInexistenteExcecao e) {
			e.printStackTrace();
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void listarLancamentosInvalido() throws ContaInexistenteExcecao {
		comando.listarLancamentos(idInvalido);
	}

}
