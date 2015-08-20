package logica;

import java.util.List;
import java.util.ArrayList;

public class Util {
	
	public static final Util INSTANCIA = new Util();
	
	private Util() {
		
	}

	
	public List<String> getMidia(String mensagem) {
		List<String> arquivos = new ArrayList<>();
		String tipoMidia = "$arquivo_";  			// variavel para concatenar o arquivo
		char[] novaMsg = mensagem.toCharArray();	// transformando a mensagem em lista de char para poder iterar
		boolean inicia = false; 					// variavel para controlar o momento de pegar os caraceres que interecam
		int cont = 0;								// contador para saber o momento de pegar o proximo arquivo
			
		for(char caracter: novaMsg) {				// onde inicia o arquivo
			if (caracter == '<') {
				inicia = true;
				cont += 1;							// um arquivo esta entre 2 '<'
				if (cont == 2) {					// deposi de dois '<' acaba o arquivo
					if ((tipoMidia.contains("audio") || tipoMidia.contains("imagem"))) {
						arquivos.add(tipoMidia);	// adiciona o arquivo ja encontrado na lista de arquivo
						inicia = false;					// espera o proximo '<' para poder comecar os passos para encotrar o proximo arquivo
					}
					tipoMidia = "$arquivo_";		// reinicia a variavel para adicionar o proximo arquivo
					cont = 1;
				}
			} else if (inicia) {
				if (caracter == '>') {				// no momento que encontra o '>' 
					tipoMidia += ":";				// adiciona ':' para o arquivo ficar no formato pedido
				} else {
					tipoMidia += caracter;			// forma o arquivo
				}
			}
		}
		return arquivos;
	}
}