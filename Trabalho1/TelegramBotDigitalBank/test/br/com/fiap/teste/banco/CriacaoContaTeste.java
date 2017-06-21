package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.cpfPrincipal;
import static br.com.fiap.teste.ConstantesTeste.emailPrincipal;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.nomePrincipal;
import static br.com.fiap.teste.ConstantesTeste.sobrenomePrincipal;
import static br.com.fiap.teste.ConstantesTeste.telefonePrincipal;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;

public class CriacaoContaTeste {

	private BotComando comando = new BotComando();

	@Test
	public void criarConta() {
		try {
			this.comando.criarConta(idTelegram, nomePrincipal, sobrenomePrincipal, telefonePrincipal, cpfPrincipal, emailPrincipal);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
