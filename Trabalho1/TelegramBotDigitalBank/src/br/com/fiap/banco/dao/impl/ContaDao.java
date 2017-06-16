package br.com.fiap.banco.dao.impl;

import br.com.fiap.banco.dao.DaoGenerico;
import br.com.fiap.banco.entidades.Conta;

public class ContaDao extends DaoGenerico<Conta> {

	public ContaDao() {
		super(Conta.class);
	}

	public boolean criarConta(Conta conta) {
		if (!temConta(conta.getNumero())) {
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
