package logica;

public class CelebridadePOP implements TipoPopularidade {

	@Override
	public void curtir(Post post) {
		post.curtir(50);
	}

	@Override
	public void descurtir(Post post) {
		post.curtir(50);
	}
}
