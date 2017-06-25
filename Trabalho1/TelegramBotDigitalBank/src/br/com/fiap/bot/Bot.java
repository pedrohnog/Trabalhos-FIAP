package br.com.fiap.bot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.constantes.EnumTipoIntegracaoBot;
import br.com.fiap.bot.integradores.IntegracaoBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.BotUtil;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe responsável pelo tratamento de integração entre usuário, bot e banco
 *
 */
public class Bot {

	/**
	 * TOKEN de acesso ao Bot criado no Telegram
	 */
	private static final String TOKEN_ACESSO = "429980480:AAFwbHwaWaxa9BaLyOs_Er5FLPm37JRI-KQ";

	/**
	 * Mapa responsável por armazenar o último comando enviado pelo usuário
	 */
	public static Map<Long, EnumComandosBot> ULTIMO_COMANDO_DO_USUARIO = new HashMap<>();

	/**
	 * Executa a inicialização do bot e realiza o 'polling' de mensagens enviadas
	 */
	public void executarBot() {
		TelegramBot bot = TelegramBotAdapter.build(TOKEN_ACESSO);
		int m = 0;

		while (true) {
			List<Update> updates = bot.execute(new GetUpdates().limit(100).offset(m)).updates();

			for (Update update : updates) {
				m = update.updateId() + 1;
				String mensagemRetorno = "";
				if (update.message() != null) {
					bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					try {
						mensagemRetorno = tratarMensagemBot(update);
					} catch (Exception e) {
						mensagemRetorno = "Desculpe, não entendi... digite /ajuda para obter a lista de comandos conhecidos.";
					}
					bot.execute(new SendMessage(update.message().chat().id(), mensagemRetorno.toString()));
				}

			}
		}
	}

	/**
	 * Identifica o tipo do comando e realizar o tratamento
	 * 
	 * @param update Requisicao recebida pelo telegram
	 * 
	 * @return Mensagem de resposta para o usuário
	 */
	private String tratarMensagemBot(Update update) {
		String mensagemRetorno = "";

		Chat usuario = update.message().chat();
		String mensagemRecebida = update.message().text();

		if (BotUtil.isMensagemComComando(update.message())) {
			mensagemRetorno = tratarMensagemBotComComando(usuario, mensagemRecebida);
		} else if (BotUtil.botAguardandoRespostaDoUsuario(usuario)) {
			mensagemRetorno = tratarRespostaEnviada(usuario, mensagemRecebida);
		} else {
			mensagemRetorno = "Desculpe, não entendi... digite /ajuda para obter a lista de comandos conhecidos.";

		}

		BotUtil.salvarMensagens(usuario, mensagemRecebida, mensagemRetorno);

		return mensagemRetorno;
	}

	/**
	 * Realiza o tratamento da resposta enviada pelo Usuário
	 * 
	 * @param usuario Objeto Chat do Telegram
	 * @param mensagemRecebida Mensagem enviada através do Telegram pelo usuário
	 * 
	 * @return mensagemRetorno Mensagem de resposta ao usuário
	 */
	private String tratarRespostaEnviada(Chat usuario, String mensagemRecebida) {
		String mensagemRetorno;
		IntegracaoBotSolicitacao resposta = (IntegracaoBotSolicitacao) BotUtil.definirClasseIntegracao(ULTIMO_COMANDO_DO_USUARIO.get(usuario.id()));

		if (resposta.validarResposta(mensagemRecebida)) {
			mensagemRetorno = resposta.integrarBanco(mensagemRecebida, usuario);
			BotUtil.removerHistoricoIntegracaoUsuario(usuario.id());
		} else {
			mensagemRetorno = resposta.informarErroNaResposta();
		}
		return mensagemRetorno;
	}

	/**
	 * Realiza o tratamento da mensagem enviada de acordo com o comando solicitado
	 * 
	 * @param usuario Objeto Chat do Telegram
	 * @param mensagemRecebida Mensagem enviada através do Telegram pelo usuário
	 * 
	 * @return mensagemRetorno Mensagem de resposta ao usuário
	 */
	private String tratarMensagemBotComComando(Chat usuario, String mensagemRecebida) {
		String mensagemRetorno;

		if (BotUtil.isComandoSimples(mensagemRecebida)) {
			IntegracaoBot resposta = BotUtil.definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			mensagemRetorno = resposta.tratarPrimeiraInteracao(usuario);

			if (EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida).getEnumTipoIntegracaoBot().equals(EnumTipoIntegracaoBot.SOLICITACAO)) {
				BotUtil.adicionarHistoricoIntegracaoUsuario(usuario.id(), EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			} else {
				BotUtil.removerHistoricoIntegracaoUsuario(usuario.id());
			}
		} else {
			List<String> comandosEnviados = BotUtil.encontrarComandosNaMensagem(mensagemRecebida);

			if (comandosEnviados.isEmpty()) {
				mensagemRetorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_DESCONHECIDO");
			} else if (comandosEnviados.size() > 1) {
				mensagemRetorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_DUPLICADO");
			} else {
				String comando = comandosEnviados.get(0);
				String mensagemSemComando = BotUtil.retirarComandoDaMensagem(mensagemRecebida, comando);

				if (EnumTipoIntegracaoBot.SOLICITACAO.equals(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando).getEnumTipoIntegracaoBot())) {
					IntegracaoBotSolicitacao resposta = (IntegracaoBotSolicitacao) BotUtil.definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					if (resposta.validarResposta(mensagemSemComando)) {
						mensagemRetorno = resposta.integrarBanco(mensagemSemComando, usuario);
						BotUtil.removerHistoricoIntegracaoUsuario(usuario.id());
					} else {
						mensagemRetorno = resposta.informarErroNaResposta();
						BotUtil.adicionarHistoricoIntegracaoUsuario(usuario.id(), EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					}
				} else {
					IntegracaoBot resposta = BotUtil.definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					mensagemRetorno = resposta.tratarPrimeiraInteracao(usuario);
				}
			}
		}
		return mensagemRetorno;
	}
	
}
