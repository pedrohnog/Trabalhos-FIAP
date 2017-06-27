package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.MoedaUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de consulta do saldo devedor do empréstimo do Bot
 *
 */
public class IntegracaoBotConsultaSaldoDevedorEmprestimo extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		EmprestimoDetalhe emprestimoDetalhe = botComando.verificarSaldoDevedorPrazoEmprestimo(usuario.id());

		if (emprestimoDetalhe.getSaldoDevedor() != 0.0d) {
			retorno.append(String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("CONSULTAR_SALDO_DEVEDOR_EMPRESTIMO"), MoedaUtil.conveterMoedaBr(emprestimoDetalhe.getSaldoDevedor()), emprestimoDetalhe.getPrazoPagamento()));
		} else {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_SEM_PARCELAS"));
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
