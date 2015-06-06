//TODO Clase sin usar!

package fiuba.algo3.componentes;

public class VidaConEscudo extends Vida {
	
	private int escudo;
	private final int escudoMaximo;
	
	// Para setear especificamente la vida/escudo inicial (ej: construccion de edificios)
	public VidaConEscudo(int vidaInicial, int vidaMaxima, int escudoInicial, int escudoMaximo) {
		super(vidaInicial, vidaMaxima);
		this.escudoMaximo = escudoMaximo;
		this.escudo = escudoInicial;
	}
	
	public VidaConEscudo(int vidaMaxima, int escudoMaximo){
		this(vidaMaxima, vidaMaxima, escudoMaximo, escudoMaximo);
	}

	public boolean tieneEscudo(){
		return true;
	}

	public int getEscudo(){
		return this.escudo;
	}
	
	public int getEscudoMaximo(){
		return this.escudoMaximo;
	}
	
	public void recibirDanio(int puntos){
		this.escudo -= puntos;
		if (this.escudo < 0){
			super.recibirDanio(-(this.escudo)); //SUPER lanza la excepcion de muerte
			this.escudo = 0;
		}
	}
	
	public Componente pasarTurno() {
		//REGENERA 10% POR TURNO, SE PUEDE CAMBIAR, VER BIEN EL REAL
		this.escudo += (this.escudoMaximo / 10);
		if (this.escudo > this.escudoMaximo)
			this.escudo = this.escudoMaximo;
		return this;
	}
	
}
