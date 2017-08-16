package br.com.fiap.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private int idUsuario;
	
	@Column(name = "Nome", length = 45)
	private String nome;

	@Column(name = "apelido", length = 45)
	private String apelido;

	@Column(name = "Senha", length = 45)
	private String senha;
	
	@Column(name = "cpf", length = 11)
	private String cpf;
	
	@Column(name = "DataNasc")
	private LocalDate dataNasc;
	
	@Column(name = "Telefone", length = 20)
	private String telefone;
	
	@Column(name = "Email", length = 20)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="FAVORITOS", 
			joinColumns=@JoinColumn(name="idUsuario"),
			inverseJoinColumns=@JoinColumn(name="idGif"))
	private Set<Gif> gifs;
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Gif> getGifs() {
		return gifs;
	}

	public void setGifs(Set<Gif> gifs) {
		this.gifs = gifs;
	}
	
	
	
}