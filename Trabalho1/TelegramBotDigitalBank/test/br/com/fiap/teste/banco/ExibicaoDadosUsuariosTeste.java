package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.cpfDependente;
import static br.com.fiap.teste.ConstantesTeste.cpfPrincipal;
import static br.com.fiap.teste.ConstantesTeste.emailDependente;
import static br.com.fiap.teste.ConstantesTeste.emailPrincipal;
import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.nomeDependente;
import static br.com.fiap.teste.ConstantesTeste.nomePrincipal;
import static br.com.fiap.teste.ConstantesTeste.sobrenomeDependente;
import static br.com.fiap.teste.ConstantesTeste.sobrenomePrincipal;
import static br.com.fiap.teste.ConstantesTeste.telefoneDependente;
import static br.com.fiap.teste.ConstantesTeste.telefonePrincipal;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class ExibicaoDadosUsuariosTeste {

	private BotComando comando = new BotComando();

	@Test
	public void listarUsuariosEDependentes() {
		try {
			List<Usuario> listaUsuarios = this.comando.listarUsuariosEDependentes(idTelegram);

			Assert.assertEquals(2, listaUsuarios.size());

			Usuario usuarioPrincipal = listaUsuarios.get(0);
			Assert.assertEquals(nomePrincipal + " " + sobrenomePrincipal, usuarioPrincipal.getNome());
			Assert.assertEquals(telefonePrincipal, usuarioPrincipal.getTelefone());
			Assert.assertEquals(cpfPrincipal, usuarioPrincipal.getCpf());
			Assert.assertEquals(emailPrincipal, usuarioPrincipal.getEmail());

			Usuario usuarioDependente = listaUsuarios.get(1);
			Assert.assertEquals(nomeDependente + " " + sobrenomeDependente, usuarioDependente.getNome());
			Assert.assertEquals(telefoneDependente, usuarioDependente.getTelefone());
			Assert.assertEquals(cpfDependente, usuarioDependente.getCpf());
			Assert.assertEquals(emailDependente, usuarioDependente.getEmail());
		} catch (ContaInexistenteExcecao e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void listarUsuariosEDependentesContaInvalida() throws ContaInexistenteExcecao {
		this.comando.listarUsuariosEDependentes(idInvalido);
	}

}
