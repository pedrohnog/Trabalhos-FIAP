package br.com.fiap.banco.entidades;

import java.io.Serializable;

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
 * Entidade que representa a tabela Usuario no BD
 *
 */
@Entity
@Table(name = "USUARIO", catalog = "DBBotBank")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 7674732064100562675L;

	/**
	 * Id auto-gerado
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	/**
	 * Nome e sobrenome do usu�rio
	 */
	@Column(name = "nome", unique = false, nullable = false, length = 50)
	private String nome;

	/**
	 * N�mero de CPF do usu�rio
	 */
	@Column(name = "cpf", unique = true, nullable = false, length = 11)
	private String cpf;

	/**
	 * Email do usu�rio
	 */
	@Column(name = "email", unique = false, nullable = true, length = 50)
	private String email;

	/**
	 * N�mero de telefone do usu�rio
	 */
	@Column(name = "telefone", unique = false, nullable = true, length = 11)
	private String telefone;

	/**
	 * Tipo de usu�rio (principal ou dependente)
	 */
	@Column(name = "tipo_usuario", unique = false, nullable = true, length = 1)
	private int tipoUsuario;

	/**
	 * Relacionamento com a conta � qual esse usu�rio est� atrelado
	 */
	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Conta conta;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (conta == null) {
			if (other.conta != null) {
				return false;
			}
		} else if (!conta.equals(other.conta)) {
			return false;
		}
		if (cpf == null) {
			if (other.cpf != null) {
				return false;
			}
		} else if (!cpf.equals(other.cpf)) {
			return false;
		}
		return true;
	}

}
