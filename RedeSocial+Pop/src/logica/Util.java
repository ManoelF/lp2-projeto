package logica;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.PostException;

public class Util {
	
	private static Util INSTANCIA;
	
	private Util() {
		
	}


    public static Util getInstancia() {
    	synchronized (Util.class) {
	    	if (INSTANCIA == null) {  
	    		INSTANCIA = new Util();
	    	}
	        return INSTANCIA;  
    	}
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
//Verificar!!!
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
			} else if (caracter == '#' && conteudo.length() > 2) {
				pos = cont;
				return conteudo.substring(0, pos - 2);
			} else {
				conteudo += caracter;
			}
		}
		return conteudo;
	}
	
	public List<String> encontraHashtag(String texto) throws PostException {
		String[] hashtags; 
		String novaHash = "";
		List<String> listaHashtags = new ArrayList<>();
		char[] campoHash = texto.toCharArray();
		boolean inicia = false;
		
		for(char caracter: campoHash){
			if (caracter == '#') {
				inicia = true;
			}
			if (inicia == true) {
				novaHash += caracter;
			}
		}
		
		hashtags = novaHash.split(" ");
		for (int i = 0; i < hashtags.length; i++) {
			if(hashtags[i] != " " && hashtags[i] != "") {
				listaHashtags.add(hashtags[i]);
			}
			if (!hashtags[0].toString().equals("") && hashtags[i].charAt(0) != '#') {
				throw new PostException("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + hashtags[i] + "'.");
			}
		}
		
		return listaHashtags;
	}
	
	public LocalDate recebeData(String data) {
		String[] dataS = data.split("/");
		int dia = Integer.parseInt(dataS[0]);
		int mes = Integer.parseInt(dataS[1]);
		int ano = Integer.parseInt(dataS[2]);
		return LocalDate.of(ano, mes, dia);
	}
	
	public LocalDateTime converteParaData(String data) {
	
		String[] dataHora = data.split(" ");		
		String[] dataS = dataHora[0].split("/");
		int ano = Integer.parseInt(dataS[2]);
		int mes = Integer.parseInt(dataS[1]);
		int dia = Integer.parseInt(dataS[0]);
		
		String[] horaS = dataHora[1].split(":");
		int hora = Integer.parseInt(horaS[0]);
		int min = Integer.parseInt(horaS[1]);
		int seg = Integer.parseInt(horaS[2]);
		
		return LocalDateTime.of(ano, mes, dia, hora, min, seg);
	}

	//VALIDACOES
	
	public boolean verificaEmail(String email) {
		String validacaoNome = "^[a-zA-Z]{1}.+@[a-z]{2,}\\.[a-z]{2,4}(\\.[a-z]{2,3})*"; 
		return email.matches(validacaoNome);
	}	
	
	public boolean verificaAtributo(String atributo) {
		String validacaoNome = "^\\w+.+";
		return atributo.matches(validacaoNome);
	}
	
	public boolean verificaSenha(String senha) {
		String validacaoNome = "^[.^\\S].+";
		return senha.matches(validacaoNome);
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
		if (dataS[0].length() != 2 ||
			dataS[1].length() != 2 ||
			dataS[2].length() != 4 ) {
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
			LocalDate.of(ano, mes, dia);

			return true;
		} catch (DateTimeException erro) {
			return false;
		}
	
	}
	
	public boolean verificaFormatoHora(String hora) {
		
		String[] horaS = hora.split(":");
		
		if (horaS.length != 3) {
			return false;
		}
		
		for (String item: horaS) {
			if (item.length() != 2) {
				return false;
			}

		if (!(horaS[0].matches("^[0-9]*$")) ||
		    !(horaS[1].matches("^[0-9]*$")) ||
			!(horaS[2].matches("^[0-9]*$")) ) {
			return false;
			}
		}
		return true;
	}
	
	public boolean verificaHoraValida(String horario) {

		String[] horaS = horario.split(":");
		
		int hora = Integer.parseInt(horaS[0]); 
		int minuto = Integer.parseInt(horaS[1]); 
		int segundo = Integer.parseInt(horaS[2]); 
		
		if (hora < 0 || hora >= 24) {
			return false;
		} 
		if (minuto < 0 || minuto >= 60) {
			return false;
		}
		if (segundo < 0 || segundo >= 60) {
			return false;
		}
		
		return true;
	}
}