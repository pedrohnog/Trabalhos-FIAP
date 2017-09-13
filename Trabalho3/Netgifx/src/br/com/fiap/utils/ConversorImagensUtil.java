package br.com.fiap.utils;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.com.fiap.entity.Gif;

public class ConversorImagensUtil {
	
	public static void converterGifParaPng(Gif gif) throws IOException {
		File entrada = new File(gif.getCaminho() + ".gif");
		File saida = new File(gif.getCaminho() + ".png");
		
		ImageIO.write(ImageIO.read(entrada), "png", saida);
	}

}
