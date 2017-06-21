package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class ExibicaoTarifasTeste {

	private BotComando comando = new BotComando();

	@Test
	public void listarTarifas() {
		try {
			TransacaoDetalhe transacaoDetalhe = comando.listarTarifas(idTelegram);

			double valorEsperado = Tarifas.SAQUE.getCustoServico() + Tarifas.EXTRATO.getCustoServico() + Tarifas.EMPRESTIMO.getCustoServico();

			Assert.assertEquals(valorEsperado, transacaoDetalhe.getSomatorio().doubleValue(), 0d);

			List<Transacao> transacoes = transacaoDetalhe.getTransacoes();

			Assert.assertEquals(3, transacoes.size());

			Transacao transacaoSaque = transacoes.get(0);
			Assert.assertEquals(TipoTransacao.TARIFA.getCodigo(), transacaoSaque.getTipoTransacao());
			Assert.assertEquals(Tarifas.SAQUE.getCustoServico(), transacaoSaque.getValor(), 0d);

			Transacao transacaoExtrato = transacoes.get(1);
			Assert.assertEquals(TipoTransacao.TARIFA.getCodigo(), transacaoExtrato.getTipoTransacao());
			Assert.assertEquals(Tarifas.EXTRATO.getCustoServico(), transacaoExtrato.getValor(), 0d);

			Transacao transacaoEmprestimo = transacoes.get(2);
			Assert.assertEquals(TipoTransacao.TARIFA.getCodigo(), transacaoEmprestimo.getTipoTransacao());
			Assert.assertEquals(Tarifas.EMPRESTIMO.getCustoServico(), transacaoEmprestimo.getValor(), 0d);
		} catch (ContaInexistenteExcecao e) {
			e.printStackTrace();
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void listarTarifasInvalido() throws ContaInexistenteExcecao {
		comando.listarTarifas(idInvalido);
	}

}
