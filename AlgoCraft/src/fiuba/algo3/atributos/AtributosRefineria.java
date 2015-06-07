package fiuba.algo3.atributos;

public class AtributosRefineria extends AtributosEdificioRecolectorGasVespeno {

	public AtributosRefineria() {
		this.cantARecolectarPorTurno = 10;
		this.vidaMaxima = 750;
		this.nombre = "Refineria";
	}
	
	@Override
	public boolean tieneEscudo() {
		return false;
	}

	@Override
	public boolean esAereo() {
		return false;
	}

	@Override
	public int getRangoEfectivo(int RangoAire, int RangoTierra) {
		return RangoTierra;
	}

}
