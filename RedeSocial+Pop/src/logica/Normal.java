package logica;


public class Normal implements TipoPopularidade  {
	private final int POPS = 10;
	
	@Override
	public void curtir(Post post) {
		post.curtir(POPS);
	}
	
	public void descurtir(Post post) {
		post.descurtir(POPS);
	}

}
