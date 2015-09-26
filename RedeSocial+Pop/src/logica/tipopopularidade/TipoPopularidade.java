package logica.tipopopularidade;

import logica.Post;

public interface TipoPopularidade {
	
	public void curtir(Post post);
	
	public void descurtir(Post post);
}
