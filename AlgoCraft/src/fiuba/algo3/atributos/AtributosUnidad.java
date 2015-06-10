package fiuba.algo3.atributos;

public abstract class AtributosUnidad extends AtributosObjetoVivo {

	int costoPoblacion;
	int costoAlmacenamiento; //costoTransporte
	int movPorTuno;
	int rangoVision;  //quizas pasar a ObjetoVivo, chequear si edificios tienen vision
					  //nop, los edificios estan habitados por ciegos parece... logic (?
	
	public int getCostoPoblacion(){
		return costoPoblacion;
	}
	
	public int getMovPorTurno(){
		return movPorTuno;
	}
	
	public int getRangoVision(){
		return rangoVision;
	}
	
	public int getCostoAlmacenamiento(){
		return costoAlmacenamiento;
	}

	public boolean puedeSerAlmacenada(){ //puedeSerTransportada
		return false;
	}
	
	public boolean puedeAtacar(){
		return false;
	}
	
	public boolean puedeHacerMagia(){
		return false;
	}
		
	public boolean puedeAlmacenar(){ //puedeTransportar()
		return false;
	}
}
