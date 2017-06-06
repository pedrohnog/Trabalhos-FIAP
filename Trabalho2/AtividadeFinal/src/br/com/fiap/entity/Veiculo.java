package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VEICULO", catalog = "DBSeguro")
@NamedQuery(name = "Veiculo.findAll", query = "select v from Veiculo v")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEICULO", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUMERO_APOLICE")
	private Apolice apolice;

	@Column(name = "CODIGO_FIPE", unique = false, nullable = false)
	private Integer codigoFipe;

	@Column(name = "PLACA", unique = true, nullable = true, length = 7)
	private String placa;

	@Column(name = "MARCA", unique = false, nullable = false, length = 50)
	private String marca;

	@Column(name = "MODELO", unique = false, nullable = false, length = 50)
	private String modelo;

	@Column(name = "VALOR_FIPE", unique = false, nullable = false)
	private Double valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}

	public Integer getCodigoFipe() {
		return codigoFipe;
	}

	public void setCodigoFipe(Integer codigoFipe) {
		this.codigoFipe = codigoFipe;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Veiculo [id=").append(id).append(", codigoFipe=")
				.append(codigoFipe).append(", placa=").append(placa).append(", marca=").append(marca)
				.append(", modelo=").append(modelo).append(", valor=").append(valor).append("]");
		return builder.toString();
	}

}
