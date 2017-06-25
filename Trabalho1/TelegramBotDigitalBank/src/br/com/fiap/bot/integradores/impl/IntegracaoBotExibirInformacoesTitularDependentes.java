package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de exibição dos dados de titulares e dependentes do Bot
 *
 */
public class IntegracaoBotExibirInformacoesTitularDependentes extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		StringBuilder retorno = new StringBuilder();
		List<Usuario> usuarios;
		BotComando botComando = new BotComando();
		try {
			usuarios = botComando.listarUsuariosEDependentes(usuario.id());

			usuarios.forEach(u -> retorno.append(u.getNome()).append(" - ").append(u.getEmail()).append(" - ").append(u.getCpf()).append(" - ").append(u.getTelefone()).append(ConstantesBot.PULAR_UMA_LINHA));

		} catch (ContaInexistenteExcecao e) {
			retorno.append(PropriedadesUtil.carregarMensagensIntegracao().getProperty("RETORNO_CONTA_INEXISTENTE"));
		}
		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		return this.integrarBanco("", usuario);
	}

}
