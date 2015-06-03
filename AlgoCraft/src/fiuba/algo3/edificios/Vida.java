package fiuba.algo3.edificios;

public interface Vida extends Componente{
	
	public int getVidaMaxima();
	
	public int getVida();
	
	public void regenerar(int vida);
	
	public void recibirDanio(int puntos);

}
