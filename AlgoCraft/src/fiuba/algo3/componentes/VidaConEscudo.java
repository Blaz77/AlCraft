package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosObjetoVivo;

public class VidaConEscudo extends Vida {
	
	private int escudo;
	private int escudoMaximo;
	
	// Para setear especificamente la vida/escudo inicial (ej: construccion de edificios)
	public VidaConEscudo(int vidaInicial, int escudoInicial, AtributosObjetoVivo atributos) {
		super(vidaInicial, atributos);
		this.escudo = escudoInicial;
		this.escudoMaximo = atributos.getEscudoMaximo();
	}
	
	public VidaConEscudo(AtributosObjetoVivo atributos){
		this(atributos.getVidaMaxima(), atributos.getEscudoMaximo(), atributos);
	}

	@Override
	public boolean tieneEscudo(){
		//return atributos.tieneEscudo(); // Consideren que esta clase ya sabe q tiene escudo, y los atributos no los necesitaria para nada
		return true;
	}

	@Override
	public int getEscudo(){
		return this.escudo;
	}
	
	@Override
	public int getEscudoMaximo(){
		return this.escudoMaximo;
	}
	
	@Override
	public void regenerarEscudo(int cantidad) {
		this.escudo += cantidad;
		if (this.escudo > getEscudoMaximo()) // escudo = escudo < escudoMaximo ? escudo : escudoMaximo;
			this.escudo = getEscudoMaximo(); // Magia de C ^
	}
	
	public void recibirDanio(int puntos){
		this.escudo -= puntos;
		if (this.escudo < 0){
			super.recibirDanio(Math.abs(this.escudo)); //SUPER lanza la excepcion de muerte
			this.escudo = 0; // ^ Esta linea es gloriosa
		}
	}
	
}
