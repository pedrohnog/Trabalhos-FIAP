package br.com.fiap.banco.comandos;

import java.time.LocalDateTime;

import br.com.fiap.banco.dao.impl.TransacaoDao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.banco.entidades.Usuario;

public class TransacaoComando {

	
	public void gravarTransacao(Usuario usuario, double valor,int tipoTransacao) {
		try(TransacaoDao transacaoDao = new TransacaoDao();) {
			Transacao transacao = new  Transacao();
			transacao.setConta(usuario.getConta());
			transacao.setDataHora(LocalDateTime.now());
			transacao.setTipoTransacao(tipoTransacao);
			transacao.setUsuario(usuario);
			transacao.setValor(valor);
			
			transacaoDao.adicionarTransacao(transacao);
		} 
	}
	
}
