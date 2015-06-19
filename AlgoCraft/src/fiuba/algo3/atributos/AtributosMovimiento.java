package fiuba.algo3.atributos;

public class AtributosMovimiento {
	
	private int movPorTurno;
	private int rangoVision;
	private int costoAlmacenamiento;
	
	public AtributosMovimiento(int movPorTurno, int rangoVision, int costoAlmacenamiento) {
		this.movPorTurno = movPorTurno;
		this.rangoVision = rangoVision;
		this.costoAlmacenamiento = costoAlmacenamiento;
	}

	public int getMovPorTurno() {
		return movPorTurno;
	}
	
	public int getRangoVision(){
		return rangoVision;
	}
	
	public int getCostoAlmacenamiento(){
		return costoAlmacenamiento;
	}

}
