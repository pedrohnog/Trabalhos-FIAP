package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class Main {

	static NetgifxCommand netgifxCommand = new NetgifxCommand();
	
	public static void main(String[] args) {

		System.out.println("teste");

//		cargaInicial();
//		listarCategorias();
//		buscarUsuario();
//		buscarGif();
		atualizarFavoritos();
		

				
	}

	private static void atualizarFavoritos() {
	
		Set<Gif> gifs = new HashSet<Gif>();
//		Gif gif1 = netgifxCommand.buscarGif(1);
//		Gif gif2 = netgifxCommand.buscarGif(2);
		
//		gifs.add(gif1);
//		gifs.add(gif2);
		
		Usuario usuario = netgifxCommand.buscarUsuario("everton");
		
		netgifxCommand.atualizarFavoritos(usuario, gifs);
	}
	
	private static void atualizarGifCategoria() {
		
		List<Gif> gifs = new ArrayList<Gif>();
		Gif gif1 = netgifxCommand.buscarGif(1);
//		Gif gif2 = netgifxCommand.buscarGif(3);
		
		gifs.add(gif1);
//		gifs.add(gif2);
		
		List<Categoria> categorias = netgifxCommand.listarCategorias();
		Categoria categoria = categorias.get(0);
		netgifxCommand.atualizarGifCategoria(gifs, categorias);
	}
	
	private static void buscarGif() {
		Gif gif1 = netgifxCommand.buscarGif(2);
		System.out.println(gif1.getNome());
		Gif gif2 = netgifxCommand.buscarGif(3);
		System.out.println(gif2.getNome());
	}
	
	private static void buscarUsuario() {
		Usuario usuario = netgifxCommand.buscarUsuario("everton");
		System.out.println(usuario.getApelido());
	}
	
	private static void listarCategorias() {
		List<Categoria> categorias = netgifxCommand.listarCategorias();

		for (Categoria categoria : categorias) {
			System.out.println(categoria.getNome());

			for (Gif gif : categoria.getGifs()) {
				System.out.println(gif.getNome());
			}

		}
	}
	
	private static void cargaInicial() {
      criarUsuario();
      criarGifs();
	  criarCategorias();
	}
	
	private static void criarUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("Everton");
		usuario.setCpf("123");
		usuario.setEmail("everton@everton.com");
		usuario.setSenha("123");
		usuario.setTelefone("123");
		usuario.setApelido("everton");
		
		Set<Usuario> usuarios = new HashSet<Usuario>();
		
		usuarios.add(usuario);

		//usuario.setGifs(criarGifs(usuarios));
		
		netgifxCommand.cadastrarUsuario(usuario);
		
	}
	
	private static void criarGifs() {
		Gif gif1 = new Gif();
		gif1.setNome("primeiro");
		gif1.setClassificacao(Double.valueOf("3"));
		gif1.setCaminho("01");
		gif1.setDescricao("primeiro");
	
		Gif gif2 = new Gif();
		gif2.setNome("Segundo");
		gif2.setClassificacao(Double.valueOf("3"));
		gif2.setCaminho("02");
		gif2.setDescricao("segundo");
		
		Gif gif3 = new Gif();
		gif3.setNome("terceiro");
		gif3.setClassificacao(Double.valueOf("5"));
		gif3.setCaminho("03");
		gif3.setDescricao("terceiro");
		
		Set<Gif> gifs = new HashSet<Gif>();
		
		gifs.add(gif1);
		gifs.add(gif2);
		gifs.add(gif3);
		
//		Set<Categoria> categorias= criarCategorias(gifs);
//		
//		gif1.setCategorias(categorias);
//		gif2.setCategorias(categorias);
//		gif3.setCategorias(categorias);
//		
//		return gifs;
		
		netgifxCommand.cadastrarGif(gif1);
		netgifxCommand.cadastrarGif(gif2);
		netgifxCommand.cadastrarGif(gif3);
	}
	
	private static void criarCategorias() {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("suspense");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("novidade");

		Set<Categoria> categorias= new HashSet<Categoria>();
		
		categorias.add(categoria1);
		categorias.add(categoria2);
		
		netgifxCommand.cadastrarCategoria(categoria1);
		netgifxCommand.cadastrarCategoria(categoria2);
		
	}
}
