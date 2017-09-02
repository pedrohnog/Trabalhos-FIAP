package br.com.fiap.commands;

import java.time.LocalDate;

import br.com.fiap.dao.impl.UsuarioDao;
import br.com.fiap.entity.Usuario;

public class UsuarioCommand {
	
	public synchronized Usuario buscarUsuario(String apelido) {
		Usuario usuario = null;
		
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			usuario = usuarioDao.buscarUsuario(apelido);
		}
		
		return usuario;
	}
	
	public synchronized void cadastrarUsuario(String nome,String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email, boolean admin ) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setTelefone(telefone);
		usuario.setApelido(apelido);
		usuario.setDataNasc(dataNasc);
		usuario.setAdmin(admin);

		cadastrarUsuario(usuario);
	}
	
	public synchronized void cadastrarUsuario(Usuario usuario) {
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			usuarioDao.cadastrarUsuario(usuario);
		}
	}
	
	public synchronized void atualizarUsuario(Usuario usuario) {
		
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			usuarioDao.atualizar(usuario);
		}
	}
	
	
}
