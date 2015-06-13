package fiuba.algo3.atributos.jugador;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.edificios.AtributosEdificioEntrenadorUnidades;
import fiuba.algo3.atributos.edificios.protoss.AtributosAcceso;
import fiuba.algo3.atributos.edificios.protoss.AtributosArchivosTemplarios;
import fiuba.algo3.atributos.edificios.protoss.AtributosAsimilador;
import fiuba.algo3.atributos.edificios.protoss.AtributosNexoMineral;
import fiuba.algo3.atributos.edificios.protoss.AtributosPilon;
import fiuba.algo3.atributos.edificios.protoss.AtributosPuertoEstelarProtoss;
import fiuba.algo3.atributos.unidades.AtributosUnidadAtaque;
import fiuba.algo3.atributos.unidades.AtributosUnidadMagica;
import fiuba.algo3.atributos.unidades.AtributosUnidadTransporte;
import fiuba.algo3.atributos.unidades.protoss.AtributosAltoTemplario;
import fiuba.algo3.atributos.unidades.protoss.AtributosDragon;
import fiuba.algo3.atributos.unidades.protoss.AtributosNaveDeTransporteProtoss;
import fiuba.algo3.atributos.unidades.protoss.AtributosScout;
import fiuba.algo3.atributos.unidades.protoss.AtributosZealot;

public class AtributosJugadorProtoss implements AtributosJugador {


	private AtributosNexoMineral nexoMineral = new AtributosNexoMineral();
	private AtributosAsimilador asimilador = new AtributosAsimilador();
	private AtributosPilon pilon = new AtributosPilon();
	private AtributosAcceso acceso = new AtributosAcceso();
	private AtributosArchivosTemplarios archivosTempolarios = new AtributosArchivosTemplarios();
	private AtributosPuertoEstelarProtoss puerto = new AtributosPuertoEstelarProtoss();
	
	private AtributosZealot zealot = new AtributosZealot();
	private AtributosDragon dragon = new AtributosDragon();
	private AtributosScout scout = new AtributosScout();
	private AtributosAltoTemplario altoTemplario = new AtributosAltoTemplario();
	private AtributosNaveDeTransporteProtoss transporte = new AtributosNaveDeTransporteProtoss();
	
	public AtributosEdificio getRecolectorMineral() {
		return nexoMineral;
	}
	
	public AtributosEdificio getRecolectorGasVespeno() {
		return asimilador;
	}
	
	public AtributosEdificio getIncrementadorPoblacion() {
		return pilon;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesBasicas() {
		return acceso;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesIntermedias() {
		return puerto;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesAvanzadas() {
		return archivosTempolarios;
	}
	
	public AtributosUnidadAtaque getInfanteriaLivianaTerrestre() {
		return zealot;
	}
	
	public AtributosUnidadAtaque getInfanteriaPesadaTerrestre() {
		return dragon;
	}
	
	public AtributosUnidadAtaque getInfanteriaPesadaArea() {
		return scout;
	}

	public AtributosUnidadMagica getInfanteriaMagica(){
		return altoTemplario;
	}
	
	public AtributosUnidadTransporte getTransporte(){
		return transporte;
	}


}
