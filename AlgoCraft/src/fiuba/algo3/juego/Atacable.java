package fiuba.algo3.juego;

// TODO En peligro de ser eliminada, dado que si ubicamos los edificios y unidades en distintos
// casilleros nunca tendremos un caso de polimorfismo en tiempo de ejecucion...


/* Interfaz atacable engloba todas las clases vulnerables a 
 * recibir ataques. Por ende, deben tener una "vida" y 
 * métodos para acceder a ella y/o modificarla
 */
public interface Atacable {

	public int getVida();

	public int getVidaMaxima();
	
	public void bajarVida(int puntos);
	
}
