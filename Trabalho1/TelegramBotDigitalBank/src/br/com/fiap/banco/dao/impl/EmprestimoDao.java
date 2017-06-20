package br.com.fiap.banco.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.entidades.Emprestimo;
import br.com.fiap.generico.dao.DaoGenerico;

public class EmprestimoDao extends DaoGenerico<Emprestimo> {

	public EmprestimoDao() {
		super(Emprestimo.class);
	}
	
	public void adicionarNovoEmprestimo(List<Emprestimo> listaEmprestimos) {
		super.adicionarLista(listaEmprestimos);
	}
	
	public void marcarEmprestimoPago(Emprestimo emprestimo) {
		emprestimo.setParcelaPaga(true);
		super.atualizar(emprestimo);
	}
	
	public List<Emprestimo> buscarDadosEmprestimoAberto(long conta) {
		TypedQuery<Emprestimo> query = super.em.createQuery("select e from Emprestimo e where parcela_paga = 0 and conta = " + conta, Emprestimo.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Emprestimo> buscarEmprestimosVencidos(long conta) {
		TypedQuery<Emprestimo> query = super.em.createQuery("select e from Emprestimo e where parcela_paga = 0 and data_vencimento <= curdate() and conta_numero = " + conta, Emprestimo.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
