package br.com.fiap.banco.excecao;

/**
 * Exceção para evitar que sejam criadas várias contas para um mesmo usuário
 *
 */
public class ContaExistenteExcecao extends Exception {
	
	private static final long serialVersionUID = 4282575057920376690L;

	public ContaExistenteExcecao() {
		super("Já existe uma conta para esse usuário. Não é possível criar uma nova conta!");
	}

}
