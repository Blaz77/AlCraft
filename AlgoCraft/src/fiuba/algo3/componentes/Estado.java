package fiuba.algo3.componentes;

import fiuba.algo3.excepciones.EstadoFinalizado;
import fiuba.algo3.ocupantes.ObjetoVivo;

public interface Estado{
	
	public String getDescripcion();
	
	//A llamar cuando ingresa en un Edificio/Unidad
	public void activar(ObjetoVivo portador);
	
	/* ESTO NO MIRARLO POR AHORA!:
	//Booleano indicando si debe anular probables Estados del ObjetoVivo
	//public boolean anulaEstados()
	
	//Si el 
	//public void anular(Estado anulable) throws Exception;
	*/
	
	//A llamar en el pasarTurno() de Edificio/Unidad
	public void pasarTurno() throws EstadoFinalizado; //FinalizacionEstadoException
	
	//A llamar cuando finalizo el estado (la excepcion de arriba). o muere la unidad!
	public void desactivar();

}
