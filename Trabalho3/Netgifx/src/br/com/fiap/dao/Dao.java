package br.com.fiap.dao;

import java.util.List;

/**
 * Interface para defini��o dos tratamentos do DAO
 *
 * @param <T> Objeto que ser� tratado
 */
public interface Dao<T> extends AutoCloseable {
	
	void adicionar(T entidade);
	void adicionarLista(List<T> entidades);
	List<T> listar();
	void atualizar(T entidade);
	void remover(T entidade);
	T buscar(long id);
	
}