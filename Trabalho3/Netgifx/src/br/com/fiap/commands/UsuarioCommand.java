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
	
	public synchronized void criarUsuario(String nome,String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email ) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setTelefone(telefone);
		usuario.setApelido(apelido);
		usuario.setDataNasc(dataNasc);

		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			usuarioDao.criarUsuario(usuario);
		}
	}
	
	public synchronized void atualizarFavoritos(Usuario usuario) {
		
		try (UsuarioDao usuarioDao = new UsuarioDao();) {
			usuarioDao.atualizar(usuario);
		}
	}
	
	
}