package fiuba.algo3.edificios;

//public class Vida implements Lastimable/Atacable/etc
public class VidaSinEscudo implements Vida {

	private final int vidaMaxima;
	private int vida;

	public VidaSinEscudo(int vidaMaxima, int vidaInicial){
		this.vidaMaxima = vidaMaxima;
		this.vida = vidaInicial;
	}
	
	public int getVidaMaxima(){
		return this.vidaMaxima;
	}
		
	public int getVida(){
		return this.vida;
	}
	
	public void regenerar(int vida){
		this.vida += vida;
		if (this.vida > this.vidaMaxima) this.vida = this.vidaMaxima;
	}
	
	public void recibirDaño(int puntos){
		this.vida -= puntos;
		if (this.vida <= 0) throw new RuntimeException();//? 
	}
}
