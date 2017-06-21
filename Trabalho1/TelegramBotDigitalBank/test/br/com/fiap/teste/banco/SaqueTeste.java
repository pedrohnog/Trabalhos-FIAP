package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.saque;
import static br.com.fiap.teste.ConstantesTeste.saqueInvalido;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;

public class SaqueTeste {

	private BotComando comando = new BotComando();

	@Test
	public void realizarSaque() {
		try {
			this.comando.realizarSaque(idTelegram, saque);
		} catch (SaldoInsuficienteExcecao | ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void realizarSaqueContaInvalida() throws ContaInexistenteExcecao {
		try {
			this.comando.realizarSaque(idInvalido, saque);
		} catch (SaldoInsuficienteExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

	@Test(expected = SaldoInsuficienteExcecao.class)
	public void realizarSaqueSaldoInsuficiente() throws SaldoInsuficienteExcecao {
		try {
			this.comando.realizarSaque(idTelegram, saqueInvalido);
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

}
