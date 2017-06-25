package br.com.fiap.bot.util;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;

import br.com.fiap.bot.Bot;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.dao.impl.MensagemIntegracaoDao;
import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.bot.integradores.IntegracaoBot;

/**
 * Classe utilit�ria para auxiliar nos tratamentos realizados pelo Bot
 *
 */
public class BotUtil {

	/**
	 * Retira o comando que foi recebido na mensagem
	 * 
	 * @param mensagemRecebida Mensagem recebida
	 * @param comando Comando que deve ser removido
	 * 
	 * @return Mensagem recebida sem o comando
	 */
	public static String retirarComandoDaMensagem(String mensagemRecebida, String comando) {
		return mensagemRecebida.replaceAll(comando, "");
	}

	/**
	 * Encontra todos os comandos conhecidos pelo bot enviados pelo usu�rio
	 * 
	 * @param mensagemRecebida Mensagem enviada pelo usu�rio
	 * 
	 * @return Lista com os comandos encontrados
	 */
	public static List<String> encontrarComandosNaMensagem(String mensagemRecebida) {
		String[] palavras = mensagemRecebida.split(" ");
		List<String> comandosEncontratos = new ArrayList<>();
		for (int i = 0; i < palavras.length; i++) {
			if (palavras[i].startsWith("/") && EnumComandosBot.LISTA_COMANDO_INTERACOES.containsKey(palavras[i].trim())) {
				comandosEncontratos.add(palavras[i].trim());
			}
		}
		return comandosEncontratos;
	}

	/**
	 * Valida se a mensagem recebida � apenas o comando solicitado (ex: /criar_conta)
	 * 
	 * @param mensagemRecebida Mensagem enviada pelo usu�rio no Telegram
	 * 
	 * @return <code>true</code> se a mensagem for um comando simples, se n�o <code>false</code>
	 */
	public static boolean isComandoSimples(String mensagemRecebida) {
		return EnumComandosBot.LISTA_COMANDO_INTERACOES.containsKey(mensagemRecebida);
	}

	/**
	 * Valida se o bot esta aguardando uma resposta do usu�rio
	 * 
	 * @param usuario Usu�rio do Telegram
	 * 
	 * @return <code>true</code> se estiver aguardando uma resposta, se n�o <code>false</code>
	 */
	public static boolean botAguardandoRespostaDoUsuario(Chat usuario) {
		return Bot.ULTIMO_COMANDO_DO_USUARIO.containsKey(usuario.id());
	}

	/**
	 * Valida se no comando enviado cont�m uma entidade do tipo "bot_command"
	 * 
	 * @param message Objeto Message do Telegram
	 * 
	 * @return <code>true</code> se na mensagem cont�m pelo menos um comando, se n�o <code>false</code>
	 */
	public static boolean isMensagemComComando(Message message) {
		boolean mensagemComComando = false;

		if (message.entities() != null) {
			for (int i = 0; i < message.entities().length; i++) {
				if (message.entities()[i].type().name().equals(ConstantesBot.BOT_COMMAND)) {
					mensagemComComando = true;
					break;
				}
			}
		}

		return mensagemComComando;
	}

	/**
	 * M�todo respons�vel por definir qual ser� a classe de valida��o e integra��o conforme os comandos
	 * 
	 * @param EnumComandosBot Comando que foi executado
	 * 
	 * @return Inst�ncia da classe de acordo com o comando executado
	 */
	public static IntegracaoBot definirClasseIntegracao(EnumComandosBot ultimoComandoExecutado) {
		IntegracaoBot retorno = null;
		
		try {
			return (IntegracaoBot) ultimoComandoExecutado.getClasseComando().newInstance();
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	/**
	 * M�todo respons�vel por remover os comandos do hist�rico do usu�rio
	 * 
	 * @param id Id do Telegram
	 */
	public static void removerHistoricoIntegracaoUsuario(Long id) {
		Bot.ULTIMO_COMANDO_DO_USUARIO.remove(id);
	}

	/**
	 * M�todo respons�vel por adicionar ou atualizar o �ltimo comando executado pelo usu�rio
	 * 
	 * @param id Id do Telegram
	 * @param comando Comando que ser� adicionado/atualizado no hist�rio do usu�rio
	 */
	public static void adicionarHistoricoIntegracaoUsuario(Long id, EnumComandosBot comando) {
		Bot.ULTIMO_COMANDO_DO_USUARIO.put(id, comando);
	}

	/**
	 * Metodo respons�vel persistir no BD todas as intera��es do usu�rio
	 * 
	 * @param usuario Id do Telegram
	 * @param mensagemRecebida Mensagem enviada pelo usu�rio para o Bot
	 * @param mensagemEnviada Mensagem enviada pelo Bot para o usu�rio
	 */
	public static void salvarMensagens(Chat usuario, String mensagemRecebida, String mensagemEnviada) {
		MensagemIntegracao mensagemIntegracao = new MensagemIntegracao();
		MensagemIntegracaoDao mensagemIntegracaoDao = new MensagemIntegracaoDao();

		String nomeUsuario = usuario.firstName().replaceAll("[^A-Za-z0-9]", "");
		
		mensagemIntegracao.setIdTelegram(usuario.id());
		mensagemIntegracao.setNomeTelegram(nomeUsuario);
		mensagemIntegracao.setMensagemRecebida(mensagemRecebida);
		mensagemIntegracao.setMensagemEnviada(mensagemEnviada);

		mensagemIntegracaoDao.adicionar(mensagemIntegracao);
		mensagemIntegracaoDao.close();
	}
	
}
