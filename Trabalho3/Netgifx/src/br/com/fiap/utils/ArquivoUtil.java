package br.com.fiap.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ArquivoUtil {
	
	private ArquivoUtil() {
		//Construtor
	}

	public static void gravarArquivo(InputStream in, File file) throws IOException {
		try (FileOutputStream fout = new FileOutputStream(file);) {
			while (in.available() != 0) {
				fout.write(in.read());
			}
		}
	}
}
