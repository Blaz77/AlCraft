package fiuba.algo3.componentes;

// Interfaz para accion que una unidad/rec/edif
// esta realizando. 
// TODO renombrar a algo mas logico.
public interface Trabajo extends Componente {
	
	//public void cambiarA(Trabajo otroTrabajo) throws Exception;
	
	//public void abortar() throws Exception;

	//public void pasarTurno() throws Exception;
	public Trabajo pasarTurno();
	
}
