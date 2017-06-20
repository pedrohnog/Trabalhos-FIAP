package br.com.fiap.banco.comandos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.banco.constantes.TipoUsuario;
import br.com.fiap.banco.dao.impl.ContaDao;
import br.com.fiap.banco.dao.impl.UsuarioDao;
import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.banco.excecao.ContaInexistenteExcecao;

class ContaComando {

	public synchronized boolean temConta(long idTelegram) throws ContaInexistenteExcecao {
		try (ContaDao contaDao = new ContaDao();) {
			if(!contaDao.temConta(idTelegram)) {
				throw new ContaInexistenteExcecao();
			}
		}
		return true;
	}

	public synchronized void criarConta(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) {
		Usuario usuario = this.criarUsuario(nome, sobrenome, telefone, cpf, email);
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

	public synchronized void alterarConta(long idTelegram, String cpf, String email) throws ContaInexistenteExcecao {
		if (this.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao(); UsuarioDao usuarioDao = new UsuarioDao()) {
				Conta conta = contaDao.buscarConta(idTelegram);
				
				List<Usuario> usuarios = conta.getUsuarios();
				
				for (Usuario usuario : usuarios) {
					if(usuario.getTipoUsuario() == TipoUsuario.PRINCIPAL.getCodigo()) {
						usuario.setCpf(cpf);
						usuario.setEmail(email);
						
						usuarioDao.alterarUsuario(usuario);
					}
				}
			}
		}
	}

	public synchronized void incluirDependente(long idTelegram, String nome, String sobrenome, String telefone, String cpf, String email) throws ContaInexistenteExcecao {
		if (this.temConta(idTelegram)) {
			try (UsuarioDao usuarioDao = new UsuarioDao(); ContaDao contaDao = new ContaDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);

				Usuario usuario = this.criarUsuario(nome, sobrenome, telefone, cpf, email);
				usuario.setTipoUsuario(TipoUsuario.DEPENDENTE.getCodigo());
				
				usuario.setConta(conta);
				
				usuarioDao.criarUsuario(usuario);
			}
		}

	}

	public synchronized List<Usuario> listarUsuarios(long idTelegram) throws ContaInexistenteExcecao {
		if (this.temConta(idTelegram)) {
			try (ContaDao contaDao = new ContaDao(); UsuarioDao usuarioDao = new UsuarioDao();) {
				Conta conta = contaDao.buscarConta(idTelegram);
				return conta.getUsuarios();
			}
		}
		return new ArrayList<>();
	}
	
	private synchronized Usuario criarUsuario(String nome, String sobrenome, String telefone, String cpf, String email) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome + " " + sobrenome);
		usuario.setTelefone(telefone);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		
		return usuario;
	}

}
