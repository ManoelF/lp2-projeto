package testes;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncontraHasTest {

	@Test
	public void test() {
		EncontraHas enc = new EncontraHas();
		String textoUm = "bom dia amigos  faces passem bem! <imagem>imagens/bomDia.jpg</imagem> #Chang #Italo #Manoel";
		enc.encontraHash(textoUm);
	}

}
