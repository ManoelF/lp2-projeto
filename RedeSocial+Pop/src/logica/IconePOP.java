package logica;

import java.util.Date;

public class IconePOP implements TipoPopularidade {

	@Override
	public void curtir(Post post) {
		Date dataAtual = new Date();
		int pontos = 25;
		if (post.getData().equals(dataAtual)) {
			pontos += 10;
		}
		post.curtir(pontos);
	}

	@Override
	public void descurtir(Post post) {
		Date dataAtual = new Date();
		int pontos = 25;
		if (post.getData().equals(dataAtual)) {
			pontos += 10;
		}
		post.descurtir(pontos);

	}
}
