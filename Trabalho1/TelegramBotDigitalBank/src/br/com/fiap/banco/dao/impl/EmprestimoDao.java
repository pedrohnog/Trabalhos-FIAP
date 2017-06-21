package br.com.fiap.banco.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.generico.dao.DaoGenerico;

/**
 * Classe responsável por tratar o DAO do Empréstimo
 *
 */
public class EmprestimoDao extends DaoGenerico<Emprestimo> {

	public EmprestimoDao() {
		super(Emprestimo.class);
	}
	
	/**
	 * Persiste em BD todas as parcelas do empréstimo solicitado
	 * 
	 * @param listaEmprestimos Lista de parcelas
	 */
	public void adicionarNovoEmprestimo(List<Emprestimo> listaEmprestimos) {
		super.adicionarLista(listaEmprestimos);
	}
	
	/**
	 * Marca o empréstimo com o status de pago no BD
	 * 
	 * @param emprestimo Objeto Emprestimo que representa a parcela paga
	 */
	public void marcarEmprestimoPago(Emprestimo emprestimo) {
		emprestimo.setParcelaPaga(true);
		super.atualizar(emprestimo);
	}
	
	/**
	 * Busca a lista de parcelas de um empréstimo que estão em aberto (não pagas)
	 * 
	 * @param conta Id do Telegram
	 * 
	 * @return Lista de parcelas abertas
	 */
	public List<Emprestimo> buscarDadosEmprestimoAberto(long conta) {
		TypedQuery<Emprestimo> query = super.em.createQuery("select e from Emprestimo e where parcela_paga = 0 and conta = " + conta, Emprestimo.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Busca a lista de parcelas de um empréstimo que estão em aberto (não pagas) e já vencidas
	 * 
	 * @param conta Id do Telegram
	 * 
	 * @return Lista de parcelas abertas e não pagas
	 */
	public List<Emprestimo> buscarEmprestimosVencidos(long conta) {
		TypedQuery<Emprestimo> query = super.em.createQuery("select e from Emprestimo e where parcela_paga = 0 and data_vencimento <= curdate() and conta_numero = " + conta, Emprestimo.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Busca a parcela de um empréstimo e só retorna se estiver vencido e não tiver sido pago
	 * 
	 * @param conta Id do Telegram
	 * @param numeroParcela Número da parcela
	 * 
	 * @return Parcela empréstima e não paga
	 */
	public Emprestimo buscarEmprestimo(long conta, int numeroParcela) {
		TypedQuery<Emprestimo> query = super.em.createQuery("select e from Emprestimo e where parcela_paga = 0 and conta_numero = " + conta + " and numero_parcela = " + numeroParcela, Emprestimo.class);
		return query.getSingleResult();
	}
	
}
