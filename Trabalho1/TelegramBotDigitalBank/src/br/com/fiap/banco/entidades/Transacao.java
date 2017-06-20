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

@Entity
@Table(name = "TRANSACAO", catalog = "DBBotBank")
public class Transacao implements Serializable {

	private static final long serialVersionUID = -2468847941236123158L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Conta conta;

	@Column(name = "data_hora", unique = false, nullable = false)
	private LocalDateTime dataHora;

	@Column(name = "valor", unique = false, nullable = false)
	private Double valor;

	@Column(name = "tipo_transacao", unique = false, nullable = false)
	private Integer tipoTransacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(Integer tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((tipoTransacao == null) ? 0 : tipoTransacao.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		if (dataHora == null) {
			if (other.dataHora != null) {
				return false;
			}
		} else if (!dataHora.equals(other.dataHora)) {
			return false;
		}
		if (tipoTransacao == null) {
			if (other.tipoTransacao != null) {
				return false;
			}
		} else if (!tipoTransacao.equals(other.tipoTransacao)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}

}
