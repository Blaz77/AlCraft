package fiuba.algo3.atributos;

public class AtributosJugadorProtoss implements AtributosJugador {


	private AtributosNexoMineral nexoMineral = new AtributosNexoMineral();
	private AtributosAsimilador asimilador = new AtributosAsimilador();
	private AtributosPilon pilon = new AtributosPilon();
	private AtributosAcceso acceso = new AtributosAcceso();
	private AtributosArchivosTemplarios archivosTempolarios = new AtributosArchivosTemplarios();
	private AtributosPuertoEstelarProtoss puerto = new AtributosPuertoEstelarProtoss();
	
	private AtributosMarine zealot = new AtributosMarine();
	private AtributosGolliat dragon = new AtributosGolliat();
	private AtributosEspectro scout = new AtributosEspectro();
	private AtributosNaveDeCiencia altoTemplario = new AtributosNaveDeCiencia();
	private AtributosNaveDeTransporte transporte = new AtributosNaveDeTransporte();
	
	public AtributosNexoMineral getRecolectorMineral() {
		return nexoMineral;
	}
	
	public AtributosAsimilador getRecolectorGasVespeno() {
		return asimilador;
	}
	
	public AtributosPilon getIncrementadorPoblacion() {
		return pilon;
	}
	
	public AtributosAcceso getEntrenadorUnidadesBasicas() {
		return acceso;
	}
	
	public AtributosArchivosTemplarios getEntrenadorUnidadesIntermedias() {
		return archivosTempolarios;
	}
	
	public AtributosPuertoEstelarProtoss getEntrenadorUnidadesAvanzadas() {
		return puerto;
	}
	
	public AtributosMarine getMarine() {
		return zealot;
	}
	
	public AtributosGolliat getGolliat() {
		return dragon;
	}
	
	public AtributosEspectro getEspectro() {
		return scout;
	}

	public AtributosNaveDeCiencia getNaveDeCiencia(){
		return altoTemplario;
	}
	
	public AtributosNaveDeTransporte getNaveDeTransporte(){
		return transporte;
	}


}
