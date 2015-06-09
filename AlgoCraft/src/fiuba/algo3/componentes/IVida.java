package fiuba.algo3.componentes;

public interface IVida{
	
	public int getVida();
	
	public int getVidaMaxima();
	
	public boolean tieneEscudo();
	
	public int getEscudo();
	
	public int getEscudoMaximo();
	
	public void regenerarVida(int cantidad);
	
	public void regenerarEscudo(int cantidad);
	
	public void recibirDanio(int puntos);

}
