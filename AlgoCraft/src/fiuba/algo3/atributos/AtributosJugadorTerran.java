package fiuba.algo3.atributos;

// No tiene sentido que se llame AtributosJugador
// Como mucho para eso seria AtributosRaza*nombre_raza*
// Tiene mas que ver con la raza

// Pero puede haber 2 jugadores de la misma raza con valores
// de atributos diferentes

public class AtributosJugadorTerran implements AtributosJugador {
	
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
	
	public AtributosEdificioRecolectorMineral getRecolectorMineral() {
		return cDeMineral;
	}
	
	public AtributosEdificioRecolectorGasVespeno getRecolectorGasVespeno() {
		return refineria;
	}	

	public AtributosEdificioIncrementadorPoblacion getIncrementadorPoblacion() {
		return deposito;
	}

	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesBasicas() {
		return barraca;
	}

	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesIntermedias() {
		return fabrica;
	}

	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesAvanzadas() {
		return puerto;
	}

	public AtributosUnidadAtaque getInfanteriaLivianaTerrestre() {
		return marine;
	}

	public AtributosUnidadAtaque getInfanteriaPesadaTerrestre() {
		return golliat;
	}

	public AtributosUnidadAtaque getInfanteriaPesadaArea() {
		return espectro;
	}

	public AtributosUnidadMagica getInfanteriaMagica() {
		return ciencia;
	}

	public AtributosUnidadTransporte getTransporte() {
		return transp;
	}
}
