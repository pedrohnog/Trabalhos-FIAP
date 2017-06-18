package br.com.fiap.banco.comandos;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.UsuarioDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Usuario;

public class ContaComando {

	private TransacaoComando transacoes = new TransacaoComando();
	
	public boolean temConta(long idTelegram) {
		try (ContaDao contaDao = new ContaDao();) {
			Usuario usuario = usuarioConta(idTelegram);
			return contaDao.temConta(usuario.getConta().getNumero());
		}
	}
	
	public Usuario usuarioConta(long idTelegram){
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
		 return usuarioDao.buscar(idTelegram);
		}
	}

	public void criarConta(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setTipoUsuario(TipoUsuario.PRINCIPAL.getCodigo());
		usuario.setId(idTelegram);
		
		Conta conta = new Conta();
		//conta.setNumero(idTelegram);
		conta.setDataAbertura(LocalDate.now());
		conta.setSaldo(0.0d);

		usuario.setConta(conta);

		try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
			if (contaDao.criarConta(conta)) {
				usuarioDao.criarUsuario(usuario);
			}
		}
	}

	public void alterarConta(long idTelegram, String cpf, String email) {
		// TODO Descobrir qual o usuário que precisa ser alterado
		// TODO esta alterando o usuario da conta
		if (temConta(idTelegram)) {
			try (UsuarioDao usuarioDao = new UsuarioDao()) {
				Usuario usuario = usuarioDao.buscar(idTelegram);
				usuario.setCpf(cpf);
				usuario.setEmail(email);
				usuarioDao.alterarUsuario(usuario);
			}
		}
	}

	public void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf,
			String email, String cpfTitular) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setTipoUsuario(TipoUsuario.DEPENDENTE.getCodigo());
		usuario.setId(idTelegram);

		try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
			Usuario usuarioTitular = usuarioDao.buscarUsuario(cpfTitular);
			if (usuarioTitular != null) {

				usuario.setConta(usuarioTitular.getConta());

				usuarioDao.criarUsuario(usuario);
			}
		}

	}

	public List<Usuario> listarUsuarios(long idTelegram) {
		try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
			Usuario usuario = usuarioDao.buscar(idTelegram);
			return contaDao.buscarConta(usuario.getConta().getNumero()).getUsuarios();
		}
	}

	public void deposito(long idTelegram, double valor) {
		if (temConta(idTelegram)) {
			Usuario usuario = usuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				Double saldo = conta.getSaldo();
				conta.setSaldo(saldo += valor);
				contaDao.alterarConta(conta);
				transacoes.gravarTransacao(usuario, valor, TipoTransacao.DEPOSITO.getCodigo());
			}
		}
	}

	public boolean sacar(long idTelegram, double valor) {
		// TODO ver se o boolean como retorno atende a necessidade
		// TODO retornar exception em caso de falta de saldo?
		if (temConta(idTelegram)) {
			Usuario usuario = usuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				Double saldo = conta.getSaldo();
				valor += Tarifas.SAQUE.getCustoServico();
				if (saldo > valor) {
					conta.setSaldo(saldo -= valor);
					
					contaDao.alterarConta(conta);
					return true;
				}
			}
		}
		return false;
	}

	public double saldo(long idTelegram) {
		double saldo = 0;
		if (temConta(idTelegram)) {
			Usuario usuario = usuarioConta(idTelegram);
			try (ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscar(usuario.getConta().getNumero());
				saldo = conta.getSaldo();
			}
		}
		return saldo;
	}
}
