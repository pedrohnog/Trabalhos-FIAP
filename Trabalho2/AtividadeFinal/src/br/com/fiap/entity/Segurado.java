package br.com.fiap.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SEGURADO", catalog = "DBSeguro", uniqueConstraints = { @UniqueConstraint(columnNames = "CPF_SEGURADO") })
@NamedQuery(name = "Segurado.findAll", query = "select s from Segurado s")
public class Segurado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SEGURADO", unique = true, nullable = false)
	private Integer id;

	@Column(name = "NOME_SEGURADO", unique = false, nullable = false, length = 100)
	private String nome;

	@Column(name = "CPF_SEGURADO", unique = true, nullable = false, length = 11)
	private String cpf;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segurado")
	private List<Apolice> apolices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Apolice> getApolices() {
		return apolices;
	}

	public void setApolices(List<Apolice> apolices) {
		this.apolices = apolices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Segurado [id=").append(id).append(", nome=").append(nome).append(", cpf=").append(cpf)
				.append(", apolices=").append(apolices).append("]");
		return builder.toString();
	}

	
}
