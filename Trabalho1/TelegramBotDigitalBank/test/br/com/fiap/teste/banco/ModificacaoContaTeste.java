package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.cpfNovo;
import static br.com.fiap.teste.ConstantesTeste.cpfPrincipal;
import static br.com.fiap.teste.ConstantesTeste.emailNovo;
import static br.com.fiap.teste.ConstantesTeste.emailPrincipal;
import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class ModificacaoContaTeste {

	private BotComando comando = new BotComando();

	@Test
	public void modificarContaExistente() {
		try {
			this.comando.modificarConta(idTelegram, cpfNovo, emailNovo);
			this.comando.modificarConta(idTelegram, cpfPrincipal, emailPrincipal);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void modificarContaInexistente() throws ContaInexistenteExcecao {
		this.comando.modificarConta(idInvalido, cpfNovo, emailNovo);
	}

}
