package br.com.fiap.teste;

import java.util.HashSet;
import java.util.Set;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;
import br.com.fiap.entity.Usuario;

public class Main {

	public static void main(String[] args) {
		System.out.println("teste");
		
		NetgifxCommand netgifxCommand = new NetgifxCommand();
		Usuario usuario = netgifxCommand.buscarUsuario("everton");
		Gif gif1 = netgifxCommand.buscarGif(2);
		Gif gif2 = netgifxCommand.buscarGif(3);
		
		Set<Gif> gifs = new HashSet<Gif>();
		
		gifs.add(gif1);
		gifs.add(gif2);
		usuario.setGifs(gifs);
		
		netgifxCommand.atualizarFavoritos(usuario);

		
//		
//		Usuario usuario = criarUsuario();
//
//		
//		try (UsuarioDao usuarioDao = new UsuarioDao()){
//			usuarioDao.adicionar(usuario);
//		}
				
	}

	private static Usuario criarUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("Everton");
		usuario.setCpf("123");
		usuario.setEmail("everton@everton.com");
		usuario.setSenha("123");
		usuario.setTelefone("123");
		usuario.setApelido("everton");
		
		Set<Usuario> usuarios = new HashSet<Usuario>();
		
		usuarios.add(usuario);

		usuario.setGifs(criarGifs(usuarios));
		
		
		return usuario;
	}
	
	private static Set<Gif> criarGifs(Set<Usuario> usuarios) {
		Gif gif1 = new Gif();
		gif1.setNome("primeiro");
		gif1.setClassificacao(Double.valueOf("0"));
		gif1.setCaminho("01");
		gif1.setDescricao("primeiro");
		gif1.setUsuarios(usuarios);

		Gif gif2 = new Gif();
		gif2.setNome("Segundo");
		gif2.setClassificacao(Double.valueOf("0"));
		gif2.setCaminho("02");
		gif2.setDescricao("segundo");
		gif2.setUsuarios(usuarios);
		
		Gif gif3 = new Gif();
		gif3.setNome("terceiro");
		gif3.setClassificacao(Double.valueOf("0"));
		gif3.setCaminho("03");
		gif3.setDescricao("terceiro");
		gif3.setUsuarios(usuarios);
		
		Set<Gif> gifs = new HashSet<Gif>();
		
		gifs.add(gif1);
		gifs.add(gif2);
		gifs.add(gif3);
		
		Set<Categoria> categorias= criarCategorias(gifs);
		
		gif1.setCategorias(categorias);
		gif2.setCategorias(categorias);
		gif3.setCategorias(categorias);
		
		return gifs;
	}
	
	private static Set<Categoria> criarCategorias(Set<Gif> gifs) {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("suspense");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("novidade");

		Set<Categoria> categorias= new HashSet<Categoria>();
		
		categorias.add(categoria1);
		categorias.add(categoria2);
		
		
		return categorias;
	}
}
