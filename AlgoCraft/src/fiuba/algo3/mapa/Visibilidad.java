package fiuba.algo3.mapa;

public enum Visibilidad {

	//		verTerreno verOcupante
	VISIBLE		( true,       true), 
	NO_VISIBLE	( true,      false), 
	DESCONOCIDO	(false,      false);
	
	private boolean verTerreno;
	private boolean verOcupante;

	private Visibilidad(boolean verTerreno, boolean verOcupante){
		this.verTerreno = verTerreno;
		this.verOcupante = verOcupante;
	}
	
	public boolean verTerreno(){
		return verTerreno;
	}
	
	public boolean verOcupante(){
		return verOcupante;
	}
}