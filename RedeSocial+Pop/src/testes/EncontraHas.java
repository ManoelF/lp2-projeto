package testes;

import java.util.ArrayList;
import java.util.List;

public class EncontraHas {
	
	public void encontraHash(String mensagem) {
		List<String> arquivos = new ArrayList<>();
		String tipoMidia = "$arquivo_";  			// variavel para concatenar o arquivo
		char[] novaMsg = mensagem.toCharArray();	// transformando a mensagem em lista de char para poder iterar
		boolean inicia = false; 	// variavel para controlar o momento de pegar os caraceres que interecam
		boolean maiorQ = false;
		int cont = 0;								// contador para saber o momento de pegar o proximo arquivo
		
		
		for (char caracter: novaMsg) {
			if (caracter == '>') {
				maiorQ = true;
			}
			if (caracter == '<') {
				inicia = true;
				cont += 1;
				if (cont == 2) {
					if (maiorQ) {
						arquivos.add(tipoMidia);
					}
					
				}
			} else if (inicia) {
				if (caracter == '>') {
					tipoMidia += ":";
				} else {
					tipoMidia += caracter;
				}
			}
		}//loop
		
		System.out.println(arquivos);
	}//metodo

	
	
	
	
	
	
	
	
	
	
	
	
}
