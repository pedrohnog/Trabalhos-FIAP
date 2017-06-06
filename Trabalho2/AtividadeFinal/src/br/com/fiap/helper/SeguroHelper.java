package br.com.fiap.helper;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.entity.Apolice;
import br.com.fiap.entity.Segurado;
import br.com.fiap.entity.Veiculo;

public class SeguroHelper implements AutoCloseable{

	private EntityManagerFactory emf;
	private EntityManager em;

	public SeguroHelper() {
		this.emf = Persistence.createEntityManagerFactory("AtividadeFinal");
		this.em = emf.createEntityManager();
	}

	@Override
	public void close() throws Exception {
		em.close();		
		emf.close();
	}
	
	/**
	 * Método responsável por inserir o segurado na base de apólices
	 * @param segurado
	 */
	public void criarNovoSeguro(Segurado segurado){
		this.em.getTransaction().begin();
		this.em.persist(segurado);
		this.em.getTransaction().commit();
	}
	
	/**
	 * Método responsável por realizar a busca do segurado no cache do hibernate
	 * @param id
	 * @return Segurado segurado
	 */
	public Segurado buscarSeguro(Integer id){
		return em.find(Segurado.class, id);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Segurado> listarSegurados(){
		Query query = em.createNamedQuery("Segurado.findAll");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Apolice> listarApolices(){
		Query query = em.createNamedQuery("Apolice.findAll");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Veiculo> listarVeiculos(){
		Query query = em.createNamedQuery("Veiculo.findAll");
		return query.getResultList();
	}
	
	

}
