package br.com.fiap.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtil {
	
	public static synchronized String gerarHash(String valor) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			return new String(md.digest(valor.getBytes()), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return valor;
		
	}

}
