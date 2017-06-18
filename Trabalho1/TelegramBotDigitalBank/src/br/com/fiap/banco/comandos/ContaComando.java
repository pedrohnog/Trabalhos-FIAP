package br.com.fiap.banco.comandos;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.UsuarioDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

public class ContaComando {

	public boolean temConta(long idTelegram) {
		try (ContaDao contaDao = new ContaDao();) {
			Usuario usuario = buscarUsuarioConta(idTelegram);
			return contaDao.temConta(usuario.getConta().getNumero());
		}
	}

	public Usuario buscarUsuarioConta(long idTelegram) {
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
		conta.setDataAbertura(LocalDate.now());
		conta.setSaldo(0.0d);

		usuario.setConta(conta);

		try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
			if (contaDao.criarConta(conta)) {
				usuarioDao.criarUsuario(usuario);
			}
		}
	}

	public void alterarConta(long idTelegram, String cpf, String email) throws ContaInexistenteExcecao {
		// TODO Descobrir qual o usuário que precisa ser alterado
		// TODO esta alterando o usuario da conta
		if (temConta(idTelegram)) {
			try (UsuarioDao usuarioDao = new UsuarioDao()) {
				Usuario usuario = usuarioDao.buscar(idTelegram);
				usuario.setCpf(cpf);
				usuario.setEmail(email);
				usuarioDao.alterarUsuario(usuario);
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
	}

	public void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email, String cpfTitular) throws ContaInexistenteExcecao {
		if (temConta(idTelegram)) {
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
		} else {
			throw new ContaInexistenteExcecao();
		}

	}

	public List<Usuario> listarUsuarios(long idTelegram) throws ContaInexistenteExcecao {
		if (temConta(idTelegram)) {
			try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
				Usuario usuario = usuarioDao.buscar(idTelegram);
				return contaDao.buscarConta(usuario.getConta().getNumero()).getUsuarios();
			}
		} else {
			throw new ContaInexistenteExcecao();
		}
	}

}
