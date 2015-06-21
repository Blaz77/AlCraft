package fiuba.algo3.ocupantes.recurso;

public enum Tipo { //Especie / Nombre
	
	// Especial:
	DESCONOCIDO(TipoOcupante.DESCONOCIDO, ""),
	
	// Recursos:
	CELDA_VACIA(TipoOcupante.RECURSO, ""), //O CeldaVacia(TipoOcupante.CeldaVacia)
	//NO_RECURSO(TipoOcupante.Celda_Vacia) en vez del de arriba
	MINERAL(TipoOcupante.RECURSO, "Cristal"),
	VESPENO(TipoOcupante.RECURSO, "Volcan"),
	
	// Unidades Terran:
	MARINE(TipoOcupante.UNIDAD, "Marine"), //TipoRaza etc.
	
	// Edificios Terran:
	CENTRO_DE_MINERAL(TipoOcupante.EDIFICIO, "Marine"),
	REFINERIA(TipoOcupante.EDIFICIO, "Marine"),
	DEPOSITO_SUMINISTRO(TipoOcupante.EDIFICIO, "Marine"), 
	BARRACA(TipoOcupante.EDIFICIO, "Marine"),
	FABRICA(TipoOcupante.EDIFICIO, "Marine"),
	PUERTO_ESTELAR_TERRAN(TipoOcupante.EDIFICIO, "Marine"),
	
			
	// Edificios Protoss
	NEXO_MINERAL(TipoOcupante.EDIFICIO, "Marine"),
	PILON(TipoOcupante.EDIFICIO, "Marine"),
	ASIMILADOR(TipoOcupante.EDIFICIO, "Marine"),
	ACCESO(TipoOcupante.EDIFICIO, "Marine"),
	PUERTO_ESTELAR_PROTOSS(TipoOcupante.EDIFICIO, "Marine"),
	ARCHIVOS_TEMPLARIOS(TipoOcupante.EDIFICIO, "Marine"),	
	
	;
	
	private TipoOcupante t;
	private String s;
	
	Tipo(TipoOcupante t, String s){
		this.t = t;
		this.s = s;
	}
	
	//					getSuperTipo
	public TipoOcupante getTipoOcupante(){
		return this.t;
	}
	
	public String getNombre(){
		return this.s;
	}
}
