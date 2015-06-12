package fiuba.algo3.componentes;

import fiuba.algo3.ocupantes.ObjetoVivo;

public interface Estado{ //Efecto
	
	//public Estado clone();
	
	//A llamar cuando ingresa en un Edificio/Unidad
	public void activar(ObjetoVivo portador);
	
	/* ESTO NO MIRARLO POR AHORA!:
	//Booleano indicando si debe anular probables Estados del ObjetoVivo
	//public boolean anulaEstados()
	
	//Si el 
	//public void anular(Estado anulable) throws Exception;
	*/
	
	//A llamar en el pasarTurno() de Edificio/Unidad
	public void pasarTurno() throws Exception; //FinalizacionEstadoException
	
	//A llamar cuando finalizo el estado (la excepcion de arriba).
	public void desactivar();

}
