package br.com.fiap.generico.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class DaoGenerico<T> implements Dao<T> {

	private final Class<T> classe;

	protected EntityManager em;
	protected EntityManagerFactory emf;

	protected DaoGenerico(Class<T> classe) {
		this.classe = classe;
		this.emf = Persistence.createEntityManagerFactory("TelegramBotDigitalBank");
		this.em = this.emf.createEntityManager();
	}

	@Override
	public void adicionar(T entidade) {
		this.em.getTransaction().begin();
		this.em.persist(entidade);
		this.em.getTransaction().commit();
	}

	@Override
	public void adicionarLista(List<T> entidades) {
		this.em.getTransaction().begin();
		for (T entidade : entidades) {
			this.em.persist(entidade);
		}
		this.em.getTransaction().commit();
	}

	@Override
	public List<T> listar() {
		TypedQuery<T> query = this.em.createQuery("SELECT * FROM " + this.classe.getSimpleName(), this.classe);
		return query.getResultList();
	}

	@Override
	public void atualizar(T entidade) {
		this.em.getTransaction().begin();
		this.em.merge(entidade);
		this.em.getTransaction().commit();
	}

	@Override
	public void remover(T entidade) {
		this.em.getTransaction().begin();
		this.em.remove(this.em.merge(entidade));
		this.em.getTransaction().commit();
	}

	@Override
	public T buscar(long id) {
		return this.em.find(this.classe, id);
	}

	@Override
	public void close() {
		em.close();
		emf.close();
	}

}