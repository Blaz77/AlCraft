package fiuba.algo3.ocupantes;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;

public interface Ocupante {
	
	public TipoOcupante getTipo();
	
	public Posicion getPosicion();
	
	//public String toString();

	public boolean puedeCambiarsePor(Ocupante otroOcupante);
	
	public void lanzarExcepcionDeCambio();
	
	public boolean puedeOcuparTierra();
	
	public boolean puedeOcuparEspacio();
	
	public boolean debeOcuparRecurso();
	
	public boolean debeOcuparMineral();
	
	public boolean debeOcuparGasVespeno();
}
