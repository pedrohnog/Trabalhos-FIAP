package br.com.fiap.banco.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONTA", catalog = "DBBotBank")
public class Conta implements Serializable {

	private static final long serialVersionUID = 144574911265646529L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero", unique = true, nullable = false)
	private Long numero;

	@Column(name = "digito", unique = false, nullable = false)
	private Integer digito;

	@Column(name = "data_abertura", unique = false, nullable = false)
	private LocalDate dataAbertura;

	@Column(name = "saldo")
	private double saldo;

	@OneToMany(mappedBy = "conta")
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "conta")
	private List<Transacao> transacoes;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((digito == null) ? 0 : digito.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof Conta)) {
			return false;
		}
		Conta other = (Conta) obj;
		if (digito == null) {
			if (other.digito != null) {
				return false;
			}
		} else if (!digito.equals(other.digito)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}

}
