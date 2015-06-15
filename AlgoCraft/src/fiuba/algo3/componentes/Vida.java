package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosVida;
import fiuba.algo3.excepciones.VidaEnCeroException;

//public class Vida implements Lastimable/Atacable/etc
public class Vida implements IVida {

	protected AtributosVida atributos;
	private int vida;

	// Para setear especificamente la vida inicial (ej: construccion de edificios)
	public Vida(int vidaInicial, AtributosVida atributos){
		this.atributos = atributos;
		this.vida = vidaInicial;
	}
	
	public Vida(AtributosVida atributos) {
		this(atributos.getVidaMaxima(), atributos);
	}
		
	public int getVida(){
		return this.vida;
	}
	
	public int getVidaMaxima(){
		return atributos.getVidaMaxima();
	}
	
	public boolean tieneEscudo() {
		return false;
	}

	public int getEscudo() {
		return 0;
	}

	public int getEscudoMaximo() {
		return 0;
	}
	
	public void regenerarEscudo(int cantidad) {}
	
	public void regenerarVida(int cantidad){
		// Si excede la vida maxima, la iguala
		this.vida += cantidad;
		
		//vida = vida < getVidaMaxima() ? vida : getVidaMaxima();
		if (this.vida > atributos.getVidaMaxima()) this.vida = atributos.getVidaMaxima();
	}
	
	public void recibirDanio(int puntos){
		this.vida -= puntos;
		if (this.vida <= 0) throw new VidaEnCeroException(); 
		// Emi: como dije antes, usar excepciones como herramienta de control del flujo del programa
		// me huele muy mal. Suelen no usarse xq manejar estos casos con valores de retorno es entendible
		// (para programadores al menos), mas rapido (las excepciones son lentas) y MUCHO mas rapido (le
		// permitimos al compilador / maquina virtual hacer optimizaciones si encuentra; con excepciones en
		// el medio no puede.)
	}

}
