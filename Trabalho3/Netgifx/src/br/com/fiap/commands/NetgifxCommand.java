package br.com.fiap.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class NetgifxCommand {

	public void cadastrarUsuario(String nome,String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email ) {
		(new UsuarioCommand()).cadastrarUsuario(nome, apelido, senha, cpf, dataNasc, telefone, email );
	}
	
	public void cadastrarUsuario(Usuario usuario) {
		(new UsuarioCommand()).cadastrarUsuario(usuario);
	}
	
	public Usuario buscarUsuario(String apelido) {
		return new UsuarioCommand().buscarUsuario(apelido);
	}
	
	public void atualizarFavoritos(Usuario usuario, Set<Gif> gifs) {
		/* FIXME esta apagando o favorito ao rodar 2x */
		
		usuario.getGifs().clear();
		if (gifs.isEmpty()) {
			new UsuarioCommand().atualizarUsuario(usuario);
		}
		for (Gif gif : gifs) {
			usuario.getGifs().add(gif);
			for (Usuario usuarioGif : gif.getUsuarios()) {
				if (usuarioGif.equals(usuario)) {
					gif.getUsuarios().remove(usuario);
				}
				
			}
			new UsuarioCommand().atualizarUsuario(usuario);
			new GifCommand().atualizarGif(gif);
		}

	}
	
	public Gif buscarGif(Integer id) {
		return new GifCommand().buscarGif(id);
	}
	
	public List<Categoria> listarCategorias() {
		return (new CategoriaCommand()).listarCategorias();
	}
	
	public void cadastrarGif(Gif gif) {
		(new GifCommand()).cadastrarGif(gif);
	}
	
	public void cadastrarCategoria(Categoria categoria) {
		(new CategoriaCommand()).cadastrarCategoria(categoria);
	}
	
	public void atualizarGifCategoria(List<Gif> gifs,List<Categoria> categorias) {
		/*TODO Fazer o cadstro de gifXCategoria */
	}
}
