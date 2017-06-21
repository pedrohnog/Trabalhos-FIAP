package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.cpfDependente;
import static br.com.fiap.teste.ConstantesTeste.emailDependente;
import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.nomeDependente;
import static br.com.fiap.teste.ConstantesTeste.sobrenomeDependente;
import static br.com.fiap.teste.ConstantesTeste.telefoneDependente;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.UsuarioDuplicadoExcecao;

public class InclusaoDependenteTeste {

	private BotComando comando = new BotComando();

	@Test(expected = UsuarioDuplicadoExcecao.class)
	public void incluirDependenteDuplicado() throws UsuarioDuplicadoExcecao {
		try {
			this.comando.incluirDependente(idTelegram, nomeDependente, sobrenomeDependente, telefoneDependente, cpfDependente, emailDependente);
			this.comando.incluirDependente(idTelegram, nomeDependente, sobrenomeDependente, telefoneDependente, cpfDependente, emailDependente);
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void incluirDependenteContaInvalida() throws ContaInexistenteExcecao {
		try {
			this.comando.incluirDependente(idInvalido, nomeDependente, sobrenomeDependente, telefoneDependente, cpfDependente, emailDependente);
		} catch (UsuarioDuplicadoExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

}
