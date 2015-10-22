package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import logica.*;

public class TestePost {
	
	String textoUm, textoDois, textoTres, textoQuatro, textoMario, data, mensagem;
	Post postUm, postDois, novoDia, perdao, frio, recalque;
	FactoryPost fabricaPost;
	
	@Before
	public void setUp() {
		fabricaPost = new FactoryPost();

	}
	

	@Test
	public void testePost() {

		try {
			textoUm = "bom dia amigos faces passem bem! <imagem>imagens/bomDia.jpg</imagem> #Chang #Italo #Manoel";
			data = "01/08/2015 12:00:10";
			postUm = fabricaPost.criaPost(textoUm, data);
			
			Assert.assertEquals("bom dia amigos faces passem bem! <imagem>imagens/bomDia.jpg</imagem>", postUm.getPost("Conteudo"));
			Assert.assertEquals("bom dia amigos faces passem bem! <imagem>imagens/bomDia.jpg</imagem>", postUm.getPost("Mensagem"));
			Assert.assertEquals("#Chang,#Italo,#Manoel", postUm.getPost("Hashtags"));
			Assert.assertEquals("2015-08-01 12:00:10", postUm.getPost("Data"));
			Assert.assertEquals("$arquivo_imagem:imagens/bomDia.jpg", postUm.getMidias(1));
			
			Assert.assertEquals("2015-08-01 12:00:10", postUm.getDataString());
			Assert.assertEquals("bom dia amigos faces passem bem!", postUm.getMidias(0));
			Assert.assertEquals("$arquivo_imagem:imagens/bomDia.jpg", postUm.getMidias(1));
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
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
						 + " quanto reclamamos, e até mais alguma coisa.[...]"; 
			
			postDois = fabricaPost.criaPost(textoQuatro, "10/02/2002 12:21:00");
			
		} catch (PostException erro){
			Assert.assertEquals("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.", erro.getMessage());
		}
	}

	@Test
	public void testCurtirDescurtir() throws NaoHaNotificacoesException {
		try {
			
			textoMario = "A importância de lutar vivendo sempre o amor "
					          + "Agradecendo a este dia que brilha Presente igual não há #novoDia";
			
			perdao = fabricaPost.criaPost(textoMario, "04/08/2015 21:08:00");
			perdao.curtir(100);
			
			Assert.assertTrue(perdao.getLike() == 1);
			Assert.assertTrue(perdao.getPopularidade() == 100);
			
			perdao.descurtir(5);
			Assert.assertTrue(perdao.getDeslike() == 1);
			Assert.assertTrue(perdao.getPopularidade() == 95);
			
		} catch (EntradaException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testConteudos() {
		try {
			
			mensagem = "Esse frio esta mim deixando doida. #alucicrazy #CGDaDepressao";
			data = "01/08/2015 21:35:00";
			frio = fabricaPost.criaPost(mensagem, data);
			
			mensagem = "Nao sei porque tanto recalque, o que eh bonito eh pra se mostrar. "
					 + "<audio>musicas/poderosas.mp3</audio> #soulinda #naza";
			data = "03/08/2015 08:21:00";
			
			recalque = fabricaPost.criaPost(mensagem, data);
			
			Assert.assertEquals("Esse frio esta mim deixando doida.", frio.getPost("Conteudo"));
			Assert.assertEquals("#alucicrazy,#CGDaDepressao", frio.getPost("Hashtags"));
			Assert.assertEquals("$arquivo_audio:musicas/poderosas.mp3", recalque.getMidias(1));
			Assert.assertEquals("2015-08-01 21:35:00", frio.getPost("Data"));
			
			frio = fabricaPost.criaPost("#amor", data);
			
		} catch(EntradaException erro) {
			Assert.fail();
		}

	}
	
}
