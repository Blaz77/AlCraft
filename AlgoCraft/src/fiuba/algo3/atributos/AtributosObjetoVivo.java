package fiuba.algo3.atributos;

public abstract class AtributosObjetoVivo {
	
	//Probablemente hacer: AtributosObjetoDelJuego
	//		- tiene tambien costos asociados!
	
	int vidaMaxima;
	//int escudoMaximo;
	String nombre;

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public String getNombre() {
		return nombre;
	}

	abstract public boolean tieneEscudo();
		//return false;

	// si no es Aereo, es Terrestre
	// Bajar a Unidad?
	abstract public boolean esAereo(); //puedeVolar()
	
	// El enemigo que me quiere atacar me pasa sus dos rangos
	// y yo que se como soy, le devuelvo el que funciona 
	// en el ataque correspondiente.
	// -> yo soy aereo, entonces devuelvo rangoAire
	// -> yo soy terrestre, entonces devuelvo rangoTierra.
	abstract public int getRangoEfectivo(int RangoAire, int RangoTierra);

}
