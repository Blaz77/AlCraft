package fiuba.algo3.atributos;

public class AtributosVoluntadDelSer {
	
	private int costoPoblacion;
	private int movPorTurno; 
	private int rangoVision;
	
	public AtributosVoluntadDelSer(int costoPoblacion, int movPorTurno, 
			int rangoVision) {
		this.costoPoblacion = costoPoblacion; 
		this.movPorTurno = movPorTurno;	
		this.rangoVision = rangoVision;
	}
	
	public int getCostoPoblacion(){
		return costoPoblacion;
	}
	
	public int getMovPorTurno(){
		return movPorTurno;
	}
	
	public int getRangoVision(){
		return rangoVision;
	}

}
