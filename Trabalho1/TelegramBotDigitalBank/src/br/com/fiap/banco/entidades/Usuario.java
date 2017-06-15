package br.com.fiap.banco.entidades;

import java.time.LocalDate;

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
@Table(name = "USUARIO", catalog = "DBBotBank")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "nome", unique = false, nullable = false, length = 50)
	private String nome;
	
	@Column(name = "DataNasc", unique = false, nullable = false)
	private LocalDate dataNascimento;

	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	private String cpf;
	

	@Column(name = "email", unique = false, nullable = true, length = 50)
	private String email;
	

	@Column(name = "telefone",unique = false, nullable = true, length = 11)
	private String telefone;

	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	private Conta conta;
	
	@Column(name = "tipoUsuario",unique = false, nullable = true, length = 1)
	private Integer tipoUsuario;
	

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


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Conta getConta() {
		return conta;
	}


	public void setConta(Conta conta) {
		this.conta= conta;
	}


	public Integer getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	
}
