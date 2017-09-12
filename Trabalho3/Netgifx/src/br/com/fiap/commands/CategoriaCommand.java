package br.com.fiap.commands;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.impl.CategoriaDao;
import br.com.fiap.dao.impl.UsuarioDao;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Usuario;

public class CategoriaCommand {

	public List<Categoria> listarCategorias() {
		
		List<Categoria> categorias  = null;
		
		try (CategoriaDao categoriaDao = new CategoriaDao();) {
			categorias = categoriaDao.listarCategorias();
			
			if(categorias == null) {
				//throw new ContaInexistenteExcecao();
			}
		}
		
		return categorias;
		
	}
	
	public synchronized void atualizarCategoria(Categoria categoria) {
		
		try (CategoriaDao categoriaDao = new CategoriaDao();) {
			categoriaDao.atualizar(categoria);
		}
	}
	
	public synchronized void cadastrarCategoria(Categoria categoria) {
		try (CategoriaDao categoriaDao = new CategoriaDao();) {
			categoriaDao.cadastrarCategoria(categoria);
		}
	}
	
	public Categoria buscarCategoria(String nome) {
		try (CategoriaDao categoriaDao = new CategoriaDao();) {
			return categoriaDao.buscarCategoria(nome);
		}
	}
	
}
