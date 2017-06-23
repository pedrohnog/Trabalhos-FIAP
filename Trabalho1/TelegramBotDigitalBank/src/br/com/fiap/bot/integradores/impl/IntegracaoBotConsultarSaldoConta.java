package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.MoedaUtil;

/**
 * Classe responsável pelo comando de consulta do saldo da conta do Bot
 *
 */
public class IntegracaoBotConsultarSaldoConta extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuffer retorno = new StringBuffer();
		BotComando botComando = new BotComando();
		Double saldoConta;
		try {
			saldoConta = botComando.verificarSaldo(usuario.id());
			retorno.append("SALDO NA CONTA:").append(MoedaUtil.conveterMoedaBr(saldoConta));
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
