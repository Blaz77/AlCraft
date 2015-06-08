package fiuba.algo3.atributos;

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
	
	public AtributosEdificioRecolectorMineral getRecolectorMineral() {
		return nexoMineral;
	}
	
	public AtributosEdificioRecolectorGasVespeno getRecolectorGasVespeno() {
		return asimilador;
	}
	
	public AtributosEdificioIncrementadorPoblacion getIncrementadorPoblacion() {
		return pilon;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesBasicas() {
		return acceso;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesIntermedias() {
		return archivosTempolarios;
	}
	
	public AtributosEdificioEntrenadorUnidades getEntrenadorUnidadesAvanzadas() {
		return puerto;
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
