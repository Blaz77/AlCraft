
package fiuba.algo3.componentes;

import fiuba.algo3.atributos.AtributosObjetoVivo;

public class VidaConEscudo extends Vida {
	
	private int escudo;
	
	// Para setear especificamente la vida/escudo inicial (ej: construccion de edificios)
	public VidaConEscudo(int vidaInicial, int escudoInicial, AtributosObjetoVivo atributos) {
		super(vidaInicial, atributos);
		this.escudo = escudoInicial;
	}
	
	public VidaConEscudo(AtributosObjetoVivo atributos){
		super(atributos);
		this.escudo = this.atributos.getEscudoMaximo();
	}

	@Override
	public boolean tieneEscudo(){
		return atributos.tieneEscudo();
		//return true;
	}

	@Override
	public int getEscudo(){
		return this.escudo;
	}
	
	@Override
	public int getEscudoMaximo(){
		return this.atributos.getEscudoMaximo();
	}
	
	@Override
	public void regenerarEscudo(int cantidad) {
		this.escudo += cantidad;
		if (this.escudo > atributos.getEscudoMaximo()) 
			this.escudo = atributos.getEscudoMaximo();
	}
	
	public void recibirDanio(int puntos){
		this.escudo -= puntos;
		if (this.escudo < 0){
			super.recibirDanio(Math.abs(this.escudo)); //SUPER lanza la excepcion de muerte
			this.escudo = 0;
		}
	}
	
}
