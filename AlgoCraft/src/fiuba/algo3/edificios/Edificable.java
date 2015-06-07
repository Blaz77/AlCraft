//TODO: Q onda esto.. nadie lo esta implementando

package fiuba.algo3.edificios;

public interface Edificable {
	//TODO: Todos los que usaban edificable ahora podrian usar Edificio
	
	public boolean enTierra();
	
	public boolean enEspacio();
	
	public boolean sobreRecurso();// quizas sacar este y dejar solo los dos de abajo
	
	public boolean sobreMineral();
	
	public boolean sobreGasVespeno();

}
