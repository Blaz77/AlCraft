package fiuba.algo3.atributos;

public class AtributosTerran implements Atributos {
	
	private AtributosBarraca barraca;
	private AtributosRefineria refineria = new AtributosRefineria();
	
	public AtributosBarraca getBarraca(){
		return barraca;
	}
	
	public AtributosRefineria getRefineria(){
		return refineria;
	}

}
