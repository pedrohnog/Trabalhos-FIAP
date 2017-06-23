package br.com.fiap.bot.integradores.impl;

import java.util.List;

import com.pengrad.telegrambot.model.Chat;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.PagamentoEmprestimoExcecao;
import br.com.fiap.bot.constantes.ConstantesBot;
import br.com.fiap.bot.integradores.IntegracaoBotSolicitacao;
import br.com.fiap.bot.util.DataUtil;
import br.com.fiap.bot.util.MoedaUtil;

/**
 * Classe responsável pelo comando de pagamento de uma parcela do empréstimo do Bot
 *
 */
public class IntegracaoBotPagarParcelaEmprestimo extends IntegracaoBotSolicitacao {

	public IntegracaoBotPagarParcelaEmprestimo() {
		super("", "xxx (ex: 12)");
	}

	@Override
	public Boolean validarResposta(String resposta) {
		boolean respostaOk = true;
		if (resposta != null) {
			resposta = resposta.trim();
			try {
				if (!(Integer.valueOf(resposta) > 0D)) {
					respostaOk = false;
				}
			} catch (NumberFormatException e) {
				respostaOk = false;
			}
		}
		return respostaOk;
	}

	@Override
	public String integrarBanco(String resposta, Chat usuario) {
		BotComando botComando = new BotComando();
		StringBuffer retorno = new StringBuffer();

		try {
			botComando.pagarParcelaEmprestimo(usuario.id(), Integer.valueOf(resposta));
			retorno.append("Parcela paga com sucesso!");
		} catch (ContaInexistenteExcecao e) {
			retorno.append("Você ainda não tem uma conta, para criar sua conta digite /criar_conta");
		} catch (PagamentoEmprestimoExcecao e) {
			retorno.append("Não é possível realizar o pagamento da parcela selecionada. A parcela não existe ou já está paga.");
		}

		return retorno.toString();
	}

	@Override
	public String tratarPrimeiraInteracao(Chat usuario) {
		StringBuffer retorno = new StringBuffer();

		BotComando botComando = new BotComando();
		List<Emprestimo> parcelas = botComando.listarEmprestimosNaoPagos(usuario.id());

		if (parcelas.isEmpty()) {
			retorno.append("Nesta consulta prévia, não encontramos parcelas pendentes para pagamento.")
					.append("Caso tenha certeza que tem uma parcela pendente, informe o número dela para tentarmos realizar o pagamento");
		} else {
			retorno.append("Informe o numero da parcela que gostaria de pagar de acordo com o retorno abaixo")
					.append(ConstantesBot.PULAR_DUAS_LINHA).append("Segue as parcelas que poderão ser pagas:")
					.append(ConstantesBot.PULAR_DUAS_LINHA);
			parcelas.forEach(p -> retorno.append(p.getNumeroParcela()).append(" - ")
					.append(DataUtil.conveterDataPadraoBr(p.getDataVencimento())).append(" - ")
					.append(MoedaUtil.conveterMoedaBr(p.getValorParcela())).append(ConstantesBot.PULAR_UMA_LINHA));
		}

		return retorno.toString();
	}

}
