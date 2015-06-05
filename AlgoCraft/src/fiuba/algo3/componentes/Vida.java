package fiuba.algo3.componentes;

import fiuba.algo3.excepciones.VidaEnCeroException;

//public class Vida implements Lastimable/Atacable/etc
public class Vida implements IVida {

	private final int vidaMaxima;
	private int vida;

	// Para setear especificamente la vida inicial (ej: construccion de edificios)
	public Vida(int vidaInicial, int vidaMaxima){
		this.vidaMaxima = vidaMaxima;
		this.vida = vidaInicial;
	}
	
	public Vida(int vidaMaxima) {
		this(vidaMaxima, vidaMaxima);
	}

	public int getVidaMaxima(){
		return this.vidaMaxima;
	}
		
	public int getVida(){
		return this.vida;
	}
	
	public boolean tieneEscudo() {
		return false;
	}

	public int getEscudo() {
		throw new RuntimeException();
	}

	public int getEscudoMaximo() {
		throw new RuntimeException();
	}
	
	public void regenerar(int vida){
		// Si excede la vida maxima, la iguala
		this.vida += vida;
		if (this.vida > this.vidaMaxima) this.vida = this.vidaMaxima;
	}
	
	public void recibirDanio(int puntos){
		this.vida -= puntos;
		if (this.vida <= 0) throw new VidaEnCeroException();
	}

	public void setAnterior(Componente componenteAnterior) {

	}

	public Componente pasarTurno() {
		return this;
	}


}
