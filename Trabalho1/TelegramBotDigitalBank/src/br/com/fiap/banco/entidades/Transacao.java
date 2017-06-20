package br.com.fiap.banco.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que representa a tabela Transacao no BD
 *
 */
@Entity
@Table(name = "TRANSACAO", catalog = "DBBotBank")
public class Transacao implements Serializable {

	private static final long serialVersionUID = -3857943629706631875L;

	/**
	 * Id auto-gerado
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	/**
	 * Relacionamento com a conta à qual essa transação pertence
	 */
	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Conta conta;

	/**
	 * Data e hora em que a transação ocorreu
	 */
	@Column(name = "data_hora", unique = false, nullable = false)
	private LocalDateTime dataHora;

	/**
	 * Valor da transação
	 */
	@Column(name = "valor", unique = false, nullable = false)
	private double valor;

	/**
	 * Tipo de transação (saque, depósito, etc.)
	 */
	@Column(name = "tipo_transacao", unique = false, nullable = false)
	private int tipoTransacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + id;
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
		if (!(obj instanceof Transacao)) {
			return false;
		}
		Transacao other = (Transacao) obj;
		if (conta == null) {
			if (other.conta != null) {
				return false;
			}
		} else if (!conta.equals(other.conta)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
