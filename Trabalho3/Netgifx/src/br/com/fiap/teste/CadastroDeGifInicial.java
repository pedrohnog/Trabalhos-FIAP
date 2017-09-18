package br.com.fiap.teste;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.fiap.entity.Gif;

public class CadastroDeGifInicial {
	
	private CadastroDeGifInicial() {
		//Construtor
	}
	
	public static Set<Gif> criarGifs() {
		Set<Gif> gifs = new HashSet<>();
		
		gifs.add(criarGif("Salto em altura", "01", "Mulher tenta fazer salto em altura e cai de costas no ch�o", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Bolada na cabe�a", "02", "Jogador de baseball joga a bola para companheiro que estava distra�do", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Cachorro de sapato", "03", "Cachorro vestindo sapato anda de forma engra�ada", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Panda vermelho assustado", "04", "Um panda vermelho toma um grande susto quando um humano se aproxima", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Pegadinha do futebol americano", "05", "Rapaz recebe uma bola de futebol america e � perseguido por v�rios jogadores", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Gato e porco espinho", "06", "Gato descobre porque n�o pode sentar em um porco espinho", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Lhama antip�tica", "07", "Lhama cheia de marra vira a cara pro humano", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Gorila nojento", "08", "Gorila fica encarando amigo que tira catota e come", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Yoga animal", "09", "Cachorro faz companhia pro humando durante o yoga", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Protestante esperto", "10", "Um homem protestando cobre seu rosto de forma inteligente", "Ingl�s", "GIF", "14"));
		gifs.add(criarGif("Lontra revoltada", "11", "Uma lontra fica revoltada porque seus brinquedinhos n�o encaixam", "Sem idioma", "GIF", "10"));
		gifs.add(criarGif("Cachorro fogueteiro", "12", "Cachorrinho pega fogos de artif�cio e faz a festa", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Gatoception", "13", "Inception com um gato assustado", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("H�stia negada", "14", "Padre n�o percebe menino esperando pela h�stia", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Trabalhador desastrado", "15", "Homem sofre acidente ao tentar se livrar da terra", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Oprah", "16", "Ataque de abelhas no programa da Oprah", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Hockey bem jogado", "17", "Jogada estranha no hockey com final surpreendente", "Sem idioma", "GIF", "10"));
		gifs.add(criarGif("Caminh�o revoltado", "18", "Caminh�o de lixo mostra toda sua revolta com o trabalho", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Amigos vesgos", "19", "Amigos vesgos brincando com o cachorrinho na praia", "Sem idioma", "GIF", "12"));
		gifs.add(criarGif("Gorila bravo", "20", "Gorila fica bravo com as crian�as", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Correio chegou", "21", "Menino descobre como os correios podem ser perigosos", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Sai daqui", "22", "M�e elefante d� um chega pra l� no filhote", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Rei Le�o?", "23", "O fim do rei le�o pelas m�os do Rafiqui", "Sem idioma", "GIF", "14"));
		gifs.add(criarGif("Tapinha amigo", "24", "Pinguim d� um tapinha nas costa do amigo", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Me larga!", "25", "Cachorro desesperado sendo perseguido por crocodilo", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Bichos estranhos", "26", "Dois bichos estranhos sendo alimentados", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Saco cheio", "27", "Adele com cara de quem j� t� de saco cheio", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Anivers�rio", "28", "Cachorro se revolta com seu bolo de anivers�rio", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("O g�s t� ligado?", "29", "D�vidas na vida de um humano que n�o se aplicam a um cachorro", "Ingl�s", "GIF", "LIVRE"));
		gifs.add(criarGif("To feliz mas to bravo", "30", "Kanye West ensina como � um mix de emo��es", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Cachorro birrenta", "31", "Cachorro faz birra mas fica quietinho quando o dono aparece", "Ingl�s", "GIF", "LIVRE"));
		gifs.add(criarGif("Sa�de!", "32", "Menina fica chocada com amiguinho que espirra", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Surpesa", "33", "Duas crian�as e s� uma vela. Quem apaga?", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("De cara no ch�o", "34", "Mascote descobre as dificuldades de correr", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Radar humano", "35", "Multando a pol�cia, mas agora corre...", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Vegetais", "36", "Coma sempre seus vegetais!!!", "Ingl�s", "GIF", "14"));
		gifs.add(criarGif("Gola�o", "37", "Um jogador, sem goleiro e um disco. Nada pode dar errado!", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Ventinho", "38", "Apenas uma pequena brisa", "Ingl�s", "GIF", "LIVRE"));
		gifs.add(criarGif("Ch�", "39", "Tomando um ch� com a amiga quando tudo come�a a implodir", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Amigo peixe", "40", "Dando um abra�o no amigo peixe", "Ingl�s", "GIF", "LIVRE"));
		gifs.add(criarGif("Beatles", "41", "Lucy in the Sky with Diamonds", "Sem idioma", "GIF", "18"));
		gifs.add(criarGif("Corrida de porco", "42", "B�bado e sua moto porco", "Sem idioma", "GIF", "16"));
		gifs.add(criarGif("Beijinho", "43", "Trump mandando beijinho pras amigas", "Sem idioma", "GIF", "LIVRE"));
		gifs.add(criarGif("Drop the mic", "44", "Obama e sua sa�da triunfal", "Sem idioma", "GIF", "LIVRE"));
		
		return gifs;
	}
	
	private static synchronized Gif criarGif(String nome, String caminho, String descricao, String idioma, String genero, String classificacaoEtaria) {
		Gif gif = new Gif();
		
		gif.setNome(nome);
		gif.setCaminho("static/img/" + caminho);
		gif.setDescricao(descricao);
		gif.setIdioma(idioma);
		gif.setGenero(genero);
		gif.setClassificacaoEtaria(classificacaoEtaria);;
		gif.setDataPublicacao(LocalDate.now());
		
		return gif;
	}

}
