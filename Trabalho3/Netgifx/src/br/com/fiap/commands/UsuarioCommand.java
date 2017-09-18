package br.com.fiap.commands;

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
