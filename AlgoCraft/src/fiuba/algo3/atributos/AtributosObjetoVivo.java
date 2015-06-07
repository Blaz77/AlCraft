package fiuba.algo3.atributos;

public abstract class AtributosObjetoVivo { //AtributosObjetoDeJuego?
	
	
	int costoMineral;
	int costoGasVespeno;
	int turnosConstruccion;
	
	int vidaMaxima;
	//int escudoMaximo;
	String nombre;

	public int getCostoMineral(){
		return costoMineral;
	}
	
	public int getCostoGasVespeno(){
		return costoGasVespeno;
	}
	
	public int getTurnosConstruccion(){
		return turnosConstruccion;
	}
	
	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean tieneEscudo(){
		return false;
	}
	
	// si no es Aereo, es Terrestre
	// Bajar a Unidad?
	public boolean esAereo(){ //puedeVolar()
		return false;
	}
	// El enemigo que me quiere atacar me pasa sus dos rangos
	// y yo que se como soy, le devuelvo el que funciona 
	// en el ataque correspondiente.
	// -> yo soy aereo, entonces devuelvo rangoAire
	// -> yo soy terrestre, entonces devuelvo rangoTierra.
	public int getRangoEfectivo(int rangoAire, int rangoTierra){
		return rangoTierra;
	}

	public int getDanioEfectivo(int danioAire, int danioTierra) {
		return danioTierra;
	}

}
