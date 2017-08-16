package br.com.fiap.commands;

import java.time.LocalDate;

import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class NetgifxCommand {

	public void criarUsuario(String nome,String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email ) {
		(new UsuarioCommand()).criarUsuario(nome, apelido, senha, cpf, dataNasc, telefone, email );
	}
	
	public Usuario buscarUsuario(String apelido) {
		return new UsuarioCommand().buscarUsuario(apelido);
	}
	
	public void atualizarFavoritos(Usuario usuario) {
		(new UsuarioCommand()).atualizarFavoritos(usuario);
	}
	
	public Gif buscarGif(Integer id) {
		return new GifCommand().buscarGif(id);
	}
}
