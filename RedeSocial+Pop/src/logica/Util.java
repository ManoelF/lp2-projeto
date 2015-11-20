/* =========================== Rede Social +Pop ================================= #
 *                                                                                *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica                              *
 * Curso Ciência da Computação (UFCG - 2015.1)                                    *
 * Laboratorio de Programação II                                                  *
 *                                                                                *
 * Discentes envolvidos:                                                          *
 *          Italo Batista                                                         *
 *          Jose Manoel Ferreira                                                  *
 *          Kerilin Chang.                                                        *
 *                                                                                *
 * Orientador:                                                                    *
 *          Francisco Neto.                                                       *
 *                                                                                *
 * ============================================================================== #
 */

/**
 * Classe <code>Util</code> responsavel por validações de atributos, tratamento de dados e salvar/recuperar 
 * o estado do sistema.
 * 
 */
package logica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.PostException;
import exceptions.RedeSocialMaisPopException;

public class Util implements Serializable {
	
	private static final long serialVersionUID = 2466398236453125276L;
	private static Util INSTANCIA;
	
	/**
	 * Construtor <b>Private</b> para seguir o padrao
	 * <b>Singleton</b> e garantir apenas uma instranciacao.
	 */
	private Util() {
		
	}
	
	/**
	 * Metodo responsavel por proporcionar a unica
	 * instanciacao do objeto, como tambem garante seguranca 
	 * das informacoes por usar <b>Synchronized</b>.
	 * 
	 * @return Util
	 * 			Instancia de <b>Util</b>.
	 */
    public static Util getInstancia() {
    	synchronized (Util.class) {
	    	if (INSTANCIA == null) {  
	    		INSTANCIA = new Util();
	    	}
	        return INSTANCIA;  
    	}
     }
   
    /**
     * Busca das midias no texto do Post.
     * 
     * @param mensagem
     * 		Texto recebido como parametro na contrucao do Post.
     * 
     * @return List
     * 		Lista com todas as midias encontradas no Post.
     */
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
    
/*	public String encontraTexto(String texto) {
		
		String mensagem = "";
		
		if(texto.contains("<") && texto.substring(texto.indexOf("<"), texto.indexOf("<")+2) != "<3"){
			int endIndex = texto.indexOf("<");
			mensagem =  texto.substring(0, endIndex);
		} else if (texto.contains("#") && texto.substring(texto.indexOf("#")+1, texto.indexOf("#")+2) != " ") {
			int endIndex = texto.indexOf("#");
			mensagem = texto.substring(0, endIndex);
		}
		return mensagem.trim();
	}*/
    
    /**
	 * Busca do texto contido no <b>Post</b>.
	 * 
	 * @param texto
	 * 			Texto recebido como parametro na contrucao do Post.
	 * 	
	 * @return String
	 * 			Texto encontrado no Post.
	 */
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
	
	/**
	 * Busca todas as hashtags contidas no Post.
	 * 
	 * @param texto
	 * 		Texto recebido como parametro na contrucao do Post.
	 * 
	 * @return List
	 * 		Hastags que foram adicionadas ao Post.
	 *  
	 * @throws PostException
	 * 		Se as hashtags nao forem adicionadas no local apropriado
	 * 		ou de forma inadequada.
	 */
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
	

	/**
	 * Conversao da informacao data(<b>String</b>) para data(<b>localDate</b>,
	 * assumindo que a formatacao das informacoes esta integra.
	 * 	
	 * @param data
	 * 		Data recebida para conversão.
	 *
	 * @return LocalDate
	 */
	public LocalDate recebeData(String data) {
		String[] dataS = data.split("/");
		int dia = Integer.parseInt(dataS[0]);
		int mes = Integer.parseInt(dataS[1]);
		int ano = Integer.parseInt(dataS[2]);
		return LocalDate.of(ano, mes, dia);
	}

	/**
	 * Conversao da informacao data(<b>String</b>) para data(<b>localDate</b>.
	 * 	
	 * @param data
	 * 		Data recebida para conversão.
	 *
	 * @return LocalDate
	 */
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

	/**
	 * Converte as hashtags(String) para objeto <b>Tag</b>.
	 * 
	 * @param hashtags
	 * 			Lista de hashtags(String).
	 * 
	 * @return List
	 * 			Lista de Tag. 
	 */
	public List<Tag> converteParaTag(List<String> hashtags) {
		
		List<Tag> tags = new ArrayList<>();
		Tag tag;
		for (String hashtag : hashtags) {
			tag = new Tag(hashtag);
			tags.add(tag);
		}
		
		return tags;
	}
	
	//VALIDACOES
	
	/**
	 * Validacao de email, verificando se atende as exigencias de um email padrao.
	 * 
	 * @param email
	 * 		Email do usuario.
	 * 
	 * @return Boolean
	 * 		Caso o email recebido satisfaca as exigencias sera retornado true,
	 *      caso contraio o retorno sera false.
	 */
	public boolean verificaEmail(String email) {
		String validacaoNome = "^[a-zA-Z]{1}.+@[a-z]{2,}\\.[a-z]{2,4}(\\.[a-z]{2,3})*"; 
		return email.matches(validacaoNome);
	}	
	
	/**
	 * Verificao de alguns atributo do tipo string, 
	 * nao sendo permitido strings vazias ou que inicie com caracteres especiais.
	 * 
	 * @param atributo 
	 * 		String a ser validada.
	 * 
	 * @return Boolean
	 *		Caso o atibuto recebido satisfaca as exigencias sera retornado true,
	 *      caso contraio o retorno sera false.
	 */
	public boolean verificaAtributo(String atributo) {
		String validacaoNome = "^\\w+.+";
		return atributo.matches(validacaoNome);
	}
	
	/**
	 * Verificacao padra da senha de usuario.
	 * 
	 * @param senha
	 * 		Senha do usuario.
	 * 	
	 * @return Boolean
	 * 		Caso a senha recebido satisfaca as exigencias sera retornado true,
	 *      caso contraio o retorno sera false.
	 */
	public boolean verificaSenha(String senha) {
		String validacaoNome = "^[.^\\S].+";
		return senha.matches(validacaoNome);
	}
	
	/**
	 * Validacao da data recebida para cadastro de usuario e criacao de postes.
	 * 
	 * @param data
	 * 		String a ser validada.
	 * @return Boolean
	 * 		Caso a data recebida satisfaca as o padrao dd/mm/aaaa sera retornado true,
	 *      caso contraio o retorno sera false.
	 */
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
	
	/**
	 * Verificao de data valida.
	 * 
	 * @param data 
	 * 		Data a ser avalida.
	 * 
	 * @return Boolean
	 * 		Caso a data recebida seja existente sera retornado true, 
	 * 		porem, se a data nao existir o retorno sera false.
	 */
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
	
	/**
	 * Verificao do fomato da hora.
	 * 
	 * @param hora
	 * 		Hora usada para criacao dos postes.
	 * 
	 * @return Boolean
	 * 		Caso a hora recebida seja em um formato diferente
	 * 		de hh:mm:ss o retorno sera false, caso contrario retorno vedadeiro.
	 */
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
	
	/**
	 * Verificao se a hora recebida e existente.
	 * 
	 * @param horario
	 * 		Hora a ser analizada.
	 * 
	 * @return Boolean
	 * 		Retorno True se a hora compreender o intervalo de 0 a 23 horas;
	 * 		0 a 59 minutos; 0 a 59 segundo. Se esse limite for violado
	 * 		o retorno sera False;
	 */
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
	
	/**
	 * Salvando o estado do <b>Sistema</b>
	 * 
	 * @param controller
	 * 			Estado a ser salvo, usado o controle pois nele
	 * 			esta contido todas as informacoes necessarias para
	 * 			restaruar o sistema. Informacoes salvas por meio de
	 * 			arquivo.
	 * 
	 * @throws RedeSocialMaisPopException
	 * 			Excecoes no trabalho com arquivos
	 */
	public void salvaSistema(Controller controller) throws RedeSocialMaisPopException {
		
		File file = new File("sistema.dat");
		FileOutputStream outFile;
		try {
			
			outFile = new FileOutputStream(file);
			BufferedOutputStream outStream = new BufferedOutputStream(outFile);
			ObjectOutputStream stream = new ObjectOutputStream(outStream);
			stream.writeObject(controller);
			stream.close();
					
		} catch (FileNotFoundException e) {
			throw new RedeSocialMaisPopException();
		} catch (IOException e) {
			throw new RedeSocialMaisPopException();
		}   // Erro ao fechar sistema
		
	}
	
	/**
	 * Metodo tem responsabilidade de recuperar todas as informacoes
	 * do <b>Sistema</b>, restaurando assim todo seu estado. As informacoes
	 * recuperadas estao em um arquivo.
	 * 
	 * @return Controller
	 * 			Instancia de controlle com o estado do sistema que anteriormente foi salvo.
	 */
	public Controller restauraSistema() {
		
		File file = new File("sistema.dat");
		Controller controller = null; 
		if (!file.exists()) {
			return null;
		}
		try {
			
			FileInputStream inFile = new FileInputStream(file);
			BufferedInputStream inStream = new BufferedInputStream(inFile);
			ObjectInputStream stream = new ObjectInputStream(inStream);
			
			controller = (Controller) stream.readObject();
			stream.close();			
			
		} catch (FileNotFoundException e) {
			System.out.println("FILENOT FOUND "+ e.getMessage());
		} catch (IOException e) {
			System.out.println("IOEX "+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("CLASS NOT FOUND "+ e.getMessage());
		}
		
		return controller;
		
	}

}