package fiuba.algo3.edificios;

public interface Trabajo extends Componente {
	
	//public void cambiarA(Trabajo otroTrabajo) throws Exception;
	
	//public void abortar() throws Exception;

	//public void pasarTurno() throws Exception;
	public Trabajo pasarTurno();
	
}
