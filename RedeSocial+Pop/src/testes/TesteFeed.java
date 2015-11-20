package testes;

import logica.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.RedeSocialMaisPopException;

public class TesteFeed {

	private Controller controller; 
	
	@Before
	public void setUp(){
		controller  = new Controller();
	}
	
	@Test
	public void testaFeed() throws RedeSocialMaisPopException {
		controller.cadastraUsuario("Melody","melodymusic@email.com.br","umadiva", "01/08/2007", "resources/melody.jpg");
		controller.cadastraUsuario("Ines Brasil", "inesbrasil@email.com", "deusehfiel", "25/10/1969", "resources/ines.jpg");
		controller.cadastraUsuario("MC Carol", "carolgatinha@email.com", "ondaforte", "05/07/1995", "resources/mccarol.jpg");
		controller.cadastraUsuario("Debora", "deboratdf@email.com", "falsetetop", "02/04/1985", "resources/debora.jpg");


		controller.login("melodymusic@email.com.br", "umadiva");
		controller.adicionaAmigo("inesbrasil@email.com");
		controller.adicionaAmigo("deboratdf@email.com");
		controller.adicionaAmigo("carolgatinha@email.com");

		controller.criaPost("Falem bem ou falem mal, mas falem de mim. <audio>musicas/falemdemim.mp3</audio>", "21/08/2015 12:20:00");
		controller.criaPost("Mais uma receitinha Melody... Sanduiche de pao pra vcs!", "21/08/2015 19:00:00");
		controller.criaPost("kkkkkk Debora, vamo mostrar cultura pra esse povo?", "22/08/2015 08:00:00");

		controller.setPops(400);
		controller.logout();

		controller.login("inesbrasil@email.com", "deusehfiel");
		controller.adicionaAmigo("carolgatinha@email.com");
		controller.adicionaAmigo("deboratdf@email.com");
		controller.aceitaAmizade("melodymusic@email.com.br");

		controller.criaPost("alo alo alo voces sabem quem sou eu?? alo alo graças a deus. #mechama #euvou", "22/08/2015 14:00:00");
		controller.criaPost("uuui que delicia de musica <audio>musicas/makelove.mp3</audio>", "22/08/2015 17:50:00");
		controller.criaPost("a vida eh assim: vamo fazendo. porque deus disse 'faça por onde que eu te ajudarei'. entao vamobora fazendo... #dancando #pulando", "25/08/2015 12:20:00");
		controller.criaPost("cabeca pro lado, corpinho pro outro. #vamodancando", "01/09/2015 10:00:00");
		controller.criaPost("ME CHAMA! Porque eu quero ir rapido assim oh. #sempreirmaos #sempreunidos #semprebrasil", "03/09/2015 20:00:00");
		
		controller.setPops(1100);
		controller.logout();

		controller.login("carolgatinha@email.com", "ondaforte");
		controller.adicionaAmigo("deboratdf@email.com");
		controller.aceitaAmizade("melodymusic@email.com.br");
		controller.aceitaAmizade("inesbrasil@email.com");

		controller.criaPost("Falar na cara ninguem quer, falar na cara ninguem gosta.", "23/08/2015 15:00:00");
		controller.criaPost("So fala da minha vida quando a sua for exemplo.", "24/08/2015 13:46:00");
		controller.criaPost("oh jorginho, me empresta a DOZE!", "26/08/2015 09:00:00");
		controller.criaPost("Enquanto uns ostentam cordoes, eu ostento refeicoes. <imagem>imagem/mccarolgourmet.jpg</imagem> #gourmet","02/09/2015 21:00:00");

		controller.setPops(600);
		controller.logout();

		// TESTE ORDENACAO POR DATA
		
		controller.login("deboratdf@email.com", "falsetetop");
		controller.aceitaAmizade("carolgatinha@email.com");
		controller.aceitaAmizade("melodymusic@email.com.br");
		controller.aceitaAmizade("inesbrasil@email.com");

		controller.ordenaFeedPorData();
		controller.atualizaFeed();
				
		Assert.assertEquals("Melody: Mais uma receitinha Melody... Sanduiche de pao pra vcs! 2015-08-21 19:00:00   Curtir(0) Rejeitar(0)", controller.getFeed(0));
		Assert.assertEquals("Melody: kkkkkk Debora, vamo mostrar cultura pra esse povo? 2015-08-22 08:00:00   Curtir(0) Rejeitar(0)", controller.getFeed(1));
		
		
		// TESTA ORDENACAO POR POPULARIDADE
				
		controller.curtirPost("carolgatinha@email.com", 0);
		
		controller.curtirPost("carolgatinha@email.com", 1);
		controller.curtirPost("carolgatinha@email.com", 1);
		controller.curtirPost("carolgatinha@email.com", 1);
		controller.curtirPost("carolgatinha@email.com", 1);
		
		controller.curtirPost("carolgatinha@email.com", 2);
		controller.curtirPost("carolgatinha@email.com", 2);
		controller.curtirPost("carolgatinha@email.com", 2);
		
		controller.curtirPost("carolgatinha@email.com", 3);
		controller.curtirPost("carolgatinha@email.com", 3);
		
		controller.ordenaFeedPorPopularidade();
		controller.atualizaFeed();
						
		Assert.assertEquals("MC Carol: Falar na cara ninguem quer, falar na cara ninguem gosta. 2015-08-23 15:00:00   Curtir(1) Rejeitar(0)", controller.getFeed(3));
		Assert.assertEquals("MC Carol: So fala da minha vida quando a sua for exemplo. 2015-08-24 13:46:00   Curtir(4) Rejeitar(0)", controller.getFeed(0));
		Assert.assertEquals("MC Carol: oh jorginho, me empresta a DOZE! 2015-08-26 09:00:00   Curtir(3) Rejeitar(0)", controller.getFeed(1));
		Assert.assertEquals("MC Carol: Enquanto uns ostentam cordoes, eu ostento refeicoes. $arquivo_imagem:imagem/mccarolgourmet.jpg #gourmet 2015-09-02 21:00:00   Curtir(2) Rejeitar(0)", controller.getFeed(2));
		Assert.assertEquals("Melody: Mais uma receitinha Melody... Sanduiche de pao pra vcs! 2015-08-21 19:00:00   Curtir(0) Rejeitar(0)", controller.getFeed(4));
		Assert.assertEquals("Melody: kkkkkk Debora, vamo mostrar cultura pra esse povo? 2015-08-22 08:00:00   Curtir(0) Rejeitar(0)", controller.getFeed(5));
	}
	
}
