package br.com.fiap.banco.excecao;

/**
 * Exceção para tratamento quando solicitar a inclusão de um usuário que já está cadastrado
 *
 */
public class UsuarioDuplicadoExcecao extends Exception {
	
	private static final long serialVersionUID = -9196841502368177309L;

	public UsuarioDuplicadoExcecao() {
		super("Já existe um usuário com esse CPF cadastrado");
	}

}
