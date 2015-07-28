package testes;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.PostException;
import logica.*;

public class TestePost {

	String textoUm, textoDois, textoTres ;
	Post postUm, postDois, postTres;
	
	@Test
	public void testePost() throws PostException{
		
		try {
			textoUm = "bom dia amigos faces #Chang #Italo e #Manoel passem bem!";
			postUm = new Post(textoUm);
			
			textoDois = "";
			postDois = new Post(textoDois);
			
			textoTres = "oi migs, sdds <3";
			postTres = new Post(textoTres);
			
			Assert.assertEquals("#Italo", postUm.getHashtags().get(1));
			Assert.assertEquals("#Chang", postUm.getHashtags().get(0));
			Assert.assertNotEquals("#Jose", postUm.getHashtags().get(2));
			
			Assert.assertTrue(postDois.getHashtags().size() == 0);
			Assert.assertTrue(postTres.getHashtags().size() == 0);
			Assert.assertEquals("oi migs, sdds <3", postTres.getTexto());
			
		} catch (PostException erro) {	
			Assert.fail();
		}
		
		
		
		
	}
	
	@Test
	public void testPostInvalido() {
		
		fail("Not yet implemented");
	}

}
