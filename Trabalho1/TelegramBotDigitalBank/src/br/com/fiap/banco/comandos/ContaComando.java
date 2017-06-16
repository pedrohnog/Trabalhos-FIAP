package br.com.fiap.banco.comandos;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.UsuarioDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Usuario;

public class ContaComando {

	public boolean temConta(long idTelegram) {
		try (ContaDao contaDao = new ContaDao();) {
			return contaDao.temConta(idTelegram);
		}
	}

	public void criarConta(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setTipoUsuario(TipoUsuario.PRINCIPAL.getCodigo());

		Conta conta = new Conta();
		conta.setNumero(idTelegram);
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
	}

	public void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf,
			String email) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setTipoUsuario(TipoUsuario.DEPENDENTE.getCodigo());

		try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
			if (usuarioDao.buscarUsuario(cpf) == null) {
				Conta conta = contaDao.buscarConta(idTelegram);

				usuario.setConta(conta);

				usuarioDao.criarUsuario(usuario);
			}
		}

	}

	public List<Usuario> listarUsuarios(long idTelegram) {
		//FIXME Revisar pois está dando erro de Lazy Initialization Collection
		try (ContaDao contaDao = new ContaDao();) {
			return contaDao.buscarConta(idTelegram).getUsuarios();
		}
	}

}
