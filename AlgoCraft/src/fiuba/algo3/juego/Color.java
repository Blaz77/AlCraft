package fiuba.algo3.juego;

import fiuba.algo3.raza.TipoRaza;

public enum Color {
	
	ROJO		(		"Guardia �lite",	"Cr�a Tiamat",     "Tribu Ara"),
	AZUL		(			 "Mar Sara",	"Cr�a Surtur",  "Tribu Sargas"), 
	AZUL_TEAL	("Coalici�n Kel-Morian",	"Cr�a Fenris",  "Tribu Akilae"),
	PURPURA		(			   "Antiga","Cr�a Jormungand", "Tribu Furinax"),
	NARANJA		(	  "Escuadr�n Delta",	  "Cr�a Garm",  "Tribu Auriga"),
	MARRON		(	  "Escuadr�n Omega",   "Cr�a Grendel", "Tribu Venatir"),
	BLANCO		(	   "Escuadr�n Alfa",   "Cr�a Baelrog",  "Tribu Shelak"),
	AMARILLO	(	"Escuadr�n Epsilon", "Cr�a Leviathan",  "Tribu Velari");
	
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
