package fiuba.algo3.edificios;

import fiuba.algo3.componentes.IVida;
import fiuba.algo3.excepciones.VidaEnCeroException;
import fiuba.algo3.juego.Jugador;
import fiuba.algo3.juego.Ocupante;
import fiuba.algo3.mapa.Posicion;

public abstract class ObjetoVivo extends Ocupante { //ObjetoVivo / ObjetoInteractuable / etc.

	protected Jugador propietario;
	protected IVida vida;

	public ObjetoVivo(Jugador propietario, Posicion posicion) {
		this.propietario = propietario;
		this.posicion = posicion;
	}

	public Jugador getPropietario() {
		return this.propietario;
	}

	public Posicion getPosicion() {
		return this.posicion;  //quizas devolver copia!
	}

	public int getVida() {
		return this.vida.getVida();
	}

	public void regenerarVida(int puntos) {
		this.vida.regenerar(puntos);
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean tieneEscudo(){
		return vida.tieneEscudo();
	}
	
	public int getEscudo(){
		return vida.getEscudo();
	}
	
	public void setVida(IVida vida) {
		this.vida = vida;		
	}

	public int getVidaMaxima() {
		return this.vida.getVidaMaxima();
	}

	public void recibirDanio(int puntos) {
		try {
			this.vida.recibirDanio(puntos);
		} 
		catch (VidaEnCeroException e) {
			this.destruir();
		}
	}
	
	public void destruir(){
		//Codigo para limpiar la existencia de Unidad o Edificio:
		//- eliminar del mapa
		//- eliminar de la lista del jugador
		//- Limpiezas internas:
		//	--p/ej: devolver las unidades transportadas al mapa
		//  --p/ej: devolver recursos en EntrenadorUnidades de construcciones no terminadas.
	}

	public void pasarTurno(){
		this.vida = (IVida)this.vida.pasarTurno(); //debe haber solucion mas elegante
	}
}