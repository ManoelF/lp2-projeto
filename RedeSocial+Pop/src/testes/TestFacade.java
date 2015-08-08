package testes;

import static org.junit.Assert.*;
import logica.*;

import org.junit.Assert;
import org.junit.Test;

public class TestFacade {

	@Test
	public void test() {
		Facade facade = new Facade();
		Usuario maria;
		try {
			maria = facade.cadastraUsuario("Maria", "maria@gmail.com", "67", "10/10/2010", "");
			Usuario id1 = facade.cadastraUsuario("Fatima Bernardes", "fatima@email.com.br","will_S2","17/09/1962" ,"resources/fatima.jpg");
			Assert.assertEquals("Maria", facade.getInfoUsuaio("Nome", maria));
			facade.getInfoUsuaio("Nome", id1);
		} catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}

}
