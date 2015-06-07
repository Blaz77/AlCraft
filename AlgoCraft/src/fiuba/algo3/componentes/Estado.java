package fiuba.algo3.componentes;

public interface Estado { //Efecto
	
	//A llamar cuando ingresa en un Edificio/Unidad
	public void activar();
	
	//A llamar en el pasarTurno() de Edificio/Unidad
	public void pasarTurno() throws Exception; //FinalizacionEstadoException
	
	//A llamar cuando finalizo el estado (la excepcion de arriba).
	public void desactivar();

}
