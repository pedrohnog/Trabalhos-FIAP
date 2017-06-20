package br.com.fiap.banco;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.banco.comandos.BotComando;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;

class TempMain {
	
	private BotComando comando = new BotComando();

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
		case 12:
			main.listarUsuarioDependente();
			break;
		case 13:
			main.solicitarEmprestimo();
			break;
		case 14:
			main.buscarDadosDevedorEmprestimo();
			break;
		default:
			System.err.println("Opção inválida");
			break;
		}
	}

	private void criarConta() {
		this.comando.criarConta(1234, "Teste 1", "Teste", "1112345678", "12345678900", "teste1@teste.com.br");
	}

	private void modificarContaExistente() {
		try {
			this.comando.modificarConta(1234, "98765432100", "novoteste@teste.com.br");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void modificarContaInexistente() {
		try {
			this.comando.modificarConta(9999, "98765432100", "novoteste@teste.com.br");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void incluirDependente() {
		try {
			this.comando.incluirDependente(1234, "Teste 2", "Teste", "1187654321", "01010101099", "teste2@teste.com.br");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void depositar() {
		try {
			this.comando.realizarDeposito(1234, 100000d);
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void sacar() {
		try {
			this.comando.realizarSaque(1234, 50d);
		} catch (SaldoInsuficienteExcecao e) {
			System.err.println("SALDO INSUFICIENTE");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void solicitarSaldo() {
		try {
			System.out.println(this.comando.verificarSaldo(1234));
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void solicitarExtrato() {
		try {
			List<Transacao> transacoes = this.comando.verificarExtrato(1234);

			for (Transacao transacao : transacoes) {
				System.out.println(transacao.getDataHora() + " " + TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " " + transacao.getValor());
			}
		} catch (SaldoInsuficienteExcecao e) {
			System.err.println("SALDO INSUFICIENTE");
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void solicitarLancamentos() {
		try {
			TransacaoDetalhe transacaoDetalhe = this.comando.listarLancamentos(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " " + TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " " + transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void solicitarRetiradas() {
		try {
			TransacaoDetalhe transacaoDetalhe = this.comando.listarRetiradas(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " " + TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " " + transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void solicitarTarifas() {
		try {
			TransacaoDetalhe transacaoDetalhe = this.comando.listarTarifas(1234);
			for (Transacao transacao : transacaoDetalhe.getTransacoes()) {
				System.out.println(transacao.getDataHora() + " " + TipoTransacao.getTipoTransacao(transacao.getTipoTransacao()).toString() + " " + transacao.getValor());
			}
			System.out.println("SOMATORIO: " + transacaoDetalhe.getSomatorio());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("CONTA INEXISTENTE");
		}
	}

	private void listarUsuarioDependente() {
		try {
			List<Usuario> usuarios = this.comando.listarUsuariosEDependentes(1234);
			for (Usuario usuario : usuarios) {
				System.out.println(usuario.getNome() + " " + usuario.getCpf() + " " + usuario.getTelefone() + " " + TipoUsuario.getTipoUsuario(usuario.getTipoUsuario()));
			}
		} catch (ContaInexistenteExcecao e) {
			System.err.println("USUARIO NAO TEM CONTA");
		}
	}
	
	private void solicitarEmprestimo() {
		try {
			this.comando.solicitarEmprestimo(1234, 2000d, 36);
		} catch (ContaInexistenteExcecao e) {
			System.err.println("USUARIO NAO TEM CONTA");
		} catch (ValorEmprestimoExcedidoExcecao e) {
			System.err.println("VALOR EMPRESTIMO EXCEDE SALDO");
		} catch (PrazoEmprestimoExcedidoExcecao e) {
			System.err.println("PRAZO SOLICITADO EXCEDE LIMITE");
		} catch (SaldoInsuficienteExcecao e) {
			System.err.println("SALDO INSUFICIENTE");
		}
	}
	
	private void buscarDadosDevedorEmprestimo() {
		try {
			EmprestimoDetalhe emprestimoDetalhe = this.comando.verificarSaldoDevedorPrazoEmprestimo(1234);
			System.out.println("SALDO DEVEDOR = " + emprestimoDetalhe.getSaldoDevedor());
			System.out.println("PRAZO PAGAMENTO = " + emprestimoDetalhe.getPrazoPagamento());
		} catch (ContaInexistenteExcecao e) {
			System.err.println("USUARIO NAO TEM CONTA");
		}
	}

}
