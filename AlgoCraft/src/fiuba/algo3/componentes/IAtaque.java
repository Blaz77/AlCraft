package fiuba.algo3.componentes;

import java.util.ArrayList;
import fiuba.algo3.ocupantes.ObjetoVivo;

public interface IAtaque {
	
	//Pensar:
	// - Habria que diferenciar los que no pueden atacar nunca
	// 	 de los que terminaron sus turnos?
	public boolean puedeAtacar();
	
	public boolean puedeAtacarA(ObjetoVivo enemigo);
	
	public void atacarA(ObjetoVivo enemigo);
	
	//esto es una idea nomas, no necesariamente hacerlo
	public ArrayList<ObjetoVivo> getPosiblesObjetivos();


}