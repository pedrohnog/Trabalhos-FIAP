package br.com.fiap.banco.excecao;

public class UsuarioDuplicadoExcecao extends Exception {
	
	private static final long serialVersionUID = -9196841502368177309L;

	public UsuarioDuplicadoExcecao() {
		super("Já existe um usuário com esse CPF cadastrado");
	}

}
