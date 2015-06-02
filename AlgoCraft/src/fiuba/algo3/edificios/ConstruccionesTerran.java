package fiuba.algo3.edificios;

import fiuba.algo3.tp_final.Jugador;

public class ConstruccionesTerran { //asignar Jugador en tiempo de creacion??!?!?!
	
	public Edificio crearCentroDeMineral(Jugador jugador, int x, int y){
		
		//Chequear Requisitos TODO
		
		Edificio edificio = new Edificio("Centro de Mineral", jugador, x, y);
		
		Vida vida = new VidaSinEscudo(500,0);
		Trabajo recolector = new TrabajoRecoleccionMineral(10, jugador);
		Trabajo construccion = new TrabajoConstruccion(4, vida, recolector);
		
		edificio.setVida(vida);
		edificio.setTrabajo(construccion);
		return edificio;
		
	}
	
	//public Edificio crearRefineria();
	
	//public Edificio crearDepositoDeSuministros();
	
	//public Edificio crearBarraca();
	
	//public Edificio crearFabrica(); //requiere el de arriba
	
	//public Edificio crearPuertoEstelar(); //requiere el de arriba
}