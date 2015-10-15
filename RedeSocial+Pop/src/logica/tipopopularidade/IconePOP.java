package logica.tipopopularidade;

import logica.Post;

public class IconePOP implements TipoPopularidade {
	private final int POPS = 50;
	private final int qntPostFeed = 6;
	
	@Override
	public void curtir(Post post) {
		post.curtir(POPS);
		post.adicionaHashtag("#epicwin");
	}

	@Override
	public void descurtir(Post post) {
		post.descurtir(POPS);
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
