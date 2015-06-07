package fiuba.algo3.atributos;

public class AtributosTerran implements Atributos {
	
	private AtributosCentroDeMineral cDeMineral = new AtributosCentroDeMineral();
	private AtributosRefineria refineria = new AtributosRefineria();
	private AtributosDepositoDeSuministros deposito = new AtributosDepositoDeSuministros();
	private AtributosBarraca barraca = new AtributosBarraca();
	private AtributosFabrica fabrica = new AtributosFabrica();
	private AtributosPuertoEstelar puerto = new AtributosPuertoEstelar();
	
	private AtributosMarine marine = new AtributosMarine();
	private AtributosGolliat golliat = new AtributosGolliat();
	private AtributosEspectro espectro = new AtributosEspectro();
	private AtributosNaveDeCiencia ciencia = new AtributosNaveDeCiencia();
	private AtributosNaveDeTransporte transp = new AtributosNaveDeTransporte();
	
	public AtributosCentroDeMineral getCentroDeMineral(){
		return cDeMineral;
	}
	
	public AtributosRefineria getRefineria(){
		return refineria;
	}
	
	public AtributosDepositoDeSuministros getDepositoDeSuministros(){
		return deposito;
	}
	
	public AtributosBarraca getBarraca(){
		return barraca;
	}
	
	public AtributosFabrica getFabrica(){
		return fabrica;
	}
	
	public AtributosPuertoEstelar getPuertoEstelar(){
		return puerto;
	}
	
	public AtributosMarine getMarine(){
		return marine;
	}
	
	public AtributosGolliat getGolliat(){
		return golliat;
	}
	
	public AtributosEspectro getEspectro(){
		return espectro;
	}

	public AtributosNaveDeCiencia getNaveDeCiencia(){
		return ciencia;
	}
	
	public AtributosNaveDeTransporte getNaveDeTransporte(){
		return transp;
	}
}
