package br.com.fiap.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class NetgifxCommand {

	public void cadastrarUsuario(String nome,String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email ) {
		(new UsuarioCommand()).cadastrarUsuario(nome, apelido, senha, cpf, dataNasc, telefone, email, true );
	}
	
	public void cadastrarUsuario(Usuario usuario) {
		(new UsuarioCommand()).cadastrarUsuario(usuario);
	}
	
	public Usuario buscarUsuario(String apelido) {
		return new UsuarioCommand().buscarUsuario(apelido);
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
	
	public void atualizarGifCategoria(Gif gif,List<Categoria> categoriasNovas) {

		Set<Categoria> categoriasAntigas = gif.getCategorias();
		//apagar das categoriasAntigas o gif
		for (Categoria categoriaAntiga : categoriasAntigas) {
			categoriaAntiga.getGifs().remove(gif);
			new CategoriaCommand().atualizarCategoria(categoriaAntiga);
		}
		//Inserir nas novasCategorias o gif
		for (Categoria categoriaNova : categoriasNovas) {
			categoriaNova.getGifs().add(gif);
			new CategoriaCommand().atualizarCategoria(categoriaNova);
		}
		
		//apagar do gif todas as categorias
		gif.getCategorias().clear();
		//add ao gif a nova lista de categorias

		gif.getCategorias().addAll(categoriasNovas);
		new GifCommand().atualizarGif(gif);
		
	}
	
	public void atualizarFavoritos(Usuario usuario, Set<Gif> gifsNovos) {
		
		Set<Gif> gifsAntigos =usuario.getGifs();
		//apagar dos gifsAntigo o usuario
		for (Gif gifAntigo : gifsAntigos) {
			gifAntigo.getUsuarios().remove(usuario);
			new GifCommand().atualizarGif(gifAntigo);
		}
		//Inserir nos novosGifs o usuario
		for (Gif gifNovo : gifsNovos) {
			gifNovo.getUsuarios().add(usuario);
			new GifCommand().atualizarGif(gifNovo);
		}
		
		//apagar do usuario todos os gif's
		usuario.getGifs().clear();
		//add ao usuario a nova lista de gif
		usuario.getGifs().addAll(gifsNovos);
		new UsuarioCommand().atualizarUsuario(usuario);

	}
}
