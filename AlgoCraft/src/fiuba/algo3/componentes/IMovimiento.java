package fiuba.algo3.componentes;

import java.util.HashSet;

import fiuba.algo3.mapa.Mapa;
import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.unidades.Unidad;

public interface IMovimiento {
	
	//Pensar:
		// - Habria que diferenciar los que no se pueden mover nunca
		// 	 de los que terminaron sus turnos?
		public boolean puedeMoverse();
		
		public boolean puedeMoverseA(Posicion destino);
		
		public void moverA(Posicion destino);

		public HashSet<Posicion> getPosiblesMovimientos(Mapa mapa);

		public int getRangoVision();

		public int getCostoAlmacenamiento();

		public boolean puedeSerAlmacenada();

}
