package fiuba.algo3.tp_final;

public class Edificio implements Atacable{

	// 2 Opciones: 
	// - Herencia Edificio -> EdificioConstructor -> Barracas, etc.
	
	// - Edificio generico con estados internos - modificables, si fuese necesario-
	// El constructor de unidades va de la mano con estos estados.

	private int vida;
	private int x;
	private int y;
	
	public Edificio() {
		
		
	}
	
	public int getVida() {
		return 0;
	}

	public int getVidaMaxima() {
		return 0;
	}
	
	public void bajarVida(int puntos){
		
	}
	
	// Manejar veneno. Alguna interfaz para esto
	public void pasoDeTurno() {
		return;
	}
}
