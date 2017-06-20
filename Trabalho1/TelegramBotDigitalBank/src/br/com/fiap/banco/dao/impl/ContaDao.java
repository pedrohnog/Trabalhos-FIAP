package br.com.fiap.banco.dao.impl;

import br.com.fiap.banco.entidades.Conta;
import br.com.fiap.generico.dao.DaoGenerico;

public class ContaDao extends DaoGenerico<Conta> {

	public ContaDao() {
		super(Conta.class);
	}

	public boolean criarConta(Conta conta) {
		if (conta.getNumero() == null || !temConta(conta.getNumero())) {
			super.adicionar(conta);
			return true;
		}
		return false;
	}

	public void alterarConta(Conta conta) {
		super.atualizar(conta);
	}

	public boolean temConta(long id) {
		return (super.buscar(id) != null);
	}

	public Conta buscarConta(long id) {
		return super.buscar(id);
	}

}
