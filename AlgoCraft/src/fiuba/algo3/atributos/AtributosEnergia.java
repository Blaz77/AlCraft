package fiuba.algo3.atributos;

public class AtributosEnergia {
	
	private int energiaMaxima;
	private int energiaARegenerarPorTurno;
	private int rangoDeMagia;
	
	public AtributosEnergia(int energiaMaxima, int energiaARegenerarPorTurno,
					int rangoDeMagia) {
		this.energiaMaxima = energiaMaxima;
		this.energiaARegenerarPorTurno = energiaARegenerarPorTurno;
		this.rangoDeMagia = rangoDeMagia;
	}
	
	public int getEnergiaMaxima() {
		return energiaMaxima;
	}

	public int getEnergiaARegenerarPorTurno() {
		return energiaARegenerarPorTurno;
	}

	public int getRangoMagia() {
		return rangoDeMagia;
	}

}
