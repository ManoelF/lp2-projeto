package testes;

import org.junit.Assert;
import org.junit.Test;

import exceptions.*;
import logica.*;

public class TestePost {

	String textoUm, textoDois, textoTres, textoQuatro;
	Post postUm, postDois, postTres, postQuatro;

	@Test
	public void testePost() throws PostException {

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
	public void testPostInvalido() throws PostException {
		
		try{
			textoQuatro = "Tenha a bondade de dizer à minha mãe que me ocuparei, da melhor forma possível, do negócio de"
						 + " que ela me incumbiu e mandarei notícias dentro em breve. Avistei-me com minha tia; não a achei"
						 + " tão má como a pitam em nossa casa. É uma mulher vivaz, arrebatada, de excelente coração. Expuslhe" 
						 + " os prejuízos causados à minha mãe pela retenção da parte que lhe cabe na herança; minha tia"
						 + " contou-me os motivos, dizendo-me as condições mediante as quais está disposta a enviar-nos tudo"
						 + " quanto reclamamos, e até mais alguma coisa. A respeito, é o que há por hoje; fale à minha mãe que"
						 + " tudo se arranjara. Esse pequeno caso, meu amigo, demonstrou-me, ainda uma vez, que os malentendidos"
						 + " e a indolencia talvez produzam mais discórdias no mundo do que a duplicidade e a"
						 + " maldade; pelo menos, estas duas ultimas são mais raras"; 
			
			postQuatro = new Post(textoQuatro);
			
		} catch(PostTamException e){
			Assert.assertEquals("O tamanho do post nao deve exceder 400 caracteres.", e.getMessage());
			
		}
	}
}
