package br.com.fiap.commands;

import java.time.LocalDate;

import br.com.fiap.cache.builder.impl.CacheBuilderImpl;
import br.com.fiap.cache.constants.TempoVida;
import br.com.fiap.cache.manager.GerenciadorCache;
import br.com.fiap.dao.impl.UsuarioDao;
import br.com.fiap.entity.Usuario;

public class UsuarioCommand {
	
	public synchronized Usuario buscarUsuario(String apelido) {
		GerenciadorCache<String, Usuario> cache = CacheBuilderImpl.init(String.class, Usuario.class).definirTempoVida(15L, TempoVida.MINUTOS).build("BUSCA USUARIO");
		
		if (cache.verificarExistencia(apelido)) {
			return cache.recuperar(apelido);
		} else {
			Usuario usuario = null;
			
			try (UsuarioDao usuarioDao = new UsuarioDao();) {
				usuario = usuarioDao.buscarUsuario(apelido);
			}
			
			cache.gravar(apelido, usuario);
			
			return usuario;
		}
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
