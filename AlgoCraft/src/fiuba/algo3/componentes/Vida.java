package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosObjetoVivo;
import fiuba.algo3.excepciones.VidaEnCeroException;

//public class Vida implements Lastimable/Atacable/etc
public class Vida implements IVida {

	private AtributosObjetoVivo atributos;
	private int vida;

	// Para setear especificamente la vida inicial (ej: construccion de edificios)
	public Vida(int vidaInicial, AtributosObjetoVivo atributos){
		this.atributos = atributos;
		this.vida = vidaInicial;
	}
	
	public Vida(AtributosObjetoVivo atributos) {
		this(atributos.getVidaMaxima(), atributos);
	}

	public int getVidaMaxima(){
		return this.atributos.getVidaMaxima();
	}
		
	public int getVida(){
		return this.vida;
	}
	
	public boolean tieneEscudo() {
		return atributos.tieneEscudo();
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
		if (this.vida > atributos.getVidaMaxima()) this.vida = atributos.getVidaMaxima();
	}
	
	public void recibirDanio(int puntos){
		this.vida -= puntos;
		if (this.vida <= 0) throw new VidaEnCeroException();
	}

}
