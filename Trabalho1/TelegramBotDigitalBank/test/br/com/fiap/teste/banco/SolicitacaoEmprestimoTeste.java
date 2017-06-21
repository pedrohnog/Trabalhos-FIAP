package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idInvalido;
import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.prazoEmprestimo;
import static br.com.fiap.teste.ConstantesTeste.prazoEmprestimoInvalido;
import static br.com.fiap.teste.ConstantesTeste.valorEmprestimo;
import static br.com.fiap.teste.ConstantesTeste.valorEmprestimoInvalido;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;

public class SolicitacaoEmprestimoTeste {

	private BotComando comando = new BotComando();

	@Test(expected = EmprestimoAbertoExcecao.class)
	public void solicitarEmprestimo() throws EmprestimoAbertoExcecao {
		try {
			this.comando.solicitarEmprestimo(idTelegram, valorEmprestimo, prazoEmprestimo);
			this.comando.solicitarEmprestimo(idTelegram, valorEmprestimo, prazoEmprestimo);
		} catch (ContaInexistenteExcecao | ValorEmprestimoExcedidoExcecao | PrazoEmprestimoExcedidoExcecao | SaldoInsuficienteExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

	@Test(expected = ContaInexistenteExcecao.class)
	public void solicitarEmprestimoContaInvalida() throws ContaInexistenteExcecao {
		try {
			this.comando.solicitarEmprestimo(idInvalido, valorEmprestimo, prazoEmprestimo);
		} catch (ValorEmprestimoExcedidoExcecao | PrazoEmprestimoExcedidoExcecao | SaldoInsuficienteExcecao | EmprestimoAbertoExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

	@Test(expected = ValorEmprestimoExcedidoExcecao.class)
	public void solicitarEmprestimoValorInvalido() throws ValorEmprestimoExcedidoExcecao {
		try {
			this.comando.solicitarEmprestimo(idTelegram, valorEmprestimoInvalido, prazoEmprestimo);
		} catch (ContaInexistenteExcecao | PrazoEmprestimoExcedidoExcecao | SaldoInsuficienteExcecao | EmprestimoAbertoExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

	@Test(expected = PrazoEmprestimoExcedidoExcecao.class)
	public void solicitarEmprestimoPrazoInvalido() throws PrazoEmprestimoExcedidoExcecao {
		try {
			this.comando.solicitarEmprestimo(idTelegram, valorEmprestimo, prazoEmprestimoInvalido);
		} catch (ContaInexistenteExcecao | ValorEmprestimoExcedidoExcecao | SaldoInsuficienteExcecao | EmprestimoAbertoExcecao e) {
			Assert.fail(e.getMessage());
		}
		Assert.fail();
	}

}
