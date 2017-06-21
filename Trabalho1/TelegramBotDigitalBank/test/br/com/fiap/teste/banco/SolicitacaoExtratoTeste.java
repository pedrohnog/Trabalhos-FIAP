package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.deposito;
import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.saque;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;

public class SolicitacaoExtratoTeste {

	private BotComando comando = new BotComando();

	@Test
	public void solicitarExtrato() {
		try {
			List<Transacao> extrato = this.comando.verificarExtrato(idTelegram);
			
			Assert.assertEquals(4, extrato.size());
			
			Transacao extratoDeposito = extrato.get(0);
			Assert.assertEquals(TipoTransacao.DEPOSITO.getCodigo(), extratoDeposito.getTipoTransacao());
			Assert.assertEquals(deposito, extratoDeposito.getValor(), 0d);
			
			Transacao extratoSaque = extrato.get(1);
			Assert.assertEquals(TipoTransacao.SAQUE.getCodigo(), extratoSaque.getTipoTransacao());
			Assert.assertEquals(saque, extratoSaque.getValor(), 0d);
			
			Transacao extratoTarifaSaque = extrato.get(2);
			Assert.assertEquals(TipoTransacao.TARIFA.getCodigo(), extratoTarifaSaque.getTipoTransacao());
			Assert.assertEquals(Tarifas.SAQUE.getCustoServico(), extratoTarifaSaque.getValor(), 0d);
			
			Transacao extratoTarifaExtrato = extrato.get(3);
			Assert.assertEquals(TipoTransacao.TARIFA.getCodigo(), extratoTarifaExtrato.getTipoTransacao());
			Assert.assertEquals(Tarifas.EXTRATO.getCustoServico(), extratoTarifaExtrato.getValor(), 0d);
		} catch (SaldoInsuficienteExcecao | ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void solicitarExtratoContaInvalida() throws ContaInexistenteExcecao {
		try {
			this.comando.verificarExtrato(idInvalido);
		} catch (SaldoInsuficienteExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

}
