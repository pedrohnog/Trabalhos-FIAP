package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.valorFinal;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class VerificacaoSaldoFinalTeste {

	private BotComando comando = new BotComando();

	@Test
	public void solicitarSaldo() {
		try {
			double saldo = this.comando.verificarSaldo(idTelegram);

			Assert.assertEquals(valorFinal, saldo, 0d);
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

}
