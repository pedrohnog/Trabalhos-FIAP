package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "APOLICE", catalog = "DBSeguro")
@NamedQuery(name = "Apolice.findAll", query = "select a from Apolice a")
public class Apolice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMERO_APOLICE", unique = true, nullable = false)
	private Integer numeroApolice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SEGURADO")
	private Segurado segurado;

	@Column(name = "INICIO_VIGENCIA", unique = false, nullable = false)
	private Date dataInicioVigencia;

	@Column(name = "FINAL_VIGENCIA", unique = false, nullable = false)
	private Date dataFinalVigencia;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "apolice")
	private List<Veiculo> veiculos;

	public Integer getNumeroApolice() {
		return numeroApolice;
	}

	public void setNumeroApolice(Integer numeroApolice) {
		this.numeroApolice = numeroApolice;
	}

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public Date getDataFinalVigencia() {
		return dataFinalVigencia;
	}

	public void setDataFinalVigencia(Date dataFinalVigencia) {
		this.dataFinalVigencia = dataFinalVigencia;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Apolice [numeroApolice=").append(numeroApolice)
				.append(", dataInicioVigencia=").append(dataInicioVigencia).append(", dataFinalVigencia=")
				.append(dataFinalVigencia).append(", veiculos=").append(veiculos).append("]");
		return builder.toString();
	}

}
