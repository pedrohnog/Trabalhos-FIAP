package br.com.fiap.banco.dao;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
	
	void adicionar(T entidade);
	void adicionarLista(List<T> entidades);
	List<T> listar();
	void atualizar(T entidade);
	void remover(T entidade);
	T buscar(long id);
	
}