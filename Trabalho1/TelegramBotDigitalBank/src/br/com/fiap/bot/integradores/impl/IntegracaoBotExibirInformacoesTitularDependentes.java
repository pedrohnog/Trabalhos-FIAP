package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.MoedaUtil;

public class IntegracaoBotExibirInformacoesTitularDependentes extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuilder retorno = new StringBuilder();
		List<Usuario> usuarios;
		BotComando botComando = new BotComando();
		try {
			usuarios = botComando.listarUsuariosEDependentes(usuario.id());
			for (Usuario usuario2 : usuarios) {
				retorno.append(usuario2.getConta().getNumero() + " - " + usuario2.getCpf() + " - " +
						usuario2.getNome() + " - " + MoedaUtil.conveterMoedaBr(usuario2.getConta().getSaldo()) + "\n");
			}
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");
		}
		return retorno.toString();
		//TODO verificar com o rafa
		//return "CONTA - CPF - NOME - SALDO!";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return "CONTA - CPF - NOME - SALDO!";
	}

}
