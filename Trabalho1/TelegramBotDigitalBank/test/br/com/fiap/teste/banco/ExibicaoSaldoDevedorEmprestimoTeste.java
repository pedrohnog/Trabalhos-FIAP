package br.com.fiap.teste.banco;

import static br.com.fiap.teste.ConstantesTeste.idTelegram;
import static br.com.fiap.teste.ConstantesTeste.prazoEmprestimo;
import static br.com.fiap.teste.ConstantesTeste.saldoDevedor;

import org.junit.Assert;
import org.junit.Test;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.dados.EmprestimoDetalhe;

public class ExibicaoSaldoDevedorEmprestimoTeste {

	private BotComando comando = new BotComando();

	@Test
	public void listarSaldoDevedor() {
		EmprestimoDetalhe emprestimoDetalhe = this.comando.verificarSaldoDevedorPrazoEmprestimo(idTelegram);

		Assert.assertEquals(saldoDevedor, emprestimoDetalhe.getSaldoDevedor(), 0d);
		Assert.assertEquals(prazoEmprestimo, emprestimoDetalhe.getPrazoPagamento());
	}

}
