package br.com.fiap.bot.integradores.impl;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.bot.integradores.IntegracaoBotConsulta;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo comando de start do Bot
 *
 */
public class IntegracaoBotStart extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		return "";
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		String nomeUsuario = usuario.firstName().replaceAll("[^A-Za-z0-9]", "");
		
		return String.format(PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_START"), nomeUsuario);
	}

}
