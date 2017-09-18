package br.com.fiap.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CriptografiaUtil {

	protected static Logger logger = LoggerFactory.getLogger(CriptografiaUtil.class);
	
	private CriptografiaUtil() {
		//Construtor
	}
	
	public static synchronized String gerarHash(String valor) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			return new String(md.digest(valor.getBytes()), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		
		return valor;
		
	}

}
