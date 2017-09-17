package br.com.fiap.teste;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.fiap.entity.Gif;

public class CadastroDeGifInicial {
	
	public static Set<Gif> criarGifs() {
		Set<Gif> gifs = new HashSet<Gif>();
		
		gifs.add(criarGif("Salto em altura", "01", "Mulher tenta fazer salto em altura e cai de costas no chão"));
		gifs.add(criarGif("Bolada na cabeça", "02", "Jogador de baseball joga a bola para companheiro que estava distraído"));
		gifs.add(criarGif("Cachorro de sapato", "03", "Cachorro vestindo sapato anda de forma engraçada"));
		gifs.add(criarGif("Panda vermelho assustado", "04", "Um panda vermelho toma um grande susto quando um humano se aproxima"));
		gifs.add(criarGif("Pegadinha do futebol americano", "05", "Rapaz recebe uma bola de futebol america e é perseguido por vários jogadores"));
		gifs.add(criarGif("Gato e porco espinho", "06", "Gato descobre porque não pode sentar em um porco espinho"));
		gifs.add(criarGif("Lhama antipática", "07", "Lhama cheia de marra vira a cara pro humano"));
		gifs.add(criarGif("Gorila nojento", "08", "Gorila fica encarando amigo que tira catota e come"));
		gifs.add(criarGif("Yoga animal", "09", "Cachorro faz companhia pro humando durante o yoga"));
		gifs.add(criarGif("Protestante esperto", "10", "Um homem protestando cobre seu rosto de forma inteligente"));
		gifs.add(criarGif("Lontra revoltada", "11", "Uma lontra fica revoltada porque seus brinquedinhos não encaixam"));
		gifs.add(criarGif("Cachorro fogueteiro", "12", "Cachorrinho pega fogos de artifício e faz a festa"));
		gifs.add(criarGif("Gatoception", "13", "Inception com um gato assustado"));
		gifs.add(criarGif("Hóstia negada", "14", "Padre não percebe menino esperando pela hóstia"));
		gifs.add(criarGif("Trabalhador desastrado", "15", "Homem sofre acidente ao tentar se livrar da terra"));
		gifs.add(criarGif("Oprah", "16", "Ataque de abelhas no programa da Oprah"));
		gifs.add(criarGif("Hockey bem jogado", "17", "Jogada estranha no hockey com final surpreendente"));
		gifs.add(criarGif("Caminhão revoltado", "18", "Caminhão de lixo mostra toda sua revolta com o trabalho"));
		gifs.add(criarGif("Amigos vesgos", "19", "Amigos vesgos brincando com o cachorrinho na praia"));
		gifs.add(criarGif("Gorila bravo", "20", "Gorila fica bravo com as crianças"));
		gifs.add(criarGif("Correio chegou", "21", "Menino descobre como os correios podem ser perigosos"));
		gifs.add(criarGif("Sai daqui", "22", "Mãe elefante dá um chega pra lá no filhote"));
		gifs.add(criarGif("Rei Leão?", "23", "O fim do rei leão pelas mãos do Rafiqui"));
		gifs.add(criarGif("Tapinha amigo", "24", "Pinguim dá um tapinha nas costa do amigo"));
		gifs.add(criarGif("Me larga!", "25", "Cachorro desesperado sendo perseguido por crocodilo"));
		gifs.add(criarGif("Bichos estranhos", "26", "Dois bichos estranhos sendo alimentados"));
		gifs.add(criarGif("Saco cheio", "27", "Adele com cara de quem já tá de saco cheio"));
		gifs.add(criarGif("Aniversário", "28", "Cachorro se revolta com seu bolo de aniversário"));
		gifs.add(criarGif("O gás tá ligado?", "29", "Dúvidas na vida de um humano que não se aplicam a um cachorro"));
		gifs.add(criarGif("To feliz mas to bravo", "30", "Kanye West ensina como é um mix de emoções"));
		gifs.add(criarGif("Cachorro birrenta", "31", "Cachorro faz birra mas fica quietinho quando o dono aparece"));
		gifs.add(criarGif("Saúde!", "32", "Menina fica chocada com amiguinho que espirra"));
		gifs.add(criarGif("Surpesa", "33", "Duas crianças e só uma vela. Quem apaga?"));
		gifs.add(criarGif("De cara no chão", "34", "Mascote descobre as dificuldades de correr"));
		gifs.add(criarGif("Radar humano", "35", "Multando a polícia, mas agora corre..."));
		gifs.add(criarGif("Vegetais", "36", "Coma sempre seus vegetais!!!"));
		gifs.add(criarGif("Golaço", "37", "Um jogador, sem goleiro e um disco. Nada pode dar errado!"));
		gifs.add(criarGif("Ventinho", "38", "Apenas uma pequena brisa"));
		gifs.add(criarGif("Chá", "39", "Tomando um chá com a amiga quando tudo começa a implodir"));
		gifs.add(criarGif("Amigo peixe", "40", "Dando um abraço no amigo peixe"));
		gifs.add(criarGif("Beatles", "41", "Lucy in the Sky with Diamonds"));
		gifs.add(criarGif("Corrida de porco", "42", "Bêbado e sua moto porco"));
		gifs.add(criarGif("Beijinho", "43", "Trump mandando beijinho pras amigas"));
		gifs.add(criarGif("Drop the mic", "44", "Obama e sua saída triunfal"));
		
		return gifs;
	}
	
	private static synchronized Gif criarGif(String nome, String caminho, String descricao) {
		Gif gif = new Gif();
		
		gif.setNome(nome);
		gif.setCaminho("static/img/" + caminho);
		gif.setDescricao(descricao);
		gif.setDataPublicacao(LocalDate.now());
		
		return gif;
	}

}
