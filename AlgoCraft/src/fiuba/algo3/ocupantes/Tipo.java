package fiuba.algo3.ocupantes;

import fiuba.algo3.raza.TipoRaza;

public enum Tipo { //Especie / Nombre
	
	// Especial:
	DESCONOCIDO(TipoOcupante.DESCONOCIDO, "?"),
	
	// Recursos:
	CELDA_VACIA(TipoOcupante.RECURSO, ""), //O CeldaVacia(TipoOcupante.CeldaVacia)
	//NO_RECURSO(TipoOcupante.Celda_Vacia) en vez del de arriba
	MINERAL(TipoOcupante.RECURSO, "Cristal"),
	VESPENO(TipoOcupante.RECURSO, "Volcan"),
	
	// Unidades Terran:
	MARINE(TipoOcupante.UNIDAD, TipoRaza.TERRAN, "Marine"),
	GOLLIAT(TipoOcupante.UNIDAD, TipoRaza.TERRAN, "Golliat"),
	ESPECTRO(TipoOcupante.UNIDAD, TipoRaza.TERRAN, "Espectro"),
	NAVE_DE_CIENCIA(TipoOcupante.UNIDAD, TipoRaza.TERRAN, "Nave de ciencia"),
	NAVE_DE_TRANSPORTE_TERRAN(TipoOcupante.UNIDAD, TipoRaza.TERRAN, "Nave de transporte"),
	
	// Unidades Protoss:
	ZEALOT(TipoOcupante.UNIDAD, TipoRaza.PROTOSS, "Zealot"),
	SCOUT(TipoOcupante.UNIDAD, TipoRaza.PROTOSS, "Scout"),
	DRAGON(TipoOcupante.UNIDAD, TipoRaza.PROTOSS, "Dragon"),
	ALTO_TEMPLARIO(TipoOcupante.UNIDAD, TipoRaza.PROTOSS, "Alto Templario"),
	NAVE_DE_TRANSPORTE_PROTOSS(TipoOcupante.UNIDAD, TipoRaza.PROTOSS, "Nave de transporte"),
	
	// Edificios Terran:
	CENTRO_DE_MINERAL(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Centro de Mineral"),
	REFINERIA(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Refineria"),
	DEPOSITO_SUMINISTRO(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Deposito De Suministros"), 
	BARRACA(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Barraca"),
	FABRICA(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Fabrica"),
	PUERTO_ESTELAR_TERRAN(TipoOcupante.EDIFICIO, TipoRaza.TERRAN, "Puerto Estelar"),
	
			
	// Edificios Protoss
	NEXO_MINERAL(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Nexo Mineral"),
	PILON(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Pilon"),
	ASIMILADOR(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Asimilador"),
	ACCESO(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Acceso"),
	PUERTO_ESTELAR_PROTOSS(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Puerto Estelar"),
	ARCHIVOS_TEMPLARIOS(TipoOcupante.EDIFICIO, TipoRaza.PROTOSS, "Archivos Templarios"),	
	
	;
	
	private TipoOcupante ocupante;
	private String nombre;
	private TipoRaza raza;
	
	Tipo(TipoOcupante ocupante, String nombre){
		this(ocupante, null, nombre);
	}
	
	Tipo(TipoOcupante ocupante, TipoRaza raza, String nombre){
		this.ocupante = ocupante;
		this.nombre = nombre;
		this.raza = raza;
	}
	
	//					getSuperTipo
	public TipoOcupante getTipoOcupante(){
		return this.ocupante;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	//				getRaza
	public TipoRaza getTipoRaza(){
		return this.raza;
	}
}
