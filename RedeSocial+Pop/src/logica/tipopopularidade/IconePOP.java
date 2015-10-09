package logica.tipopopularidade;

import logica.Post;

public class IconePOP implements TipoPopularidade {
	private final int POPS = 50;
	private final int qntPostFeed = 6;
	
	@Override
	public void curtir(Post post) {
		post.curtir(50);
		post.adicionaHashtag("#epicwin");
	}

	@Override
	public void descurtir(Post post) {
		post.descurtir(50);
		post.adicionaHashtag("#epicfail");
	}
	
	@Override
	public int qntPostFeed() {
		return this.qntPostFeed;
	}
	
	public String popularidade() {
		return "Icone Pop";
	}
}
