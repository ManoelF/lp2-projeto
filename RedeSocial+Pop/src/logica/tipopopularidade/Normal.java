package logica.tipopopularidade;

import logica.Post;


public class Normal implements TipoPopularidade  {
	private final int POPS = 10;
	private final int qntPostFeed = 2;
	
	@Override
	public void curtir(Post post) {
		post.curtir(POPS);
	}
	
	public void descurtir(Post post) {
		post.descurtir(POPS);
	}

	@Override
	public int qntPostFeed() {
		return this.qntPostFeed;
	}

}
