package br.com.fiap.banco.dao.impl;

import java.util.List;

import br.com.fiap.banco.dao.DaoGenerico;
import br.com.fiap.banco.entidades.Emprestimo;

public class EmprestimoDao extends DaoGenerico<Emprestimo> {

	public EmprestimoDao() {
		super(Emprestimo.class);
	}
	
	public void adicionarNovoEmprestimo(List<Emprestimo> listaEmprestimos) {
		super.adicionarLista(listaEmprestimos);
	}

}
