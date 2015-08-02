package logica;


public class Normal implements TipoPopularidade  {

	@Override
	public void curtir(Post post) {
		post.curtir(10);
	}
	
	public void descurtir(Post post) {
		post.curtir(10);
	}

}
