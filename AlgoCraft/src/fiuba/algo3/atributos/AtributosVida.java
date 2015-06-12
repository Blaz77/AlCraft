package fiuba.algo3.atributos;

public class AtributosVida {
	
	private int vidaMaxima;
	private int escudoMaximo; //inicializacion necesaria solo para Protoss!
	
	public AtributosVida(int vidaMaxima, int escudoMaximo){
		this.vidaMaxima = vidaMaxima;
		this.escudoMaximo = escudoMaximo;
	}
	
	public AtributosVida(int vidaMaxima){
		this(vidaMaxima, 0);
	}
	
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	
	public int getEscudoMaximo() {
		return escudoMaximo;
	}

}
