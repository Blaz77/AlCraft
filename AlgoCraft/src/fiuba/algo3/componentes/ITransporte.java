package fiuba.algo3.componentes;

import java.util.ArrayList;
import fiuba.algo3.ocupantes.unidades.Pasajero;
import fiuba.algo3.ocupantes.unidades.Unidad;

public interface ITransporte {
	
	public boolean puedeAlmacenar();
	
	//							ObjetoVivo obj.
	public boolean puedeAlmacenarA(Unidad unidad);
	//							ObjetoVivo obj.
	public void almacenarA(Unidad unidad);
	
	public ArrayList<Pasajero> getUnidadesAlmacenadas();
	
	public void liberarUnidad(Unidad unidadAlmacenada);

}
