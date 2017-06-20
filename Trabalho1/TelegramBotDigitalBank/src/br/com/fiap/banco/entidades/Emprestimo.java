package br.com.fiap.banco.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Cacheable;
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
@Table(name = "EMPRESTIMO", catalog = "DBBotBank")
@Cacheable(true)
public class Emprestimo implements Serializable {

	private static final long serialVersionUID = -926890707876508258L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Conta conta;

	@Column(name = "numero_parcela", unique = false, nullable = false, length = 1)
	private Integer numeroParcela;

	@Column(name = "valor_parcela", unique = false, nullable = false)
	private double valorParcela;

	@Column(name = "juros", unique = false, nullable = false)
	private double juros;

	@Column(name = "data_vencimento", unique = false, nullable = false)
	private LocalDate dataVencimento;

	@Column(name = "parcela_paga", unique = false, nullable = false)
	private boolean parcelaPaga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public boolean isParcelaPaga() {
		return parcelaPaga;
	}

	public void setParcelaPaga(boolean parcelaPaga) {
		this.parcelaPaga = parcelaPaga;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((numeroParcela == null) ? 0 : numeroParcela.hashCode());
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
		if (!(obj instanceof Emprestimo)) {
			return false;
		}
		Emprestimo other = (Emprestimo) obj;
		if (conta == null) {
			if (other.conta != null) {
				return false;
			}
		} else if (!conta.equals(other.conta)) {
			return false;
		}
		if (dataVencimento == null) {
			if (other.dataVencimento != null) {
				return false;
			}
		} else if (!dataVencimento.equals(other.dataVencimento)) {
			return false;
		}
		if (numeroParcela == null) {
			if (other.numeroParcela != null) {
				return false;
			}
		} else if (!numeroParcela.equals(other.numeroParcela)) {
			return false;
		}
		return true;
	}
}
