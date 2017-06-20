package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.dados.EmprestimoDetalhe;
import br.com.fiap.banco.dados.TransacaoDetalhe;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;
import br.com.fiap.banco.excecao.EmprestimoAbertoExcecao;
import br.com.fiap.banco.excecao.PrazoEmprestimoExcedidoExcecao;
import br.com.fiap.banco.excecao.SaldoInsuficienteExcecao;
import br.com.fiap.banco.excecao.ValorEmprestimoExcedidoExcecao;

public class BotComando {

	public synchronized void criarConta(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) {
		ContaComando contaComando = new ContaComando();
		contaComando.criarConta(idTelegram, nome, sobrenome, telefone, cpf, email);
	}

	public synchronized void modificarConta(long idTelegram, String cpf, String email) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		contaComando.alterarConta(idTelegram, cpf, email);
	}

	public synchronized void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		contaComando.incluirDependente(idTelegram, nome, sobrenome, telefone, cpf, email);
	}

	public synchronized List<Usuario> listarUsuariosEDependentes(long idTelegram) throws ContaInexistenteExcecao {
		ContaComando contaComando = new ContaComando();
		return contaComando.listarUsuarios(idTelegram);
	}

	public synchronized void realizarDeposito(long idTelegram, double valor) throws ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		operacoesComando.realizarDeposito(idTelegram, valor);
	}
	
	public synchronized void realizarSaque(long idTelegram, double valor) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		operacoesComando.realizarSaque(idTelegram, valor);
	}
	
	public synchronized double verificarSaldo(long idTelegram) throws ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		return operacoesComando.verificarSaldo(idTelegram);
	}
	
	public synchronized List<Transacao> verificarExtrato(long idTelegram) throws SaldoInsuficienteExcecao, ContaInexistenteExcecao {
		OperacoesComando operacoesComando = new OperacoesComando();
		return operacoesComando.verificacaoExtrato(idTelegram);
	}
	
	public synchronized double verificarValorMaximoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		return emprestimoComando.verificarValorMaximoEmprestimo(idTelegram);
	}
	
	public synchronized void solicitarEmprestimo(long idTelegram, double valor, int prazo) throws ContaInexistenteExcecao, ValorEmprestimoExcedidoExcecao, PrazoEmprestimoExcedidoExcecao, SaldoInsuficienteExcecao, EmprestimoAbertoExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		emprestimoComando.solicitarEmprestimo(idTelegram, valor, prazo);
	}
	
	public synchronized EmprestimoDetalhe verificarSaldoDevedorPrazoEmprestimo(long idTelegram) throws ContaInexistenteExcecao {
		EmprestimoComando emprestimoComando = new EmprestimoComando();
		return emprestimoComando.buscarSaldoDevedorPrazoEmprestimo(idTelegram);
	}
	
	public synchronized TransacaoDetalhe listarLancamentos(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarLancamentos(idTelegram);
	}
	
	public synchronized TransacaoDetalhe listarRetiradas(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarRetiradas(idTelegram);
	}
	
	public synchronized TransacaoDetalhe listarTarifas(long idTelegram) throws ContaInexistenteExcecao {
		TransacaoComando transacaoComando = new TransacaoComando();
		return transacaoComando.listarTarifas(idTelegram);
	}

}
