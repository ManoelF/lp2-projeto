package logica;

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
		} else {
			this.midia = new Video("$arquivo_video");
			return this.midia;
		}
	}
}
