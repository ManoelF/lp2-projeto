package logica;

import logica.midia.Audio;
import logica.midia.Imagem;
import logica.midia.Mensagem;
import logica.midia.Midia;
import logica.midia.Video;

public class FactoryMidia {
	
	Midia midia;
	public FactoryMidia() {
	}
	
	public Midia obtemMidias(String midia) { 
		if (midia.contains("$arquivo_imagem:")) {
			this.midia = new Imagem(midia);
			return this.midia;
		} else if (midia.contains("$arquivo_audio")) {
			this.midia = new Audio(midia);
			return this.midia;
		} else if (midia.contains("$arquivo_video")) {
			this.midia = new Video(midia);
			return this.midia;
		} else { //Mensagem
			this.midia = new Mensagem(midia);
			return this.midia;
		}
	}
}
