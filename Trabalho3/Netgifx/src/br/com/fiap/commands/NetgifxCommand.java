package br.com.fiap.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.fiap.cache.builder.impl.CacheBuilderImpl;
import br.com.fiap.cache.constants.TempoVida;
import br.com.fiap.cache.manager.GerenciadorCache;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class NetgifxCommand {

	public void cadastrarUsuario(String nome, String apelido, String senha, String cpf, LocalDate dataNasc, String telefone, String email) {
		(new UsuarioCommand()).cadastrarUsuario(nome, apelido, senha, cpf, dataNasc, telefone, email, true);
	}

	public void cadastrarUsuario(Usuario usuario) {
		(new UsuarioCommand()).cadastrarUsuario(usuario);
	}

	public Usuario buscarUsuario(String apelido) {
		GerenciadorCache<String, Usuario> cache = CacheBuilderImpl.init(String.class, Usuario.class) .definirTempoVida(15L, TempoVida.MINUTOS).build("BUSCA USUARIO");

		if (cache.verificarExistencia(apelido)) {
			return cache.recuperar(apelido);
		} else {
			Usuario usuario = new UsuarioCommand().buscarUsuario(apelido);
			
			cache.gravar(apelido, usuario);
			
			return usuario;
		}
	}

	public Gif buscarGif(Integer id) {
		GerenciadorCache<Integer, Gif> cache = CacheBuilderImpl.init(Integer.class, Gif.class) .definirTempoVida(15L, TempoVida.MINUTOS).build("BUSCA GIF");

		if (cache.verificarExistencia(id)) {
			return cache.recuperar(id);
		} else {
			Gif gif = new GifCommand().buscarGif(id);
			
			cache.gravar(id, gif);
			
			return gif;
		}
	}

	public List<Categoria> listarCategorias() {
		GerenciadorCache<String, Categoria[]> cache = CacheBuilderImpl.init(String.class, Categoria[].class) .definirTempoVida(1L, TempoVida.DIAS).build("LISTAR CATEGORIAS");
		
		if (cache.verificarExistencia("CHAVE")) {
			return Arrays.asList(cache.recuperar("CHAVE"));
		} else {
			List<Categoria> categorias = (new CategoriaCommand()).listarCategorias();
			
			cache.gravar("CHAVE", categorias.toArray(new Categoria[0]));
			
			return categorias;
		}
	}

	public void cadastrarGif(Gif gif) {
		(new GifCommand()).cadastrarGif(gif);
	}

	public void cadastrarCategoria(Categoria categoria) {
		(new CategoriaCommand()).cadastrarCategoria(categoria);
	}

	public void atualizarGifCategoria(Gif gif, List<Categoria> categoriasNovas) {
		Set<Categoria> categoriasAntigas = gif.getCategorias();

		// apagar das categoriasAntigas o gif
		if (categoriasAntigas != null) {
			for (Categoria categoriaAntiga : categoriasAntigas) {
				if (categoriaAntiga.getGifs() == null) {
					categoriaAntiga.setGifs(new HashSet<>());
				}
				categoriaAntiga.getGifs().remove(gif);
				new CategoriaCommand().atualizarCategoria(categoriaAntiga);
			}
		}

		// Inserir nas novasCategorias o gif
		if (categoriasNovas != null) {
			for (Categoria categoriaNova : categoriasNovas) {
				if (categoriaNova.getGifs() == null) {
					categoriaNova.setGifs(new HashSet<>());
				}
				categoriaNova.getGifs().add(gif);
				new CategoriaCommand().atualizarCategoria(categoriaNova);
			}
		}

		// apagar do gif todas as categorias
		if (gif.getCategorias() == null) {
			gif.setCategorias(new HashSet<>());
		}
		gif.getCategorias().clear();

		// add ao gif a nova lista de categorias
		gif.getCategorias().addAll(categoriasNovas);
		new GifCommand().atualizarGif(gif);
	}

	public void atualizarFavoritos(Usuario usuario, Set<Gif> gifsNovos) {
		Set<Gif> gifsAntigos = usuario.getGifs();

		// apagar dos gifsAntigo o usuario
		if (gifsAntigos != null) {
			for (Gif gifAntigo : gifsAntigos) {
				if (gifAntigo.getUsuarios() == null) {
					gifAntigo.setUsuarios(new HashSet<>());
				}
				gifAntigo.getUsuarios().remove(usuario);
				new GifCommand().atualizarGif(gifAntigo);
			}
		}

		// Inserir nos novosGifs o usuario
		if (gifsNovos != null) {
			for (Gif gifNovo : gifsNovos) {
				if (gifNovo.getUsuarios() == null) {
					gifNovo.setUsuarios(new HashSet<>());
				}
				gifNovo.getUsuarios().add(usuario);
				new GifCommand().atualizarGif(gifNovo);
			}
		}

		// apagar do usuario todos os gif's
		if (usuario.getGifs() == null) {
			usuario.setGifs(new HashSet<>());
		}
		usuario.getGifs().clear();

		// add ao usuario a nova lista de gif
		usuario.getGifs().addAll(gifsNovos);
		new UsuarioCommand().atualizarUsuario(usuario);
	}
}
