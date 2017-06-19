package br.com.fiap.banco;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.banco.comandos.ContaComando;
import br.com.fiap.banco.comandos.OperacoesComando;
import br.com.fiap.banco.comandos.TransacaoComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;

public class TempMain {

	public static void main(String[] args) {
		TempMain main = new TempMain();
		int opcao = Integer.valueOf(JOptionPane.showInputDialog("Digite a opção de teste"));
		switch (opcao) {
		case 1:
			main.criarConta();
			break;
		case 2:
			main.modificarContaExistente();
			break;
		case 3:
			main.modificarContaInexistente();
			break;
		case 4:
			main.incluirDependente();
			break;
		case 5:
			main.depositar();
			break;
		case 6:
			main.sacar();
			break;
		case 7:
			main.solicitarSaldo();
			break;
		case 8:
			main.solicitarExtrato();
			break;
		case 9:
			main.solicitarLancamentos();
			break;
		case 10:
			main.solicitarRetiradas();
			break;
		case 11:
			main.solicitarTarifas();
			break;
		default:
			System.err.println("Opção inválida");
			break;
		}
	}

	public void criarConta() {
		ContaComando contaComando = new ContaComando();
		contaComando.criarConta(1234, "Teste 1", "Teste", "1112345678", "12345678900", "teste1@teste.com.br");
	}

	public void modificarContaExistente() {
		ContaComando contaComando = new ContaComando();
		try {
			contaComando.alterarConta(1234, "98765432100", "novoteste@teste.com.br");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void modificarContaInexistente() {
		ContaComando contaComando = new ContaComando();
		try {
			contaComando.alterarConta(9999, "98765432100", "novoteste@teste.com.br");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void incluirDependente() {
		ContaComando contaComando = new ContaComando();
		try {
			contaComando.incluirDependente(1234, "Teste 2", "Teste", "1187654321", "01010101099", "teste2@teste.com.br",
					"98765432100");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void depositar() {
		OperacoesComando operacoesComando = new OperacoesComando();
		try {
			operacoesComando.realizarDeposito(1234, 100000d);
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void sacar() {
		OperacoesComando operacoesComando = new OperacoesComando();
		try {
			operacoesComando.realizarSaque(1234, 50d);
		} catch (SaldoInsuficienteExcecao e) {
			System.err.println("SALDO INSUFICIENTE");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void solicitarSaldo() {
		OperacoesComando operacoesComando = new OperacoesComando();
		try {
			System.out.println(operacoesComando.verificarSaldo(1234));
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void solicitarExtrato() {
		//FIXME Erro de Lazy
		OperacoesComando operacoesComando = new OperacoesComando();
		try {
			List<Transacao> transacoes = operacoesComando.verificacaoExtrato(1234);

			for (Transacao transacao : transacoes) {
				System.out.println(transacao.getDataHora() + " "
						+ TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " "
						+ transacao.getValor());
			}
		} catch (SaldoInsuficienteExcecao e) {
			System.err.println("SALDO INSUFICIENTE");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void solicitarLancamentos() {
		TransacaoComando transacaoComando = new TransacaoComando();
		try {
			TransacaoDetalhe transacaoDetalhe = transacaoComando.listarLancamentos(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " "
						+ TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " "
						+ transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void solicitarRetiradas() {
		TransacaoComando transacaoComando = new TransacaoComando();
		try {
			TransacaoDetalhe transacaoDetalhe = transacaoComando.listarRetiradas(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " "
						+ TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " "
						+ transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	public void solicitarTarifas() {
		TransacaoComando transacaoComando = new TransacaoComando();
		try {
			TransacaoDetalhe transacaoDetalhe = transacaoComando.listarTarifas(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " "
						+ TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " "
						+ transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

}
