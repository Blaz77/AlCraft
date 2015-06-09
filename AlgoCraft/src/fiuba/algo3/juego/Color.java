package fiuba.algo3.juego;

import fiuba.algo3.raza.TipoRaza;

public enum Color {
	
	ROJO		(		"Guardia Élite",	"Cría Tiamat",     "Tribu Ara"),
	AZUL		(			 "Mar Sara",	"Cría Surtur",  "Tribu Sargas"), 
	AZUL_TEAL	("Coalición Kel-Morian",	"Cría Fenris",  "Tribu Akilae"),
	PURPURA		(			   "Antiga","Cría Jormungand", "Tribu Furinax"),
	NARANJA		(	  "Escuadrón Delta",	  "Cría Garm",  "Tribu Auriga"),
	MARRON		(	  "Escuadrón Omega",   "Cría Grendel", "Tribu Venatir"),
	BLANCO		(	   "Escuadrón Alfa",   "Cría Baelrog",  "Tribu Shelak"),
	AMARILLO	(	"Escuadrón Epsilon", "Cría Leviathan",  "Tribu Velari");
	
	/* En ingles suena mejor joder
	ROJO		(		"Elite Guard", 		"Tiamat Brood", 	"Ara Tribe"),
	AZUL		(		   "Mar Sara", 		"Surtur Brood",  "Sargas Tribe"), 
	AZUL_TEAL	("Kel-Morian Combine", 		"Fenris Brood",  "Akilae Tribe"),
	PURPURA		(			 "Antiga", 	"Jormungand Brood", "Furinax Tribe"),
	NARANJA		(	 "Delta Squadron", 		  "Garm Brood",  "Auriga Tribe"),
	MARRON		(	 "Omega Squadron", 	   "Grendel Brood", "Venatir Tribe"),
	BLANCO		(	 "Alpha Squadron",	   "Baelrog Brood",  "Shelak Tribe"),
	AMARILLO	(  "Epsilon Squadron",	 "Leviathan Brood",  "Velari Tribe");
	*/
	
	private String[] nombresPorDefecto;
	
	// Esto es en el juego posta, donde no se puede elegir nombre. Pienso
	// usarlos como nombres x defecto, a luego ser cambiados x los jugadores.
	private Color(String nombreTerran, String nombreZerg, String nombreProtoss){
		
		String[] _nombresPorDefecto = {nombreTerran, nombreProtoss, nombreZerg};
		this.nombresPorDefecto = _nombresPorDefecto;
	}
	
	public String getNombrePorDefecto(TipoRaza tipoRazaJugador) {
		int i = 0;
		for (TipoRaza tipoRaza: TipoRaza.values()){
			if (tipoRaza == tipoRazaJugador)
				return nombresPorDefecto[i];
			i++;
		}
		//TODO: Mejorar esto con alguna exception?
		return null;
		
	}
	
	
	
	
	
	
	
	
}
