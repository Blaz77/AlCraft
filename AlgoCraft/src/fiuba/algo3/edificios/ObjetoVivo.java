package fiuba.algo3.edificios;

import fiuba.algo3.componentes.Vida;
import fiuba.algo3.componentes.VidaNull;
import fiuba.algo3.juego.Jugador;

public abstract class ObjetoVivo { //ObjetoVivo / ObjetoInteractuable / etc.

	protected String nombre; //pasar a toString()
	protected Jugador propietario;
	protected int x;
	protected int y;
	protected Vida vida = new VidaNull();

	public ObjetoVivo(Jugador propietario, int x, int y) {
		this.propietario = propietario;
		this.x = x;
		this.y = y;
	}

	public Jugador getPropietario() {
		return this.propietario;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public void setVida(Vida vida) {
		this.vida = vida;		
	}

	public int getVidaMaxima() {
		return this.vida.getVidaMaxima();
	}

	public void recibirDanio(int puntos) {
		this.vida.recibirDanio(puntos);
	}

	public abstract void pasarTurno();
		//this.vida = this.vida.pasarTurno();
	

}