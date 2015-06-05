package fiuba.algo3.componentes;

public interface IVida extends Componente{
	
	public int getVida();
	
	public int getVidaMaxima();
	
	public boolean tieneEscudo();
	
	public int getEscudo();
	
	public int getEscudoMaximo();
	
	public void regenerar(int vida);
	
	public void recibirDanio(int puntos);

}
