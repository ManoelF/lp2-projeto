package testes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TesteTratamento {


	
	@Test
	public void testNomeFile(){
		salvaPosts("bola");
	}
	
	
	
	
	
	
	private void salvaPosts(String nome) {
		File arquivo = null;
		FileWriter fluxoSaida = null;
		try {
			arquivo = new File("postsUsuarios/" + nome + ".txt");
			
			
			if (arquivo.exists()) {
				fluxoSaida = new FileWriter(arquivo, true);
				
			} else {
				fluxoSaida = new FileWriter(arquivo);
			}
			
			BufferedWriter stream = new BufferedWriter(fluxoSaida);
			stream.append(nome);
			stream.close();
			
		} catch (FileNotFoundException erro) {
			System.out.println(erro.getMessage() + "FlNF");
		} catch (IOException erro) {
			System.out.println(erro.getMessage() + "IOE");
		}
		
		
	}
	
	
	
	
	
	
	
	
}
