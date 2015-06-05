package fiuba.algo3.edificios;

import java.util.ArrayList;

import fiuba.algo3.juego.Jugador;
import fiuba.algo3.unidades.Constructor;

public abstract class EdificioEntrenadorUnidades extends Edificio implements IEntrenador{
	
	protected EntrenadorUnidades entrenador = new EntrenadorUnidades(this);
	
	public EdificioEntrenadorUnidades(Jugador propietario, int x, int y) {
		super(propietario, x, y);
		this.trabajo = (Trabajo) this.entrenador; //quizas mover esto despues
	}
	
	public boolean puedeEntrenarUnidades(){
		return true;
	}
	
	public ArrayList<Constructor> getUnidadesEntrenables(){
		return this.entrenador.getUnidadesEntrenables();
	}

}
