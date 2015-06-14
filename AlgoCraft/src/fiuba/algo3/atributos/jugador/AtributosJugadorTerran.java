package fiuba.algo3.atributos.jugador;

import fiuba.algo3.A_BORRAR.AtributosEdificioIncrementadorPoblacion;
import fiuba.algo3.A_BORRAR.AtributosEdificioRecolectorGasVespeno;
import fiuba.algo3.A_BORRAR.AtributosEdificioRecolectorMineral;
import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.atributos.edificios.terran.AtributosBarraca;
import fiuba.algo3.atributos.edificios.terran.AtributosCentroDeMineral;
import fiuba.algo3.atributos.edificios.terran.AtributosDepositoDeSuministros;
import fiuba.algo3.atributos.edificios.terran.AtributosFabrica;
import fiuba.algo3.atributos.edificios.terran.AtributosPuertoEstelar;
import fiuba.algo3.atributos.edificios.terran.AtributosRefineria;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;
import fiuba.algo3.atributos.unidades.terran.AtributosEspectro;
import fiuba.algo3.atributos.unidades.terran.AtributosGolliat;
import fiuba.algo3.atributos.unidades.terran.AtributosMarine;
import fiuba.algo3.atributos.unidades.terran.AtributosNaveDeCiencia;
import fiuba.algo3.atributos.unidades.terran.AtributosNaveDeTransporte;

// No tiene sentido que se llame AtributosJugador
// Como mucho para eso seria AtributosRaza*nombre_raza*
// Tiene mas que ver con la raza

// Pero puede haber 2 jugadores de la misma raza con valores
// de atributos diferentes

public class AtributosJugadorTerran implements AtributosJugador {
	
	private AtributosMarine marine = new AtributosMarine();
	private AtributosGolliat golliat = new AtributosGolliat();
	private AtributosEspectro espectro = new AtributosEspectro();
	private AtributosNaveDeCiencia ciencia = new AtributosNaveDeCiencia();
	private AtributosNaveDeTransporte transp = new AtributosNaveDeTransporte();
	
	private AtributosCentroDeMineral cDeMineral = new AtributosCentroDeMineral();
	private AtributosRefineria refineria = new AtributosRefineria();
	private AtributosDepositoDeSuministros deposito = new AtributosDepositoDeSuministros();
	private AtributosBarraca barraca = new AtributosBarraca(marine);
	private AtributosFabrica fabrica = new AtributosFabrica(golliat);
	private AtributosPuertoEstelar puerto = new AtributosPuertoEstelar(espectro, ciencia, transp);
	
	public AtributosEdificio getRecolectorMineral() {
		return cDeMineral;
	}
	
	public AtributosEdificio getRecolectorGasVespeno() {
		return refineria;
	}	

	public AtributosEdificio getIncrementadorPoblacion() {
		return deposito;
	}

	public AtributosEdificio getEntrenadorUnidadesBasicas() {
		return barraca;
	}

	public AtributosEdificio getEntrenadorUnidadesIntermedias() {
		return fabrica;
	}

	public AtributosEdificio getEntrenadorUnidadesAvanzadas() {
		return puerto;
	}

	public AtributosUnidad getInfanteriaLivianaTerrestre() {
		return marine;
	}

	public AtributosUnidad getInfanteriaPesadaTerrestre() {
		return golliat;
	}

	public AtributosUnidad getInfanteriaPesadaArea() {
		return espectro;
	}

	public AtributosUnidad getInfanteriaMagica() {
		return ciencia;
	}

	public AtributosUnidad getTransporte() {
		return transp;
	}
}
