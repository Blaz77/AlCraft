package fiuba.algo3.edificios;

//public class Vida implements Lastimable/Atacable/etc
public class VidaSinEscudo implements Vida {

	private final int vidaMaxima;
	private int vida;

	/*public VidaSinEscudo(int vidaInicial, int vidaMaxima){
		this.vidaMaxima = vidaMaxima;
		this.vida = vidaInicial;
	}*/
	
	public VidaSinEscudo(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
		this.vida = 0;
	}

	public int getVidaMaxima(){
		return this.vidaMaxima;
	}
		
	public int getVida(){
		return this.vida;
	}
	
	public void regenerar(int vida){
		// Si excede la vida maxima, la iguala
		this.vida += vida;
		if (this.vida > this.vidaMaxima) this.vida = this.vidaMaxima;
	}
	
	public void recibirDanio(int puntos){
		this.vida -= puntos;
		if (this.vida <= 0) throw new RuntimeException();//? 
	}

	public void setAnterior(Componente componenteAnterior) {

	}
}
