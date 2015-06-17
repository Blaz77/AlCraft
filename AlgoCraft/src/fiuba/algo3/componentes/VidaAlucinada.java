package fiuba.algo3.componentes;

public class VidaAlucinada implements IVida {

	private IVida vida;
	
	public VidaAlucinada(IVida vidaOriginal) {
		this.vida = vidaOriginal;
	}

	public int getVida() {
		return this.getVidaMaxima(); //tecnicamente no tiene vida, pero siempre muestro que si.
	}

	public int getVidaMaxima() {
		return vida.getVidaMaxima();
	}

	public boolean tieneEscudo() {
		return vida.tieneEscudo();
	}

	public int getEscudo() {
		return vida.getEscudo();
	}

	public int getEscudoMaximo() {
		return vida.getEscudoMaximo();
	}

	public void regenerarVida(int cantidad) {}

	public void regenerarEscudo(int cantidad) {
		vida.regenerarEscudo(cantidad);
	}

	public void recibirDanio(int puntos) {
		vida.recibirDanio(puntos);
		if (vida.getEscudo() == 0) //lo mato
			vida.recibirDanio(vida.getVidaMaxima());
	}

}
