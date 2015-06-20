package fiuba.algo3.ocupantes;

import fiuba.algo3.mapa.Posicion;
import fiuba.algo3.ocupantes.recurso.Tipo;
import fiuba.algo3.ocupantes.recurso.TipoOcupante;

public interface Ocupante {
	
	public Tipo getTipo();
	
	public TipoOcupante getTipoOcupante();

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
