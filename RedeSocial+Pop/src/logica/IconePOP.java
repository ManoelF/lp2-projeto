package logica;

public class IconePOP implements TipoPopularidade {
	private final int POPS = 50;
	
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
}
