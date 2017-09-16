package br.com.fiap.utils;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ConversorImagensUtil {
	
	public static void converterGifParaPng(File entrada) throws IOException {
		File saida = new File(entrada.getAbsolutePath().replace(".gif", ".png"));
		
		ImageIO.write(ImageIO.read(entrada), "png", saida);
	}

}
