package fiuba.algo3.atributos;

public class AtributosCosto {
	
	private int costoMineral;
	private int costoGasVespeno;
	private int turnosConstruccion;
	
	public AtributosCosto(int costoMineral, int costoGasVespeno, int turnosConstruccion){
		this.costoMineral = costoMineral;
		this.costoGasVespeno = costoGasVespeno;
		this.turnosConstruccion = turnosConstruccion;
	}
	
	public int getCostoMineral() {
		return costoMineral;
	}
	
	public int getCostoGasVespeno() {
		return costoGasVespeno;
	}
	
	public int getTurnosConstruccion() {
		return turnosConstruccion;
	}

}
