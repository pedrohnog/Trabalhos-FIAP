package br.com.fiap.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.constantes.EnumComandosBot;
import br.com.fiap.bot.constantes.EnumTipoIntegracaoBot;
import br.com.fiap.bot.dao.impl.MensagemIntegracaoDao;
import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.bot.integradores.IntegracaoBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.PropriedadesUtil;

/**
 * Classe respons�vel pelo tratamento de integra��o entre usu�rio, bot e banco
 *
 */
public class Bot {

	/**
	 * TOKEN de acesso ao Bot criado no Telegram
	 */
	private static final String TOKEN_ACESSO = "429980480:AAFwbHwaWaxa9BaLyOs_Er5FLPm37JRI-KQ";

	/**
	 * Mapa respons�vel por armazenar o �ltimo comando enviado pelo usu�rio
	 */
	public static Map<Long, EnumComandosBot> ULTIMO_COMANDO_DO_USUARIO = new HashMap<>();

	/**
	 * Executa a inicializa��o do bot e realiza o 'polling' de mensagens enviadas
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
						mensagemRetorno = "Desculpe, n�o entendi... digite /ajuda para obter a lista de comandos conhecidos.";
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
	 * @return Mensagem de resposta para o usu�rio
	 */
	private String tratarMensagemBot(Update update) {
		String mensagemRetorno = "";

		Chat usuario = update.message().chat();
		String mensagemRecebida = update.message().text();

		if (isMensagemComComando(update.message())) {
			mensagemRetorno = tratarMensagemBotComComando(usuario, mensagemRecebida);
		} else if (botAguardandoRespostaDoUsuario(usuario)) {
			mensagemRetorno = tratarRespostaEnviada(usuario, mensagemRecebida);
		} else {
			mensagemRetorno = "Desculpe, n�o entendi... digite /ajuda para obter a lista de comandos conhecidos.";

		}

		salvarMensagens(usuario, mensagemRecebida, mensagemRetorno);

		return mensagemRetorno;
	}

	/**
	 * Realiza o tratamento da resposta enviada pelo Usu�rio
	 * 
	 * @param usuario Objeto Chat do Telegram
	 * @param mensagemRecebida Mensagem enviada atrav�s do Telegram pelo usu�rio
	 * 
	 * @return mensagemRetorno Mensagem de resposta ao usu�rio
	 */
	private String tratarRespostaEnviada(Chat usuario, String mensagemRecebida) {
		String mensagemRetorno;
		IntegracaoBotSolicitacao resposta = (IntegracaoBotSolicitacao) definirClasseIntegracao(ULTIMO_COMANDO_DO_USUARIO.get(usuario.id()));

		if (resposta.validarResposta(mensagemRecebida)) {
			mensagemRetorno = resposta.integrarBanco(mensagemRecebida, usuario);
			removerHistoricoIntegracaoUsuario(usuario.id());
		} else {
			mensagemRetorno = resposta.informarErroNaResposta();
		}
		return mensagemRetorno;
	}

	/**
	 * Realiza o tratamento da mensagem enviada de acordo com o comando solicitado
	 * 
	 * @param usuario Objeto Chat do Telegram
	 * @param mensagemRecebida Mensagem enviada atrav�s do Telegram pelo usu�rio
	 * 
	 * @return mensagemRetorno Mensagem de resposta ao usu�rio
	 */
	private String tratarMensagemBotComComando(Chat usuario, String mensagemRecebida) {
		String mensagemRetorno;

		if (isComandoSimples(mensagemRecebida)) {
			IntegracaoBot resposta = definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			mensagemRetorno = resposta.tratarPrimeiraInteracao(usuario);

			if (EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida).getEnumTipoIntegracaoBot().equals(EnumTipoIntegracaoBot.SOLICITACAO)) {
				adicionarHistoricoIntegracaoUsuario(usuario.id(), EnumComandosBot.LISTA_COMANDO_INTERACOES.get(mensagemRecebida));
			} else {
				removerHistoricoIntegracaoUsuario(usuario.id());
			}
		} else {
			List<String> comandosEnviados = encontrarComandosNaMensagem(mensagemRecebida);

			if (comandosEnviados.isEmpty()) {
				mensagemRetorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_DESCONHECIDO");
			} else if (comandosEnviados.size() > 1) {
				mensagemRetorno = PropriedadesUtil.carregarMensagensIntegracao().getProperty("COMANDO_DUPLICADO");
			} else {
				String comando = comandosEnviados.get(0);
				String mensagemSemComando = retirarComandoDaMensagem(mensagemRecebida, comando);

				if (EnumTipoIntegracaoBot.SOLICITACAO.equals(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando).getEnumTipoIntegracaoBot())) {
					IntegracaoBotSolicitacao resposta = (IntegracaoBotSolicitacao) definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					if (resposta.validarResposta(mensagemSemComando)) {
						mensagemRetorno = resposta.integrarBanco(mensagemSemComando, usuario);
						removerHistoricoIntegracaoUsuario(usuario.id());
					} else {
						mensagemRetorno = resposta.informarErroNaResposta();
						adicionarHistoricoIntegracaoUsuario(usuario.id(), EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					}
				} else {
					IntegracaoBot resposta = definirClasseIntegracao(EnumComandosBot.LISTA_COMANDO_INTERACOES.get(comando));
					mensagemRetorno = resposta.tratarPrimeiraInteracao(usuario);
				}
			}
		}
		return mensagemRetorno;
	}
	
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
		return ULTIMO_COMANDO_DO_USUARIO.containsKey(usuario.id());
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
		ULTIMO_COMANDO_DO_USUARIO.remove(id);
	}

	/**
	 * M�todo respons�vel por adicionar ou atualizar o �ltimo comando executado pelo usu�rio
	 * 
	 * @param id Id do Telegram
	 * @param comando Comando que ser� adicionado/atualizado no hist�rio do usu�rio
	 */
	public static void adicionarHistoricoIntegracaoUsuario(Long id, EnumComandosBot comando) {
		ULTIMO_COMANDO_DO_USUARIO.put(id, comando);
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
