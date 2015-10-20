package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logica.midia.Mensagem;
import logica.midia.Midia;
import exceptions.PostException;

public class FactoryPost {
	
	private Post post;
	private Util util;
	private FactoryMidia fabricaMidia;
	
	public FactoryPost() {
		this.util = Util.getInstancia();
		this.fabricaMidia = new FactoryMidia();
	}
	
	public Post criaPost(String texto, String data) throws PostException {
		if (texto == null || texto.trim().length() == 0) {
			// lancar Exception
		}
		
		String[] dataHorario = data.split(" ");
		String dataS = dataHorario[0];
		String horaS = dataHorario[1];
		
		if (util.verificaFormatoData(dataS) == false) {
			// lanca execao data formato invalido
		}
		if (util.verificaFormatoHora(horaS) == false) {
			// lanca execao data formato invalido
		}
		if (util.verificaDataValida(dataS) == false) {
			// lanca excecao data invalida
		}
		if (util.verificaHoraValida(horaS) == false) {
			// lanca excecao data invalida
		}
		LocalDateTime dataPost = util.converteParaData(data);
		List<String> hashtags = util.encontraHashtag(texto);
		List<Midia> midias = buscaMidia(texto);
		verificaTam(texto);
		
		this.post = new Post(texto, dataPost, hashtags,midias);
		
		return  this.post;
	}
	

	
	private List<Midia> buscaMidia(String mensagem) {
		this.fabricaMidia = new FactoryMidia();
		List<String> listMidias = util.getMidia(mensagem);
		List<Midia> midias = new ArrayList<>();
		
		Midia mensagem2 = new Mensagem(util.encontraTexto(mensagem));
		if (!mensagem2.toString().equals("")) {
			midias.add(mensagem2);
		}
		
		for (String arquivo: listMidias) {
			midias.add(this.fabricaMidia.obtemMidias(arquivo));
		}
		return midias;
	}
	
	
	private boolean verificaTam(String texto) throws PostException {
		String novoTexto = Util.getInstancia().encontraTexto(texto);
		if (novoTexto.length() >= 200) {
			throw new PostException("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		} else {
			//this.texto = texto;
			return true;
		}
	}
	
	
	
	
}
