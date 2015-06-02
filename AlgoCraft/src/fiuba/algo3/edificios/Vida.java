package fiuba.algo3.edificios;

public interface Vida {
	
	public int getVidaMaxima();
	
	public int getVida();
	
	public void regenerar(int vida);
	
	public void recibirDaño(int puntos);

}
