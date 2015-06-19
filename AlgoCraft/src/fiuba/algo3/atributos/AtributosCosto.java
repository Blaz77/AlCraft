package fiuba.algo3.atributos;

public class AtributosCosto {
	
	private int costoPoblacion;
	private int costoMineral;
	private int costoGasVespeno;
	private int turnosConstruccion;
	
	public AtributosCosto(int costoMineral, int costoGasVespeno, int turnosConstruccion){
		this(costoMineral, costoGasVespeno, turnosConstruccion, 0);
	}
	
	public AtributosCosto(int costoMineral, int costoGasVespeno,
			 			 int turnosConstruccion, int costoPoblacion){
		this.costoMineral = costoMineral;
		this.costoGasVespeno = costoGasVespeno;
		this.turnosConstruccion = turnosConstruccion;
		this.costoPoblacion = costoPoblacion;
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
	
	public int getCostoPoblacion(){
		return costoPoblacion;
	}

}
