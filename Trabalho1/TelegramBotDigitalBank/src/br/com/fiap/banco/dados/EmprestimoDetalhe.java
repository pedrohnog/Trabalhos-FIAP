package br.com.fiap.banco.dados;

import java.io.Serializable;

/**
 * Classe responsável por cuidar dos detalhes do Empréstimo
 *
 */
public class EmprestimoDetalhe implements Serializable {

	private static final long serialVersionUID = 6900632487562096621L;
	
	private double saldoDevedor;
	private int prazoPagamento;

	public double getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}

	public int getPrazoPagamento() {
		return prazoPagamento;
	}

	public void setPrazoPagamento(int prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prazoPagamento;
		long temp;
		temp = Double.doubleToLongBits(saldoDevedor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EmprestimoDetalhe)) {
			return false;
		}
		EmprestimoDetalhe other = (EmprestimoDetalhe) obj;
		if (prazoPagamento != other.prazoPagamento) {
			return false;
		}
		if (Double.doubleToLongBits(saldoDevedor) != Double.doubleToLongBits(other.saldoDevedor)) {
			return false;
		}
		return true;
	}

}
