package fiuba.algo3.A_BORRAR;

import fiuba.algo3.atributos.AtributosMagia;
import fiuba.algo3.componentes.Magia;

public class AtributosUnidadMagica extends AtributosUnidad {

	//protected int energiaARegenerarPorTurno;
	//protected int energiaMax;
	//protected int rangoMagia;
	protected AtributosMagia magia;
	
	@Override
	public boolean puedeHacerMagia(){
		return true;
	}
	
	public Magia getMagia() {
		return new Magia(this.magia);
	}
		
}
