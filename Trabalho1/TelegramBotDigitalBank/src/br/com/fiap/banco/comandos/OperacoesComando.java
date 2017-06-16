package br.com.fiap.banco.comandos;

import java.util.List;

import br.com.fiap.banco.constantes.Tarifas;
import br.com.fiap.banco.entidades.Transacao;

public class OperacoesComando {
	
	private double verificarSaldo(long id) {
		//TODO retornar o saldo que o cliente tem em conta
		return 0.0;
	}
	
	public void realizarDeposito(long id, double valor) {
		valor += this.verificarSaldo(id);
		//TODO Persistir no BD
	}
	
	public boolean realizarSaque(long id, double valor) {
		valor += Tarifas.SAQUE.getCustoServico();
		if(valor <= this.verificarSaldo(id)) {
			//TODO Persistir no BD
			return true;
		} else {
			return false;
		}
	}
	
	public List<Transacao> verificacaoExtrato(long id) {
		if(Tarifas.EXTRATO.getCustoServico() <= this.verificarSaldo(id)) {
			//TODO Buscar a lista de transações no BD
			return null;
		} else {
			return null;
		}
	}
	
}
