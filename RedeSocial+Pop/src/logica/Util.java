package logica;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import exceptions.PostException;

public class Util {
	
	private static Util INSTANCIA;
	
	private Util() {
		
	}


    public static Util getInstancia() {  
    	if (INSTANCIA == null) {  
    		INSTANCIA = new Util();
    	}
        return INSTANCIA;  
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
	
	// buscando as hashtag do texto
	// logica semelhante a usada na busca de arquivos
	public List<String> encontraHashtag(String mensagem) throws PostException {
		List<String> hashtags = new ArrayList<>();
		String novaHash = "";
		char[] novaMsg = mensagem.toCharArray();
		boolean espaco = false;
		boolean iniciaVerificacao = false;
		
		for(char caracter: novaMsg) {
			if (caracter == '#') {
				iniciaVerificacao = true;
				novaHash += caracter;
			} 
			
			if (iniciaVerificacao) {
				if (caracter == '#'){
					espaco = false;
				} else if (espaco) {
					throw new PostException("Hashtag invalida");
				} else if (caracter != ' ') {
					novaHash += caracter;
					
				} else if (caracter == ' ') {
					hashtags.add(novaHash);
					novaHash = "";
					espaco = true;
				}
			}
		}
		if (!novaHash.equals("")){
			hashtags.add(novaHash);
		}
		return hashtags;
	}
	
	
	
	public boolean verificaFormatoData(String data) {
		
		String[] dataS = data.split("/");
		if (dataS.length != 3) {
			return false;
		}
		if (!(dataS[0].matches("^[0-9]*$")) ||
		    !(dataS[1].matches("^[0-9]*$")) ||
			!(dataS[2].matches("^[0-9]*$")) ) {
				return false;
		    }
		if (dataS[0].length() > 2 ||
			dataS[0].length() > 12 ||
			dataS[0].length() > 4 ) {
			return false;
		}
				
		return true;
	}
	
	public boolean verificaDataValida(String data) {
		try {	
			String[] dataS = data.split("/");
			int dia = Integer.parseInt(dataS[0]);
			int mes = Integer.parseInt(dataS[1]);
			int ano = Integer.parseInt(dataS[2]);
			LocalDate dat = LocalDate.of(ano, mes, dia);
			return true;
		} catch (DateTimeException erro) {
			return false;
		}
	
	}
	
	public LocalDate recebeData(String data) {
		String[] dataS = data.split("/");
		int dia = Integer.parseInt(dataS[0]);
		int mes = Integer.parseInt(dataS[1]);
		int ano = Integer.parseInt(dataS[2]);
		return LocalDate.of(ano, mes, dia);
	}

	public String encontraTexto(String texto) {
		char[] novoTexto = texto.toCharArray();
		String conteudo = "";
		String chave = "";
		boolean inicia = false;
		int pos = 0;
		int cont = 0;
		for (char caracter: novoTexto) {
			cont += 1;
			if (caracter == '<') {
				pos = cont;
				inicia = true;
			}else if (caracter == '>' ) { 
				if (chave.contains("audio") || chave.contains("imagem")) {
					
					return conteudo.substring(0, pos - 2);
				}
				
			} else if (inicia) {
				chave += caracter;
			} else if (caracter == '#') {
				pos = cont;
				return conteudo.substring(0, pos - 2);
			} else {
				conteudo += caracter;
			}
			
		}
		return conteudo;
	}
	
}