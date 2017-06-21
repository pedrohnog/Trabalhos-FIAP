package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.deposito;
import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class DepositoTeste {

	private BotComando comando = new BotComando();

	@Test
	public void realizarDeposito() {
		try {
			this.comando.realizarDeposito(idTelegram, deposito);
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void realizarDepositoContaInvalida() throws ContaInexistenteExcecao {
		this.comando.realizarDeposito(idInvalido, deposito);
	}
}
