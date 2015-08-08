package testes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import exceptions.*;
import logica.*;

public class TestePost {
	
	Usuario jane, fred, mario, bruna, marcos;
	String textoUm, textoDois, textoTres, textoQuatro,data, mensagem;
	Post postUm, postDois, postTres, postQuatro;
	Post novoDia;
	

	@Test
	public void testePost() throws PostException {

		try {
			textoUm = "bom dia amigos faces passem bem! #Chang #Italo #Manoel";
			data = "01/08/2015 12:00:00";
			postUm = new Post(textoUm, data);
			
			Date data1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/08/2015 12:00:00");

			textoDois = "";
			postDois = new Post(textoDois, data);

			textoTres = "oi migs, sdds <3";
			postTres = new Post(textoTres, data);

			Assert.assertEquals("#Italo", postUm.getHashtags().get(1));
			Assert.assertEquals("#Chang", postUm.getHashtags().get(0));
			Assert.assertNotEquals("#Jose", postUm.getHashtags().get(2));

			Assert.assertTrue(postDois.getHashtags().size() == 0);
			Assert.assertTrue(postTres.getHashtags().size() == 0);
			Assert.assertEquals("oi migs, sdds <3", postTres.getTexto());

		} catch (PostException erro) {
			System.out.println(erro.getMessage());
			Assert.fail();
		} catch (ParseException erro){ 
			System.out.println(erro.getMessage());
		}

	}

	@Test
	public void testPostInvalido(){
		
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
			
			postQuatro = new Post(textoQuatro, "10/02/2002 12:21:00");
			
		} catch (ParseException erro) {
			System.out.println(erro.getMessage());
		} catch (PostTamException erro){
			Assert.assertEquals("O tamanho do post nao deve exceder 200 caracteres.", erro.getMessage());
		} catch (PostException erro) {
			System.out.println(erro.getMessage());
		}
	}

	@Test
	public void testCurtirDescurtir() throws NaoHaNotificacoesException {
		try {
			mario =  new Usuario("Mario", "mario@email.com", "2134", "21/03/1988", "resources/mario.jpg");
			jane =  new Usuario("Jane", "jane@email.com", "76543", "11/09/1985", "resources/jane.jpg");
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/1989", "resources/fred.jpg");
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000", "resources/bruna.jpg");
			marcos = new Usuario("Marcos", "marcos@email.com", "0988", "31/01/1992", "resources/marcos.jpg");
			
			String textoMario = "A importância de lutar vivendo sempre o amor "
					          + "Agradecendo a este dia que brilha Presente igual não há #novoDia";
			
			mario.criaPost(textoMario, "04/08/2015 21:08:00");
			
			jane.curtir(mario, 0);
			fred.curtir(mario, 0);
			bruna.curtir(mario, 0);
			
			
			Assert.assertTrue(mario.getNotificacoes() == 3);
			Assert.assertTrue(mario.getPop() == 30);
			Assert.assertEquals("Jane curtiu seu post de 2015-08-04 21:08:00.", mario.getNextNotificacao());
			Assert.assertEquals("Fred curtiu seu post de 2015-08-04 21:08:00.", mario.getNextNotificacao());
			Assert.assertEquals("Bruna curtiu seu post de 2015-08-04 21:08:00.", mario.getNextNotificacao());
			Assert.assertTrue(mario.getNotificacoes() == 0);
			
			jane.descurtir(mario, 0);

			Assert.assertTrue(mario.getPop() == 20);
			Assert.assertTrue(mario.getNotificacoes() == 1);
			Assert.assertEquals("Jane descurtiu seu post de 2015-08-04 21:08:00.", mario.getNextNotificacao());
			
			//} catch (LogicaException erro) {
			//System.out.println(erro.getMessage());
		} catch (EntradaException erro) {
			System.out.println(erro.getMessage());
		} catch (ParseException erro) {
			System.out.println(erro.getMessage());
		}
	}
	
	@Test
	public void testArquivos() {
		try {
			
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000", "resources/bruna.jpg");
			
			mensagem = "Esse frio esta mim deixando doida. #alucicrazy #CGDaDepressao";
			data = "01/08/2015 21:35:00";
			bruna.criaPost(mensagem, data);
			
			mensagem = "Nao sei porque tanto recalque, o que eh bonito eh pra se mostrar. "
					 + "<audio>musicas/poderosas.mp3</audio> #soulinda #naza";
			data = "03/08/2015 08:21:00";
			
			bruna.criaPost(mensagem, data);
			
			Assert.assertEquals("$arquivo_audio:musicas/poderosas.mp3", bruna.getArquivo(0, 1));
			Assert.assertEquals("#alucicrazy,#CGDaDepressao", bruna.getConteudo("Hashtags", 0));
			Assert.assertEquals("2015-08-01 21:35:00", bruna.getConteudo("Data", 0));
			Assert.assertEquals("Esse frio esta mim deixando doida. ", bruna.getConteudo("Conteudo", 0));
			
		} catch(EntradaException erro) {
			
		} catch(ParseException erro) {
			
		}
			
	}

}
