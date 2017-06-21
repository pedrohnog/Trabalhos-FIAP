package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.saldoEsperado;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class SolicitacaoSaldoTeste {

	private BotComando comando = new BotComando();

	@Test
	public void solicitarSaldo() {
		try {
			double saldo = this.comando.verificarSaldo(idTelegram);

			Assert.assertEquals(saldoEsperado, saldo, 0d);
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void solicitarSaldoContaInvalida() throws ContaInexistenteExcecao {
		this.comando.verificarSaldo(idInvalido);
	}

}
