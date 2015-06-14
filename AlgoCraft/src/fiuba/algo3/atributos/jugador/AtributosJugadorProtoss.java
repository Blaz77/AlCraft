package fiuba.algo3.atributos.jugador;

import fiuba.algo3.atributos.edificios.AtributosEdificio;
import fiuba.algo3.atributos.edificios.protoss.AtributosAcceso;
import fiuba.algo3.atributos.edificios.protoss.AtributosArchivosTemplarios;
import fiuba.algo3.atributos.edificios.protoss.AtributosAsimilador;
import fiuba.algo3.atributos.edificios.protoss.AtributosNexoMineral;
import fiuba.algo3.atributos.edificios.protoss.AtributosPilon;
import fiuba.algo3.atributos.edificios.protoss.AtributosPuertoEstelarProtoss;
import fiuba.algo3.atributos.unidades.AtributosUnidad;
import fiuba.algo3.atributos.unidades.protoss.AtributosAltoTemplario;
import fiuba.algo3.atributos.unidades.protoss.AtributosDragon;
import fiuba.algo3.atributos.unidades.protoss.AtributosNaveDeTransporteProtoss;
import fiuba.algo3.atributos.unidades.protoss.AtributosScout;
import fiuba.algo3.atributos.unidades.protoss.AtributosZealot;

public class AtributosJugadorProtoss implements AtributosJugador {

	private AtributosZealot zealot = new AtributosZealot();
	private AtributosDragon dragon = new AtributosDragon();
	private AtributosScout scout = new AtributosScout();
	private AtributosAltoTemplario altoTemplario = new AtributosAltoTemplario();
	private AtributosNaveDeTransporteProtoss transporte = new AtributosNaveDeTransporteProtoss();

	private AtributosNexoMineral nexoMineral = new AtributosNexoMineral();
	private AtributosAsimilador asimilador = new AtributosAsimilador();
	private AtributosPilon pilon = new AtributosPilon();
	private AtributosAcceso acceso = new AtributosAcceso(zealot, dragon);
	private AtributosArchivosTemplarios archivosTemplarios = 
			new AtributosArchivosTemplarios(altoTemplario);
	private AtributosPuertoEstelarProtoss puerto = 
			new AtributosPuertoEstelarProtoss(scout, transporte);
	
	
	public AtributosEdificio getRecolectorMineral() {
		return nexoMineral;
	}
	
	public AtributosEdificio getRecolectorGasVespeno() {
		return asimilador;
	}
	
	public AtributosEdificio getIncrementadorPoblacion() {
		return pilon;
	}
	
	public AtributosEdificio getEntrenadorUnidadesBasicas() {
		return acceso;
	}
	
	public AtributosEdificio getEntrenadorUnidadesIntermedias() {
		return puerto;
	}
	
	public AtributosEdificio getEntrenadorUnidadesAvanzadas() {
		return archivosTemplarios;
	}
	
	public AtributosUnidad getInfanteriaLivianaTerrestre() {
		return zealot;
	}
	
	public AtributosUnidad getInfanteriaPesadaTerrestre() {
		return dragon;
	}
	
	public AtributosUnidad getInfanteriaPesadaArea() {
		return scout;
	}

	public AtributosUnidad getInfanteriaMagica(){
		return altoTemplario;
	}
	
	public AtributosUnidad getTransporte(){
		return transporte;
	}


}
